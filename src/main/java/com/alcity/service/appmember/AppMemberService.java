package com.alcity.service.appmember;

import com.alcity.comparetors.JourneyStepComparator;
import com.alcity.customexception.ResponseMessage;
import com.alcity.dto.RemoteAccess.RemoteRequestDTO;
import com.alcity.dto.appmember.*;
import com.alcity.dto.journey.RoadMapDTO;
import com.alcity.dto.plimpexport.PLObjectiveData;
import com.alcity.dto.puzzle.PLDTO;
import com.alcity.dto.puzzle.PLGameInstanceDTO;
import com.alcity.dto.puzzle.PLObjectiveDTO;
import com.alcity.dto.puzzle.PuzzleLevelStepMappingDTO;
import com.alcity.dto.search.AppMemberSearchCriteriaDTO;
import com.alcity.entity.alenum.*;
import com.alcity.entity.appmember.*;
import com.alcity.entity.base.ClientType;
import com.alcity.entity.base.PLPrivacy;
import com.alcity.entity.journey.Journey;
import com.alcity.entity.journey.JourneyStep;
import com.alcity.entity.puzzle.PLGameInstance;
import com.alcity.entity.puzzle.PLObjective;
import com.alcity.entity.puzzle.PuzzleLevel;
import com.alcity.o3rdparty.ALCityAcessRight;
import com.alcity.customexception.ResponseObject;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.base.MemberType;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.repository.appmember.AppMember_WalletItemRepository;
import com.alcity.repository.appmember.CustomizedUserRepository;
import com.alcity.repository.appmember.WalletItemRespository;
import com.alcity.repository.base.BinaryContentRepository;
import com.alcity.repository.base.PLPrivacyRepository;
import com.alcity.service.base.MemberTypeService;
import com.alcity.service.puzzle.PuzzleLevelService;
import com.alcity.utility.DTOUtil;
import com.alcity.utility.DateUtils;
import com.alcity.utility.GenerateSHA256;
import com.alcity.utility.SlicedStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
public class AppMemberService implements AppMemberRepository, CustomizedUserRepository {

    @Autowired

    private AppMemberRepository appMemberRepository;

    @Autowired
    private BinaryContentRepository binaryContentRepository;

    @Autowired
    private MemberTypeService memberTypeService;

    @Autowired
    private AppMember_WalletItemRepository appMember_WalletItemRepository;

    @Autowired
    private WalletItemRespository walletItemRespository;

    @Qualifier("PLPrivacyRepository")
    @Autowired
    private PLPrivacyRepository plPrivacyRepository;

    @Autowired
    private PuzzleLevelService puzzleLevelService;

    @Autowired
    private AppMemberPuzzleLevelScoreService appMemberPuzzleLevelScoreService;
    @Autowired
    private AppMember_LearningSkillService appMember_LearningSkillService;


    public Collection<AppMember> findByCriteria(AppMemberSearchCriteriaDTO dto) {
        Integer age =dto.getAge();
        Collection<AppMember> appMembers = appMemberRepository.findByUsernameContainingIgnoreCaseOrNicknameContainingIgnoreCaseOrEmailIsContainingIgnoreCase(dto.getCriteria(), dto.getCriteria(), dto.getCriteria());
      //  Collection<AppMember> matchValues = appMembers.stream().filter(appMember ->  appMember.getAge().equals(age)).toList();
        Stream<AppMember> appMemberStream = appMembers.stream();
        if(dto.getLastIndex() < 0L ) dto.setLastIndex(0L);
        if(dto.getPageSize() <= 0 ) dto.setPageSize(1);
        Collection<AppMember> page = SlicedStream.getSliceOfStream(appMemberStream,dto.getLastIndex() ,dto.getLastIndex()  + dto.getPageSize() -1 ).toList();
        return page;
    }

    @Cacheable(value = "getPublicPuzzleLevels", key = "#p1")
    public  Collection<PLDTO> getPublicPuzzleLevels(AppMember appMember){
        Collection<PLDTO>  pldtos= new ArrayList<PLDTO>();
        long start_time2 = System.currentTimeMillis();
        PLPrivacy pub = plPrivacyRepository.findByValue("Public");
        Collection<PuzzleLevel> puzzleLevels = puzzleLevelService.findByPuzzleLevelPrivacyByAge(pub,appMember.getAge());

        pldtos =DTOUtil.getPuzzleLevelDTOS(puzzleLevels);
        long end_time2 = System.currentTimeMillis();
        System.out.println("time for running getPublicPuzzleLevelByAppMemberNew Method = " + (end_time2 - start_time2)*0.001);
        return pldtos;
    }

    public  Collection<PLDTO> getPuzzleLevelsNotPlayed(Collection<PLDTO> puzzles,Collection<PLDTO> played){
        Collection<PLDTO>  notPlayed = puzzles.stream().filter(pldto -> played.contains(pldto)).collect(Collectors.toList());
        return notPlayed;
    }

    public  Collection<PLDTO> getPuzzleLevelsPlayed(AppMember appMember){
        Collection<PLDTO>  pldtos= new ArrayList<PLDTO>();
        Collection<PLGameInstance> gameInstances = appMember.getPlGameInstances();
        pldtos =DTOUtil.getPlayedPuzzlesByAppMemberDTOS(gameInstances);

        return pldtos;
    }

    public Collection<AppMemberJourneyDTO> getAppMemberJourneysByScores(AppMember member, Collection<Journey> journeys) {
        Collection<AppMemberJourneyDTO> dtos = new ArrayList<AppMemberJourneyDTO>();
        Iterator<Journey> itr = journeys.iterator();
        while(itr.hasNext()){
            Journey journey = itr.next();
            AppMemberJourneyDTO dto = getJourneyScoresForAppMember(member,journey);
            dtos.add(dto);
        }
        return  dtos;
    }


    public AppMemberJourneyInfo getAppMemberJourneyInfo(AppMember member, Journey journey) {
        Collection<AppMemberStepInfo> stepInfos = new ArrayList<>();
        Collection<PLDTO> publicPuzzleLevels = getPublicPuzzleLevels(member);
        Iterator<PLDTO> itr = publicPuzzleLevels.iterator();
        Collection<RoadMapDTO> roadMapDTOS = DTOUtil.getJourneyRoadMapsDTOS(journey.getRoadMaps());
        AppMemberJourneyInfo journeyInfo = new AppMemberJourneyInfo();
        journeyInfo.setRoadMaps(roadMapDTOS);
        journeyInfo.setJourneyTitle(journey.getTitle());
        journeyInfo.setJourneyId(journey.getId());
        journeyInfo.setAppMemberId(member.getId());
        journeyInfo.setAppMemberUserName(member.getUsername());
        journeyInfo.setCurrentIconId(journey.getButtonCurrenIcon().getId());
        journeyInfo.setPassedIconId(journey.getButtonPassedIcon().getId());
        journeyInfo.setLockedIconId(journey.getButtonLockedIcon().getId());
        while (itr.hasNext()){
            PLDTO pldto = itr.next();
            Collection<PLObjectiveDTO> objectives = pldto.getObjectives();
            Collection<PLObjectiveData> objectivesData = DTOUtil.getPLObjectivesData(objectives);

            JourneyStep journeyStep = puzzleLevelService.getPuzzleLevelMappedStep(pldto.getId());
            if(journeyStep!=null){
                if(journeyStep.getJourney().getId() == journey.getId()) {
                    AppMemberStepInfo dto = new AppMemberStepInfo(journeyStep.getId(), journeyStep.getTitle(),journeyStep.getXpos(),journeyStep.getYpos(),journeyStep.getOrdering(),
                        pldto.getId(), pldto.getIconId(), pldto.getTitle(), pldto.getPuzzleGroupId(), pldto.getPuzzleGroupTitle(),0,Boolean.FALSE,objectivesData);
                    stepInfos.add(dto);
                }
            }
        }
        Comparator journeyStepComparator = new JourneyStepComparator();
        List<AppMemberStepInfo> sortedList =new ArrayList<AppMemberStepInfo>();
        sortedList = stepInfos.stream().collect(toList());
        sortedList.sort(journeyStepComparator);

        journeyInfo.setSteps(sortedList);
        return journeyInfo;
    }

    public AppMemberJourneyInfo getAppMemberJourneyInfoWithScores(AppMember member, AppMemberJourneyInfo journeyInfo) {
        AppMemberJourneyInfo journeyInfoWithScores = new AppMemberJourneyInfo();
        journeyInfoWithScores.setRoadMaps(journeyInfo.getRoadMaps());
        journeyInfoWithScores.setJourneyTitle(journeyInfo.getJourneyTitle());
        journeyInfoWithScores.setJourneyId(journeyInfo.getJourneyId());
        journeyInfoWithScores.setPassedIconId(journeyInfo.getPassedIconId());
        journeyInfoWithScores.setCurrentIconId(journeyInfo.getCurrentIconId());
        journeyInfoWithScores.setLockedIconId(journeyInfo.getJourneyId());

        journeyInfoWithScores.setAppMemberId(member.getId());
        journeyInfoWithScores.setAppMemberUserName(member.getUsername());

        Collection<PLGameInstance>  histories= member.getPlGameInstances();
        Collection<PLGameInstanceDTO> playedPuzzles = DTOUtil.getPLGameInstanceDTOS(histories);
        Iterator<PLGameInstanceDTO> itr = playedPuzzles.iterator();
        Collection<AppMemberStepInfo> stepInfos = journeyInfo.getSteps();

        while (itr.hasNext()){
            PLGameInstanceDTO historyDTO = itr.next();
            Optional<AppMemberStepInfo> stepInfoOptional = stepInfos.stream().filter(AppMemberStepInfo -> AppMemberStepInfo.getPuzzleLevelId() == historyDTO.getPuzzleLevelId()).findFirst();
            Float score =0.f;
            if(stepInfoOptional.isPresent()){
                AppMemberStepInfo stepInfo =stepInfoOptional.get();
                Optional<PuzzleLevel> puzzleLevelOptional = puzzleLevelService.findById(stepInfo.getPuzzleLevelId());
                PuzzleLevel puzzleLevel = puzzleLevelOptional.get();
                Optional<AppMemberPuzzleLevelScore> scoreOptional = appMemberPuzzleLevelScoreService.findByPuzzleLevelAndPlayer(puzzleLevel,member);
                if(scoreOptional.isPresent()) {
                    stepInfo.setCompleted(Boolean.TRUE);
                    score = scoreOptional.get().getScoreByBaseCurrency();
                }else {
                    stepInfo.setCompleted(Boolean.FALSE);
                    score =0f;
                    AppMemberPuzzleLevelScore puzzleLevelScore = new AppMemberPuzzleLevelScore(member,puzzleLevel,0f,
                            1L,DateUtils.getNow(),DateUtils.getNow(),member,member);
                    appMemberPuzzleLevelScoreService.save(puzzleLevelScore);
                }
                Integer stars=getPuzzleLevelAppMemberStars(score,puzzleLevel);

                stepInfo.setStars(stars);
            }

        }
        journeyInfoWithScores.setSteps(stepInfos);
        return journeyInfoWithScores;
    }

    public Integer getPuzzleLevelAppMemberStars(Float score, PuzzleLevel puzzleLevel) {
        Float firstScoreStar = puzzleLevel.getFirstStarScore();
        Float secondScoreStar = puzzleLevel.getSecondStarScore();
        Float thirdScoreStar = puzzleLevel.getThirdStartScore();
        if(score>=firstScoreStar && score<=secondScoreStar )
            return 1;
        if(score>secondScoreStar && score<thirdScoreStar )
            return 2;
        if(score>=thirdScoreStar )
            return  3;

        return 0;
    }

    public AppMemberJourneyDTO getJourneyScoresForAppMember(AppMember member, Journey journey) {
        Collection<PLGameInstance> histories = member.getPlGameInstances();
        Collection<PuzzleLevelStepMappingDTO> mappingDTOS = new ArrayList<>();
        AppMemberJourneyDTO dto = new AppMemberJourneyDTO();
        Integer currentStar=0;
        Iterator<PLGameInstance> itr = histories.iterator();
        dto.setTitle(journey.getTitle());
        dto.setOrdering(journey.getOrdering());
        dto.setAppMemberId(member.getId());
        dto.setOpen(Boolean.TRUE);
        dto.setPicId(journey.getPic().getId());
        dto.setMinToOpenStar(journey.getMinToOpenStar());
        dto.setMinToPassStar(journey.getMinToPassStar());
        dto.setJourneyId(journey.getId());
        while(itr.hasNext()) {
            PLGameInstance playHistory = itr.next();
            Long journeyId = puzzleLevelService.getJourneyIdMappedWithPuzzleLevel(playHistory.getPuzzleLevel());
            if(journeyId.equals(journey.getId())) {
                Optional<AppMemberPuzzleLevelScore> puzzleLevelScoreOptional = appMemberPuzzleLevelScoreService.findByPuzzleLevelAndPlayer(playHistory.getPuzzleLevel(),playHistory.getPlayer());
                if(puzzleLevelScoreOptional.isEmpty())
                    currentStar+=0;
                else
                    currentStar += getPuzzleLevelAppMemberStars(puzzleLevelScoreOptional.get().getScoreByBaseCurrency(),playHistory.getPuzzleLevel());
            }
        }
        dto.setCurrentStar(currentStar);
        if(currentStar >= journey.getMinToOpenStar())
            dto.setOpen(Boolean.TRUE);
        else if (currentStar==0 && journey.getOrdering()==1L)
            dto.setOpen(Boolean.TRUE);

        return  dto;
    }

    public AppMember_WalletItem chargeOrDeChargeAppMemberWallet(AppMemberWalletDTO dto, String code) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        Optional<WalletItem> walletItemOptional = walletItemRespository.findById(dto.getWalletItemId());
        Optional<AppMember> appMemberOptional = appMemberRepository.findById(dto.getAppMemberId());
        WalletItem walletItem =null;
        AppMember appMember =null;

        if(walletItemOptional.isPresent())
            walletItem = walletItemOptional.get();

        if(appMemberOptional.isPresent())
            appMember = appMemberOptional.get();


        AppMember_WalletItem appMember_walletItem=null;
        Optional<AppMember_WalletItem> isWalletItemPresent = appMember_WalletItemRepository.findByApplicationMemberAndWalletItem(appMember,walletItem);
        if (code.equalsIgnoreCase("Save") && !isWalletItemPresent.isPresent()) { //Save
            appMember_walletItem = new AppMember_WalletItem(appMember,walletItem, dto.getAmount()
                    ,1L, DateUtils.getNow(), DateUtils.getNow(), createdBy.get(), createdBy.get());
            appMember_WalletItemRepository.save(appMember_walletItem);
        } else if(code.equalsIgnoreCase("Save") && isWalletItemPresent.isPresent()){
             appMember_walletItem = isWalletItemPresent.get();
            appMember_walletItem.setAmount(appMember_walletItem.getAmount() + dto.getAmount());
            appMember_WalletItemRepository.save(appMember_walletItem);
        }
        else{//edit
            Optional<AppMember_WalletItem> appMember_walletItemOptional= appMember_WalletItemRepository.findById(dto.getId());
            if(appMember_walletItemOptional.isPresent()) {
                appMember_walletItem = appMember_walletItemOptional.get();
                appMember_walletItem.setApplicationMember(appMember);
                appMember_walletItem.setWalletItem(walletItem);
                appMember_walletItem.setAmount(dto.getAmount());
                appMember_walletItem.setVersion(appMember.getVersion()+1);
                appMember_walletItem.setCreated(DateUtils.getNow());
                appMember_walletItem.setUpdated(DateUtils.getNow());
                appMember_walletItem.setCreatedBy(createdBy.get());
                appMember_walletItem.setUpdatedBy(createdBy.get());
                appMember_WalletItemRepository.save(appMember_walletItem);
            }
        }
        return appMember_walletItem;

    }
    public AppMember updateAvatar(Long appMemmberId, Long avatarId) {
        Optional<BinaryContent> iconOptional = binaryContentRepository.findById(avatarId);
        BinaryContent icon= null;
        AppMember appMember=null;

        if(avatarId == null || avatarId ==0L || iconOptional.isEmpty())
            icon = binaryContentRepository.findByfileName("no_photo_avatar");
        else
            icon = binaryContentRepository.findById(avatarId).get();

        Optional<AppMember> appMemberOptional= appMemberRepository.findById(appMemmberId);

        if(appMemberOptional.isPresent()) {
            appMember = appMemberOptional.get();
            appMember.setIcon(icon);
            appMemberRepository.save(appMember);
        }

        return appMember;
    }

    public AppMember updatePassword(Long appMemmberId,String oldPassword,String newPassword) {
        Optional<AppMember> memberOptional = appMemberRepository.findById(appMemmberId);
        AppMember appMember=null;
        ResponseMessage  responseMessage = null;
        if(memberOptional == null  || memberOptional.isEmpty())
            return appMember;
        else {
            appMember = memberOptional.get();
            appMember.setPassword(newPassword);
            appMemberRepository.save(appMember);
        }
        return appMember;
    }

    public AppMember save(AppMemberDTO dto, String code) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        Language language=null;
        UserGender gender=null;
        if(dto.getLanguage()==null || dto.getLanguage().equalsIgnoreCase(""))
            language = Language.English;
        else
            language = Language.getByTitle(dto.getLanguage());
        if(language==null) language = Language.English;

        if(dto.getGender()==null || dto.getGender().equalsIgnoreCase(""))
            gender=UserGender.Male;
        else
            gender = UserGender.getByTitle(dto.getGender());
        if(gender==null) gender = UserGender.Male;

        MemberType memberType = memberTypeService.findByValue(dto.getMemberType()).get();
        BinaryContent icon=null;
        if(dto.getIconId() == null || dto.getIconId() ==0)
                icon = binaryContentRepository.findByfileName("no_photo_avatar");
        else
            icon = binaryContentRepository.findById(dto.getIconId()).get();

        byte [] hash = null;
        try {
             hash = GenerateSHA256.getSHA(dto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        String hashedPassword = GenerateSHA256.toHexString(hash) ;
        AppMember appMember=null;
        if (code.equalsIgnoreCase("Save")) { //Save
            appMember = new AppMember(dto.getAge(),language,dto.getUsername(), hashedPassword, dto.getNickname(), dto.getMobile(),dto.getEmail(),icon,gender ,memberType,dto.getEnergy()
                    ,1L, DateUtils.getNow(), DateUtils.getNow(), createdBy.get(), createdBy.get());
            appMemberRepository.save(appMember);
            appMember_LearningSkillService.initSkillWalletByUser(appMember);
        }else{//edit
            Optional<AppMember> appMemberOptional= appMemberRepository.findById(dto.getId());
            if(appMemberOptional.isPresent()) {
                appMember = appMemberOptional.get();
                appMember.setNickname(dto.getNickname());
                appMember.setUsername(dto.getUsername());
                appMember.setPassword(hashedPassword);
                appMember.setMobile(dto.getMobile());
                appMember.setIcon(icon);
                appMember.setMemberType(memberType);
                appMember.setGender(gender);
                appMember.setAge(dto.getAge());
                appMember.setVersion(appMember.getVersion()+1);
                appMember.setCreated(DateUtils.getNow());
                appMember.setUpdated(DateUtils.getNow());
                appMember.setCreatedBy(createdBy.get());
                appMember.setUpdatedBy(createdBy.get());
                appMemberRepository.save(appMember);
            }
        }
        return appMember;
    }

    public AppMember saveGuestUser(Integer bornYear) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        MemberType memberType = memberTypeService.findByValue("Guest").get();

        BinaryContent icon=null;
        AppMember guest=null;
        Integer age = DateUtils.calculateAgeFromJalali(bornYear);
        icon = binaryContentRepository.findByfileName("no_photo_avatar");
        byte [] hash = null;
        try {
            hash = GenerateSHA256.getSHA("Guest"+bornYear);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        String hashedPassword = GenerateSHA256.toHexString(hash) ;

        guest = new AppMember(age,Language.English,"Guest", hashedPassword, "Guest"+bornYear, "","",icon,UserGender.Unknow ,memberType,10
                ,1L, DateUtils.getNow(), DateUtils.getNow(), createdBy.get(), createdBy.get());
        save(guest);
        String UniqueUserName= guest.getUsername() + guest.getId();
        guest.setUsername(UniqueUserName);
        appMemberRepository.save(guest);
        return guest;
    }

    public ALCityAcessRight findRemoteUser(RemoteRequestDTO remoteAccessDTO) {
        String userName = remoteAccessDTO.getRemoteUserName();;
        Optional<AppMember> appMemberOptional = appMemberRepository.findByUsername(userName);
        if(appMemberOptional.isEmpty())
            return  new ALCityAcessRight(-1L, remoteAccessDTO.getRemoteUserName(),-1,"data not found","-1",-1,"error","error","error",-1L,"error","error");
        return  null;
    }
        public AppMember saveRemoteUser(RemoteRequestDTO accessDTO) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        MemberType memberType = memberTypeService.findByValue("Guest").get();
        BinaryContent icon=null;
        AppMember guest=null;
        Integer age = DateUtils.calculateAgeFromJalali(accessDTO.getBirthYear());
        icon = binaryContentRepository.findByfileName("no_photo_avatar");
        guest = new AppMember(age,Language.English,accessDTO.getRemoteHost() + "-" + accessDTO.getRemoteUserName(), "Guest", "Guest", "","",icon,UserGender.Unknow ,memberType,10
                ,1L, DateUtils.getNow(), DateUtils.getNow(), createdBy.get(), createdBy.get());
        appMemberRepository.save(guest);
        String UniqueUserName= guest.getUsername() + guest.getId();
        guest.setUsername(UniqueUserName);
        appMemberRepository.save(guest);
        return guest;
    }
    @Override
    public <S extends AppMember> S save(S entity) {
        //entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        return appMemberRepository.save(entity);
    }

    @Override
    public <S extends AppMember> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<AppMember> findById(Long id) {
        if(id == null) return Optional.empty();
        return appMemberRepository.findById(id);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<AppMember> findAll() {
        return appMemberRepository.findAll();
    }

    @Override
    public Iterable<AppMember> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {
        appMemberRepository.deleteById(aLong);
    }

    @Override
    public void delete(AppMember member) {
        //load and delete member client types
         Collection<ClientType>  clientTypes=member.getClientTypes();
         member.getClientTypes().removeAll(clientTypes);
        //load and delete member instances game

        //load and delete puzzleLevelScore for a member

        //load and delete AppMember_WalletItems

        //load and delete AppMember_LearningSkill

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends AppMember> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Optional<AppMember> findByUsername(String username) {
        return appMemberRepository.findByUsername(username);
    }

    @Override
    public Collection<AppMember> findByMobile(String mobile) {
        return null;
    }

    @Override
    public AppMember findByEmail(String email) {
        return null;
    }

    @Override
    public Collection<AppMember> findByUsernameContainingIgnoreCaseOrNicknameContainingIgnoreCaseOrEmailIsContainingIgnoreCase(String userName, String nickName, String email) {
        return appMemberRepository.findByUsernameContainingIgnoreCaseOrNicknameContainingIgnoreCaseOrEmailIsContainingIgnoreCase(userName,nickName,email);
    }
    public ResponseMessage setClientType(AppMember appMember, ClientType clientType) {
        Collection<ClientType> clientTypes = appMember.getClientTypes();
        clientTypes.add(clientType);
        appMember.setClientTypes(clientTypes);
        appMemberRepository.save(appMember);
        return new ResponseMessage(ErrorType.SaveSuccess, Status.ok.name(),AppMember.class.getSimpleName() ,  appMember.getId(), SystemMessage.SaveOrEditMessage_Success);

    }
    public Boolean isUserPasswordMatched(AppMember member,String username, String password) {
        byte [] hash = null;
        if(!member.getUsername().equals(username)) return false;
        try {
            hash = GenerateSHA256.getSHA(password);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        String hashedPassword = GenerateSHA256.toHexString(hash) ;

        if(!hashedPassword.equals(member.getPassword())) return false;
        return true;
    }
    public ResponseObject login(String username, String password) {
        return null;
    }
}
