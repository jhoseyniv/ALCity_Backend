package com.alcity.service.base;

import com.alcity.dto.base.BinaryContentDTO;
import com.alcity.dto.search.ContentSearchCriteriaDTO;
import com.alcity.entity.alenum.BinaryContentType;
import com.alcity.entity.appmember.WalletItem;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.learning.LearningContent;
import com.alcity.entity.puzzle.BaseObject;
import com.alcity.entity.puzzle.PuzzleGroup;
import com.alcity.entity.puzzle.PuzzleLevel;
import com.alcity.entity.appmember.AppMember;
import com.alcity.repository.appmember.WalletItemRespository;
import com.alcity.repository.base.BinaryContentCustom;
import com.alcity.repository.base.BinaryContentRepository;
import com.alcity.repository.puzzle.BaseObjectRepository;
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
    private BaseObjectRepository baseObjectRepository;

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
        if(id == null){return Optional.empty();}
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
    public Collection<BinaryContent> findByFileNameContainsIgnoreCaseOrTag1ContainsIgnoreCaseOrTag2ContainsIgnoreCaseOrTag3ContainsIgnoreCase(String fileName, String tag1, String tag2, String tag3) {
        return binaryContentRepository.findByFileNameContainsIgnoreCaseOrTag1ContainsIgnoreCaseOrTag2ContainsIgnoreCaseOrTag3ContainsIgnoreCase(fileName, tag1, tag2, tag3);
    }

    public byte[] getFile(Long id){
       Optional<BinaryContent> binaryContentOptional = binaryContentRepository.findById(id);
       if(binaryContentOptional.isPresent())
           return binaryContentOptional.get().getContent();
       return null;
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
        Optional<BaseObject> alCityObjectOptional = baseObjectRepository.findByIcon(binaryContentOptional.get());
        if(alCityObjectOptional.isPresent()){
            BaseObject alCityObject = alCityObjectOptional.get();
            alCityObject.setIcon(null);
            baseObjectRepository.save(alCityObject);
        }
        alCityObjectOptional = baseObjectRepository.findByPic(binaryContentOptional.get());
        if(alCityObjectOptional.isPresent()){
            BaseObject alCityObject = alCityObjectOptional.get();
            alCityObject.setPic(null);
            baseObjectRepository.save(alCityObject);
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
                    dto.getIos3Dcontent(), dto.getAndroid3Dcontent(), dto.getWeb3Dcontent(),
                    dto.getTag1(), dto.getTag2(), dto.getTag3(), BinaryContentType.getByTitle(dto.getContentType()));
            binaryContentRepository.save(binaryContent);
        }else{//edit
            Optional<BinaryContent> binaryContentOptional= binaryContentRepository.findById(dto.getId());
            if(dto.getContent()!=null) {
                byte[] tumb = ImageUtil.getThumbnail(dto.getContent(), dto.getFileName());
                binaryContent.setThumbnail(tumb);
                binaryContent.setContent(dto.getContent());
            }
            if(binaryContentOptional.isPresent()) {
                binaryContent = binaryContentOptional.get();
                binaryContent.setContentType(BinaryContentType.getByTitle(dto.getContentType()));
                binaryContent.setFileName(dto.getFileName());
                binaryContent.setTag1(dto.getTag1());
                binaryContent.setTag2(dto.getTag2());
                binaryContent.setTag3(dto.getTag3());
                binaryContent.setIs3dContent(dto.getIs3dContent());

                if(dto.getIos3Dcontent()!=null) {
                    binaryContent.setIos3Dcontent(dto.getIos3Dcontent());
                } else if(dto.getAndroid3Dcontent()!=null) {
                    binaryContent.setAndriod3Dcontent(dto.getAndroid3Dcontent());
                } else if(dto.getWeb3Dcontent()!=null) {
                    binaryContent.setWeb3Dcontent(dto.getWeb3Dcontent());
                } else if(dto.getContent()!=null) {
                    binaryContent.setContent(dto.getContent());
                }
                binaryContent.setFileName(dto.getFileName());
                binaryContent.setVersion(binaryContent.getVersion()+1);
                binaryContentRepository.save(binaryContent);
            }
        }
        return binaryContent;
    }

}
