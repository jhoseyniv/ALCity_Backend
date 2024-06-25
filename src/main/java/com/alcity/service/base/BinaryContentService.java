package com.alcity.service.base;

import com.alcity.customexception.ALCityResponseObject;
import com.alcity.dto.base.BinaryContentDTO;
import com.alcity.dto.puzzle.PuzzleCategoryDTO;
import com.alcity.entity.alenum.BinaryContentType;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.base.PuzzleCategory;
import com.alcity.entity.learning.LearningContent;
import com.alcity.entity.puzzle.PuzzleGroup;
import com.alcity.entity.puzzle.PuzzleLevel;
import com.alcity.entity.users.ApplicationMember;
import com.alcity.repository.base.BinaryContentCustom;
import com.alcity.repository.base.BinaryContentRepository;
import com.alcity.repository.puzzle.PGRepository;
import com.alcity.repository.puzzle.PuzzleLevelRepository;
import com.alcity.repository.users.ApplicationMemberRepository;
import com.alcity.repository.users.CustomizedUserRepository;
import com.alcity.service.learning.LearningContentService;
import com.alcity.service.puzzle.PGService;
import com.alcity.service.puzzle.PuzzleLevelService;
import com.alcity.utility.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Optional;

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

    @Autowired
    private ApplicationMemberRepository applicationMemberRepository;


    @Override
    public BinaryContent save(String fileName, MultipartFile file) throws IOException {
        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String now = current.format(format);
        ApplicationMember createdBy = applicationMemberRepository.findByUsername("admin");
        BinaryContentType binaryContentType = ImageUtil.getBinaryContentType(file.getContentType());
        BinaryContent binaryContent = new BinaryContent(fileName,file.getBytes(), binaryContentType,1L,now,now,createdBy,createdBy);
        binaryContentRepository.save(binaryContent);

        return binaryContent;
    }

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

    }

    public BinaryContent save(BinaryContentDTO dto, String code) {
        ApplicationMember createdBy = applicationMemberRepository.findByUsername("admin");
        BinaryContent binaryContent=null;
        if (code.equalsIgnoreCase("Save")) { // save
            binaryContent = new BinaryContent(dto.getFileName(), dto.getContent(),
                    BinaryContentType.getByTitle(dto.getContentType()) ,
                    1L, "1714379790", "1714379790",createdBy, createdBy);
            binaryContentRepository.save(binaryContent);
        }else{//edit
            Optional<BinaryContent> binaryContentOptional= binaryContentRepository.findById(dto.getId());
            if(binaryContentOptional.isPresent()) {
                binaryContent = binaryContentOptional.get();
                binaryContent.setContentType(BinaryContentType.getByTitle(dto.getContentType()));
                binaryContent.setFileName(dto.getFileName());
                binaryContent.setVersion(binaryContent.getVersion()+1);
                binaryContentRepository.save(binaryContent);
            }
        }
        return binaryContent;
    }

}
