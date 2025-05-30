package com.alcity.service.base;

import com.alcity.dto.base.BinaryContentDTO;
import com.alcity.dto.search.ContentSearchCriteriaDTO;
import com.alcity.entity.alenum.BinaryContentType;
import com.alcity.entity.appmember.WalletItem;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.learning.LearningContent;
import com.alcity.entity.puzzle.ALCityObject;
import com.alcity.entity.puzzle.PuzzleGroup;
import com.alcity.entity.puzzle.PuzzleLevel;
import com.alcity.entity.appmember.AppMember;
import com.alcity.repository.appmember.WalletItemRespository;
import com.alcity.repository.base.BinaryContentCustom;
import com.alcity.repository.base.BinaryContentRepository;
import com.alcity.repository.puzzle.ObjectRepository;
import com.alcity.repository.puzzle.PuzzleLevelRepository;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.service.learning.LearningContentService;
import com.alcity.service.puzzle.PGService;
import com.alcity.utility.DateUtils;
import com.alcity.utility.ImageUtil;
import com.alcity.utility.SlicedStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
;
import java.io.IOException;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@Transactional
public class BinaryContentService implements BinaryContentRepository , BinaryContentCustom {

    @Autowired
    BinaryContentRepository binaryContentRepository;
    @Autowired
    private PGService pgServie;
    @Autowired
    private LearningContentService learningContentService;
    @Autowired
    private PuzzleLevelRepository puzzleLevelRepository;

    @Autowired
    private ObjectRepository objectRepository;

    @Autowired
    @Qualifier("walletItemRespository")
    private WalletItemRespository walletItemRespository;

    @Override
    public <S extends BinaryContent> S save(S entity) {
        return binaryContentRepository.save(entity);
    }

    @Override
    public <S extends BinaryContent> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<BinaryContent> findById(Long id) {
        return binaryContentRepository.findById(id);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<BinaryContent> findAll() {
        return null;
    }

    @Override
    public Iterable<BinaryContent> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {
        binaryContentRepository.deleteById(aLong);
    }

    @Override
    public void delete(BinaryContent entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends BinaryContent> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public BinaryContent findByfileName(String fileName) {
        return binaryContentRepository.findByfileName(fileName);
    }

    @Override
    public Collection<BinaryContent> findBySize(Integer size) {
        return null;
    }

    @Override
    public Collection<BinaryContent> findByFileNameContainsIgnoreCaseOrTag1ContainsIgnoreCaseOrTag2ContainsIgnoreCaseOrTag3ContainsIgnoreCase(String fileName,String tag1, String tag2, String tag3) {
        return binaryContentRepository.findByFileNameContainsIgnoreCaseOrTag1ContainsIgnoreCaseOrTag2ContainsIgnoreCaseOrTag3ContainsIgnoreCase(fileName,tag1,tag2,tag3);
    }


    public Collection<BinaryContent> findByCriteria(ContentSearchCriteriaDTO dto) {
        BinaryContentType contentType =BinaryContentType.getById(dto.getContentTypeId());
        Collection<BinaryContent> binaryContents = binaryContentRepository.findByFileNameContainsIgnoreCaseOrTag1ContainsIgnoreCaseOrTag2ContainsIgnoreCaseOrTag3ContainsIgnoreCase(dto.getCriteria(), dto.getCriteria(), dto.getCriteria(), dto.getCriteria());
        Collection<BinaryContent> matchValues = binaryContents.stream().filter(binaryContent ->  binaryContent.getContentType().equals(contentType)).toList();

        Stream<BinaryContent> binaryContentStream = matchValues.stream();
        if(dto.getLastIndex() < 0L ) dto.setLastIndex(0L);
        if(dto.getPageSize() <= 0 ) dto.setPageSize(1);
        Collection<BinaryContent> page = SlicedStream.getSliceOfStream(binaryContentStream,dto.getLastIndex() ,dto.getLastIndex()  + dto.getPageSize() -1 ).toList();

        return page;

    }

    @Autowired
    private AppMemberRepository appMemberRepository;


//    @Override
//    public BinaryContent save(String fileName, MultipartFile uploadedFile) throws IOException {
//        LocalDateTime current = LocalDateTime.now();
//        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//        String now = current.format(format);
//        AppMember createdBy = appMemberRepository.findByUsername("admin");
//        BinaryContentType binaryContentType = ImageUtil.getBinaryContentType(uploadedFile.getContentType());
//        File file= new File("src/main/resources/images/image-utility/",uploadedFile.getName());
//        uploadedFile.transferTo(file);
//        byte[] tubmnile = ImageUtil.getThumbnail(file);
//        BinaryContent binaryContent = new BinaryContent(fileName,uploadedFile.getSize(),uploadedFile.getBytes(),tubmnile ,
//                ,uploadedFile., binaryContentType,1L,now,now,createdBy,createdBy);
//        binaryContentRepository.save(binaryContent);
//
//        return binaryContent;
//    }

    @Override
    public void removeForeignKeys(Long id) throws IOException {
        Optional<BinaryContent> binaryContentOptional = binaryContentRepository.findById(id);
        if(binaryContentOptional.isEmpty()) return;

        Optional<PuzzleGroup> puzzleGroupOptional = pgServie.findByIcon(binaryContentOptional.get());
        if(puzzleGroupOptional.isPresent()){
            PuzzleGroup puzzleGroup = puzzleGroupOptional.get();
            puzzleGroup.setIcon(null);
            pgServie.save(puzzleGroup);
        }
        puzzleGroupOptional = pgServie.findByPic(binaryContentOptional.get());
        if(puzzleGroupOptional.isPresent()){
            PuzzleGroup puzzleGroup = puzzleGroupOptional.get();
            puzzleGroup.setPic(null);
            pgServie.save(puzzleGroup);
        }
        Optional<LearningContent> learningContentOptional = learningContentService.findByBinaryContent(binaryContentOptional.get());
        if(learningContentOptional.isPresent()){
            LearningContent learningContent = learningContentOptional.get();
            learningContent.setBinaryContent(null);
            learningContentService.save(learningContent);
        }
        Optional<PuzzleLevel> puzzleLevelOptional = puzzleLevelRepository.findByIcon(binaryContentOptional.get());
        if(puzzleLevelOptional.isPresent()){
            PuzzleLevel puzzleLevel = puzzleLevelOptional.get();
            puzzleLevel.setIcon(null);
            puzzleLevelRepository.save(puzzleLevel);
        }
        puzzleLevelOptional = puzzleLevelRepository.findByPicture(binaryContentOptional.get());
        if(puzzleLevelOptional.isPresent()){
            PuzzleLevel puzzleLevel = puzzleLevelOptional.get();
            puzzleLevel.setPicture(null);
            puzzleLevelRepository.save(puzzleLevel);
        }
        Optional<ALCityObject> alCityObjectOptional = objectRepository.findByIcon(binaryContentOptional.get());
        if(alCityObjectOptional.isPresent()){
            ALCityObject alCityObject = alCityObjectOptional.get();
            alCityObject.setIcon(null);
            objectRepository.save(alCityObject);
        }
        alCityObjectOptional = objectRepository.findByPic(binaryContentOptional.get());
        if(alCityObjectOptional.isPresent()){
            ALCityObject alCityObject = alCityObjectOptional.get();
            alCityObject.setPic(null);
            objectRepository.save(alCityObject);
        }

        Optional<WalletItem> walletItemRespositoryOptional = walletItemRespository.findByIcon(binaryContentOptional.get());
        if(walletItemRespositoryOptional.isPresent()){
            WalletItem walletItem = walletItemRespositoryOptional.get();
            walletItem.setIcon(null);
            walletItemRespository.save(walletItem);
        }

    }

    public BinaryContent save(BinaryContentDTO dto, String code) throws IOException {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");

        BinaryContent binaryContent=null;
        if (code.equalsIgnoreCase("Save")) { // save
            byte[]  tumb = ImageUtil.getThumbnail(dto.getContent(),dto.getFileName());
            binaryContent = new BinaryContent(1L, DateUtils.getNow(), DateUtils.getNow(),createdBy.get() , createdBy.get(),dto.getFileName(),
                    dto.getSize(), dto.getContent(), tumb,dto.getIs3dContent(),
                    dto.getIos3Dcontent(), dto.getAndriod3Dcontent(), dto.getWeb3Dcontent(),
                    dto.getTag1(), dto.getTag2(), dto.getTag3(), BinaryContentType.getByTitle(dto.getContentType()));
            binaryContentRepository.save(binaryContent);
        }else{//edit
            Optional<BinaryContent> binaryContentOptional= binaryContentRepository.findById(dto.getId());
            byte[]  tumb = ImageUtil.getThumbnail(dto.getContent(),dto.getFileName());
            if(binaryContentOptional.isPresent()) {
                binaryContent = binaryContentOptional.get();
                binaryContent.setContentType(BinaryContentType.getByTitle(dto.getContentType()));
                binaryContent.setFileName(dto.getFileName());
                binaryContent.setTag1(dto.getTag1());
                binaryContent.setTag2(dto.getTag2());
                binaryContent.setTag3(dto.getTag3());
                binaryContent.setIs3dContent(dto.getIs3dContent());
                binaryContent.setThumbnail(tumb);

                if(dto.getIos3Dcontent().length > 0 && dto.getIos3Dcontent()!=null )
                     binaryContent.setIos3Dcontent(dto.getIos3Dcontent());

                if(dto.getAndriod3Dcontent().length > 0 && dto.getAndriod3Dcontent() !=null)
                     binaryContent.setAndriod3Dcontent(dto.getAndriod3Dcontent());

                if(dto.getWeb3Dcontent().length > 0  && dto.getWeb3Dcontent() !=null)
                    binaryContent.setWeb3Dcontent(dto.getWeb3Dcontent());

                binaryContent.setFileName(dto.getFileName());
                binaryContent.setVersion(binaryContent.getVersion()+1);
                binaryContentRepository.save(binaryContent);
            }
        }
        return binaryContent;
    }

}
