package com.alcity.service.puzzle;

import com.alcity.dto.puzzle.PGDTO;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.base.PuzzleCategory;
import com.alcity.entity.puzzle.PuzzleGroup;
import com.alcity.entity.appmember.AppMember;
import com.alcity.repository.base.BinaryContentRepository;
import com.alcity.repository.base.PuzzleCategoryRepository;
import com.alcity.repository.puzzle.PGRepository;
import com.alcity.repository.appmember.AppMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class PGService implements PGRepository {

    @Qualifier("PGRepository")
    @Autowired
    PGRepository pgRepository;
    @Override
    public <S extends PuzzleGroup> S save(S entity) {
        return pgRepository.save(entity);
    }

    @Override
    public <S extends PuzzleGroup> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PuzzleGroup> findById(Long id) {
        return pgRepository.findById(id);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<PuzzleGroup> findAll() {
        return pgRepository.findAll();
    }

    @Override
    public Iterable<PuzzleGroup> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {
        pgRepository.deleteById(aLong);
    }
    @Autowired
    private AppMemberRepository appMemberRepository;

    @Autowired
    private BinaryContentRepository binaryContentRepository;
    @Autowired
    private PuzzleCategoryRepository puzzleCategoryRepository;

    public PuzzleGroup save(PGDTO dto, String code) {
        AppMember createdBy = appMemberRepository.findByUsername("admin");
        PuzzleGroup puzzleGroup=null;
        Optional<BinaryContent> icon = binaryContentRepository.findById(dto.getIconId());
        Optional<BinaryContent> pic = binaryContentRepository.findById(dto.getPicId());
        Optional<PuzzleCategory>  puzzleCategory = puzzleCategoryRepository.findById(dto.getPuzzleCategoryId());
        if (code.equalsIgnoreCase("Save")) { // save
            puzzleGroup = new PuzzleGroup(dto.getTitle(),puzzleCategory.get(),icon.get(),pic.get(), 1L, "1714379790", "1714379790", createdBy, createdBy);
            pgRepository.save(puzzleGroup);
        }else{//edit
            Optional<PuzzleGroup> puzzleGroupOptional= pgRepository.findById(dto.getId());
            if(puzzleGroupOptional.isPresent()) {
                puzzleGroup = puzzleGroupOptional.get();
                puzzleGroup.setTitle(dto.getTitle());
                puzzleGroup.setIcon(icon.get());
                puzzleGroup.setPic(pic.get());
                puzzleGroup.setPuzzleCategory(puzzleCategory.get());
                puzzleGroup.setVersion(puzzleGroup.getVersion()+1);
                pgRepository.save(puzzleGroup);
            }
        }
        return puzzleGroup;
    }

    @Override
    public void delete(PuzzleGroup entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PuzzleGroup> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public  Optional<PuzzleGroup> findByTitle(String title) {
        return pgRepository.findByTitle(title);
    }

    @Override
    public Optional<PuzzleGroup> findByIcon(BinaryContent icon) {
        return pgRepository.findByIcon(icon);
    }

    @Override
    public Optional<PuzzleGroup> findByPic(BinaryContent pic) {
        return pgRepository.findByPic(pic);
    }


}
