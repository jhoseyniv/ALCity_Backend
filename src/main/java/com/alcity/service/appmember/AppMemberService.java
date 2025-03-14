package com.alcity.service.appmember;

import com.alcity.dto.RemoteAccess.RemoteAccessDTO;
import com.alcity.dto.appmember.*;
import com.alcity.dto.journey.RoadMapDTO;
import com.alcity.dto.player.PlayHistoryDTO;
import com.alcity.dto.puzzle.PLDTO;
import com.alcity.dto.puzzle.PuzzleLevelStepMappingDTO;
import com.alcity.entity.appmember.AppMemberJourneyInfo;
import com.alcity.entity.appmember.AppMemberStepInfo;
import com.alcity.entity.journey.Journey;
import com.alcity.entity.journey.JourneyStep;
import com.alcity.entity.play.PlayHistory;
import com.alcity.entity.puzzle.PuzzleLevel;
import com.alcity.service.customexception.ALCityAcessRight;
import com.alcity.service.customexception.ALCityResponseObject;
import com.alcity.entity.alenum.UserGender;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.appmember.AppMember_WalletItem;
import com.alcity.entity.appmember.WalletItem;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.base.MemberType;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.repository.appmember.AppMember_WalletItemRepository;
import com.alcity.repository.appmember.CustomizedUserRepository;
import com.alcity.repository.appmember.WalletItemRespository;
import com.alcity.repository.base.BinaryContentRepository;
import com.alcity.repository.base.MemberTypeRepository;
import com.alcity.service.puzzle.PuzzleLevelService;
import com.alcity.utility.DTOUtil;
import com.alcity.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AppMemberService implements AppMemberRepository, CustomizedUserRepository {

    @Autowired
    private AppMemberRepository appMemberRepository;
    @Autowired
    private BinaryContentRepository binaryContentRepository;
    @Autowired
    private MemberTypeRepository memberTypeRepository;

    @Autowired
    private AppMember_WalletItemRepository appMember_WalletItemRepository;

    @Autowired
    private WalletItemRespository walletItemRespository;

    @Autowired
    private PuzzleLevelService puzzleLevelService;

/*
    public Collection<AppMemberJourneyStepDTO>  getJourneyStepsScore(Collection<PlayHistory> histories, Journey journey){
        Collection<AppMemberJourneyStepDTO> dtos = new ArrayList<>();
        Iterator<PlayHistory> itr = histories.iterator();
        while(itr.hasNext()){
            PlayHistory playHistory = itr.next();
            JourneyStep journeyStep = puzzleLevelService.getJourneyStepIdMappedWithPuzzleLevel(journey,playHistory.getPuzzleLevel());
            if(journeyStep.getJourney().getId() == journey.getId()) {
                AppMemberJourneyStepDTO dto = new AppMemberJourneyStepDTO();
                dto.setStepId(journeyStep.getId());
                dto.setXpos(journeyStep.getXpos());
                dto.setYpos(journeyStep.getYpos());
                dto.setPuzzleLevelId(playHistory.getPuzzleLevel().getId());
                dto.setStars(playHistory.getStars());
                dto.setCompleted(Boolean.TRUE);
                dtos.add(dto);
            }
        }
        return dtos;
    }

    /*
    public AppMemberJourneyDetailDTO getAppMemberJourneyDetailByScores(AppMember member, Journey journey) {
        Integer age= member.getAge();
        Collection<PlayHistory> histories = member.getPlayHistories();
        Collection<PlayHistoryDTO> playHistoryDTOS = DTOUtil.getPlayHistoryDTOS(histories);
        Collection<PlayHistoryDTO> filtredPlayHistoryDTOS = playHistoryDTOS.stream().filter(PlayHistoryDTO -> PlayHistoryDTO.getPlFromAge() <= age && age <= PlayHistoryDTO.getPlToAge()).collect(Collectors.toList());
        AppMemberJourneyDetailDTO journeyDetailDTO = new AppMemberJourneyDetailDTO();
        Collection<JourneyStepDTO> journeyStepDTOS = DTOUtil.getJorneyStepsDTOS(journey.getJourneyStepCollection());
        journeyDetailDTO = DTOUtil.getAppMemberJourneyDetailDTO(filtredPlayHistoryDTOS,journeyStepDTOS);
        Collection<RoadMapDTO> roadMapDTOS = DTOUtil.getJourneyRoadMapsDTOS(journey.getRoadMaps());
        journeyDetailDTO.setRoadMapDTOS(roadMapDTOS);
        journeyDetailDTO.setId(journey.getId());
        journeyDetailDTO.setTitle(journey.getTitle());
        return  journeyDetailDTO;
    }
*/
    public  Collection<PLDTO> getPublicPuzzleLevels(AppMember appMember){
        Collection<PLDTO>  pldtos= new ArrayList<PLDTO>();
        Collection<PuzzleLevel> puzzleLevels = puzzleLevelService.getPublicPuzzleLevelByAppMember(appMember);
        pldtos =DTOUtil.getPuzzleLevelDTOS(puzzleLevels);
        return pldtos;
    }

    public  Collection<PLDTO> getPuzzleLevelsNotPlayed(Collection<PLDTO> puzzles,Collection<PLDTO> played){
        Collection<PLDTO>  notPlayed = puzzles.stream().filter(pldto -> played.contains(pldto)).collect(Collectors.toList());
        return notPlayed;
    }
    public  Collection<PLDTO> getPuzzleLevelsPlayed(AppMember appMember){
        Collection<PLDTO>  pldtos= new ArrayList<PLDTO>();
        Collection<PlayHistory> histories = appMember.getPlayHistories();
        pldtos =DTOUtil.getPlayedPuzzlesByAppMemberDTOS(histories);

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
            JourneyStep journeyStep = puzzleLevelService.getPuzzleLevelMappedStep(pldto.getId());
            if(journeyStep.getJourney().getId() == journey.getId()) {
                AppMemberStepInfo dto = new AppMemberStepInfo(journeyStep.getId(), journeyStep.getTitle(),journeyStep.getXpos(),journeyStep.getXpos(),
                        pldto.getId(), pldto.getTitle(), pldto.getPuzzleGroupId(), pldto.getPuzzleGroupTitle(),0,Boolean.FALSE);
                stepInfos.add(dto);
            }
        }
        journeyInfo.setSteps(stepInfos);
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

        Collection<PlayHistory>  histories= member.getPlayHistories();
        Collection<PlayHistoryDTO> playedPuzzles = DTOUtil.getPlayHistoryDTOS(histories);
        Iterator<PlayHistoryDTO> itr = playedPuzzles.iterator();
        Collection<AppMemberStepInfo> stepInfos = journeyInfo.getSteps();

        while (itr.hasNext()){
            PlayHistoryDTO historyDTO = itr.next();
            Optional<AppMemberStepInfo> stepInfoOptional = stepInfos.stream().filter(AppMemberStepInfo -> AppMemberStepInfo.getPuzzleLevelId() == historyDTO.getPlId()).findFirst();
            if(stepInfoOptional.isPresent()){
                AppMemberStepInfo stepInfo =stepInfoOptional.get();
                stepInfo.setCompleted(Boolean.TRUE);
                stepInfo.setStars(historyDTO.getStars());
            }

        }
        journeyInfoWithScores.setSteps(stepInfos);
        return journeyInfoWithScores;
    }

    public AppMemberJourneyDTO getJourneyScoresForAppMember(AppMember member, Journey journey) {
        Collection<PlayHistory> histories = member.getPlayHistories();
        Collection<PuzzleLevelStepMappingDTO> mappingDTOS = new ArrayList<>();
        AppMemberJourneyDTO dto = new AppMemberJourneyDTO();
        Integer currentStar=0;
        Iterator<PlayHistory> itr = histories.iterator();
        dto.setTitle(journey.getTitle());
        dto.setAppMemberId(member.getId());
        dto.setOpen(Boolean.FALSE);
        dto.setMinToOpenStar(journey.getMinToOpenStar());
        dto.setMinToPassStar(journey.getMinToPassStar());
        dto.setJourneyId(journey.getId());
        while(itr.hasNext()) {
            PlayHistory playHistory = itr.next();
            Long journeyId = puzzleLevelService.getJourneyIdMappedWithPuzzleLevel(playHistory.getPuzzleLevel());
            if(journeyId == journey.getId()) {
                currentStar += playHistory.getStars();
            }
        }
        dto.setCurrentStar(currentStar);
        if(currentStar >= journey.getMinToOpenStar())
            dto.setOpen(Boolean.TRUE);
    return  dto;
    }


/*
    public AppMemberJourneyDetailDTO getAppMemberJourneyByScore(AppMember member, Journey journey) {
        AppMemberJourneyDetailDTO dto = new AppMemberJourneyDetailDTO();
        Collection<JourneyStep> steps = journey.getJourneyStepCollection();
        Collection<PlayHistory>  histories= member.getPlayHistories();
        Collection<PlayHistoryDTO> historyDTOS = DTOUtil.getPlayHistoryDTOS(histories);

        return  dto;
    }


 */
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
    public AppMember save(AppMemberDTO dto, String code) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        MemberType memberType = memberTypeRepository.findByValue(dto.getMemberType()).get();
        UserGender gender = UserGender.getByTitle(dto.getGender());
        BinaryContent icon=null;
        if(dto.getIconId() == null || dto.getIconId() ==0)
                icon = binaryContentRepository.findByfileName("no_photo_avatar");
        else
            icon = binaryContentRepository.findById(dto.getIconId()).get();

        AppMember appMember=null;
        if (code.equalsIgnoreCase("Save")) { //Save
            appMember = new AppMember(dto.getAge(),dto.getUsername(), dto.getPassword(), dto.getNickname(), dto.getMobile(),dto.getEmail(),icon,gender ,memberType
                    ,1L, DateUtils.getNow(), DateUtils.getNow(), createdBy.get(), createdBy.get());
            appMemberRepository.save(appMember);
        }else{//edit
            Optional<AppMember> appMemberOptional= appMemberRepository.findById(dto.getId());
            if(appMemberOptional.isPresent()) {
                appMember = appMemberOptional.get();
                appMember.setNickname(dto.getNickname());
                appMember.setUsername(dto.getUsername());
                appMember.setPassword(dto.getPassword());
                appMember.setMobile(dto.getMobile());
                appMember.setIcon(icon);
                appMember.setMemberType(memberType);
                appMember.setGender(gender);
                appMember.setAge(appMember.getAge());
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
        MemberType memberType = memberTypeRepository.findByValue("Guest").get();
        BinaryContent icon=null;
        AppMember guest=null;
        Integer age = DateUtils.calculateAgeFromJalali(bornYear);
        icon = binaryContentRepository.findByfileName("no_photo_avatar");
        guest = new AppMember(age,"Guest", "Guest", "Guest", "","",icon,UserGender.Unknow ,memberType
                ,1L, DateUtils.getNow(), DateUtils.getNow(), createdBy.get(), createdBy.get());
        appMemberRepository.save(guest);
        String UniqueUserName= guest.getUsername() + guest.getId();
        guest.setUsername(UniqueUserName);
        appMemberRepository.save(guest);
        return guest;
    }

    public ALCityAcessRight findRemoteUser(RemoteAccessDTO remoteAccessDTO) {
        String userName = remoteAccessDTO.remoteUserName;;
        Optional<AppMember> appMemberOptional = appMemberRepository.findByUsername(userName);
        if(appMemberOptional.isEmpty())
            return  new ALCityAcessRight(-1L, remoteAccessDTO.getRemoteUserName(),-1,"data not found","-1",-1,"error","error","error",-1L,"error","error");
        return  null;
    }
        public AppMember saveRemoteUser(RemoteAccessDTO accessDTO) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        MemberType memberType = memberTypeRepository.findByValue("Guest").get();
        BinaryContent icon=null;
        AppMember guest=null;
        Integer age = DateUtils.calculateAgeFromJalali(accessDTO.birthYear);
        icon = binaryContentRepository.findByfileName("no_photo_avatar");
        guest = new AppMember(age,accessDTO.getRemoteHost() + "-" + accessDTO.getRemoteUserName(), "Guest", "Guest", "","",icon,UserGender.Unknow ,memberType
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
    public void delete(AppMember entity) {

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

    public ALCityResponseObject login(String username, String password) {
        return null;
    }
}
