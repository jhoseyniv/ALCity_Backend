package com.alcity.service.base;


import com.alcity.dto.puzzle.PuzzleCategoryDTO;
import com.alcity.entity.base.PuzzleCategory;
import com.alcity.entity.appmember.AppMember;
import com.alcity.repository.base.PuzzleCategoryRepository;
import com.alcity.repository.appmember.AppMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class PuzzleCategoryService implements PuzzleCategoryRepository {

    @Autowired
    private PuzzleCategoryRepository puzzleCategoryRepository;
    @Autowired
    private AppMemberRepository appMemberRepository;

    public PuzzleCategory save(PuzzleCategoryDTO dto,String code) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        PuzzleCategory puzzleCategory=null;
        if (code.equalsIgnoreCase("Save")) { // save
            puzzleCategory = new PuzzleCategory(dto.getLabel(), dto.getValue(), 1L, "1714379790", "1714379790", createdBy.get(), createdBy.get());
            puzzleCategoryRepository.save(puzzleCategory);
         }else{//edit
            Optional<PuzzleCategory> puzzleCategoryOptional= puzzleCategoryRepository.findById(dto.getId());
            if(puzzleCategoryOptional.isPresent()) {
                puzzleCategory = puzzleCategoryOptional.get();
                puzzleCategory.setLabel(dto.getLabel());
                puzzleCategory.setValue(dto.getValue());
                puzzleCategory.setVersion(puzzleCategory.getVersion()+1);
                puzzleCategoryRepository.save(puzzleCategory);
            }
        }
        return puzzleCategory;
    }

    @Override
    public <S extends PuzzleCategory> S save(S entity) {
        return puzzleCategoryRepository.save(entity);
    }

    @Override
    public <S extends PuzzleCategory> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PuzzleCategory> findById(Long id) {
        return puzzleCategoryRepository.findById(id);
    }

    @Override
    public Collection<PuzzleCategory> findAll(Specification specification) {
        return puzzleCategoryRepository.findAll(specification);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<PuzzleCategory> findAll() {
        return puzzleCategoryRepository.findAll();
    }

    @Override
    public Iterable<PuzzleCategory> findAllById(Iterable<Long> longs) {
        return null;
    }


    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {
        puzzleCategoryRepository.deleteById(aLong);
    }

    @Override
    public void delete(PuzzleCategory entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PuzzleCategory> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public PuzzleCategory findByLabel(String label) {
        return puzzleCategoryRepository.findByLabel(label);
    }

    @Override
    public PuzzleCategory findByValue(String value) {
        return puzzleCategoryRepository.findByValue(value);
    }

    @Override
    public Collection<PuzzleCategory> findByValueContains(String criteria) {
        return puzzleCategoryRepository.findByValueContains(criteria);
    }


}
