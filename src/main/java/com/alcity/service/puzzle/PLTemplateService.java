package com.alcity.service.puzzle;

import com.alcity.dto.puzzle.PLDTO;
import com.alcity.dto.puzzle.PLTemplateDTO;
import com.alcity.entity.alenum.PLDifficulty;
import com.alcity.entity.alenum.PLStatus;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.base.PLPrivacy;
import com.alcity.entity.base.PuzzleCategory;
import com.alcity.entity.puzzle.PLTemplate;
import com.alcity.entity.puzzle.PuzzleGroup;
import com.alcity.entity.puzzle.PuzzleLevel;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.repository.base.PuzzleCategoryRepository;
import com.alcity.repository.puzzle.PGRepository;
import com.alcity.repository.puzzle.PLRuleRepository;
import com.alcity.repository.puzzle.PLTemplateRepository;
import com.alcity.repository.puzzle.PuzzleLevelRepository;
import com.alcity.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class PLTemplateService  implements PLTemplateRepository {

    @Qualifier("PLTemplateRepository")
    @Autowired
    PLTemplateRepository templateRepository;
    @Autowired
    private AppMemberRepository appMemberRepository;
    @Autowired
    private PuzzleCategoryRepository puzzleCategoryRepository;

    @Qualifier("PGRepository")
    @Autowired
    private PGRepository pgRepository;

    @Autowired
    private PuzzleLevelRepository puzzleLevelRepository;

    @Override
    public <S extends PLTemplate> S save(S entity) {
        return null;
    }

    public PLTemplate saveNew(PLTemplateDTO dto) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        Optional<PuzzleCategory> puzzleCategoryOptional = puzzleCategoryRepository.findById(dto.getPuzzleCategoryId());
        PLTemplate template=null;

            template = new PLTemplate(dto.getTitle(),dto.getFromAge(),dto.getToAge(),puzzleCategoryOptional.get(),
                    dto.getPuzzleGroupId(), dto.getPuzzleLevelId(),dto.getContent() ,
                    1L, DateUtils.getNow(), DateUtils.getNow(), createdBy.get(), createdBy.get());
            templateRepository.save(template);

        return template;
    }
    public PLTemplate editContent(PLTemplateDTO dto) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        Optional<PuzzleCategory> puzzleCategoryOptional = puzzleCategoryRepository.findById(dto.getPuzzleCategoryId());
        PLTemplate template=null;
        Optional<PLTemplate> plTemplateOptional= templateRepository.findById(dto.getId());
            if(plTemplateOptional.isPresent()) {
                template = plTemplateOptional.get();
                template.setContent(dto.getContent());
                template.setUpdated(DateUtils.getNow());
                template.setVersion(template.getVersion()+1);
                templateRepository.save(template);
            }
         return template;
    }
    public PLTemplate noEditContent(PLTemplateDTO dto) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        Optional<PuzzleCategory> puzzleCategoryOptional = puzzleCategoryRepository.findById(dto.getPuzzleCategoryId());
        PLTemplate template=null;
        Optional<PLTemplate> plTemplateOptional= templateRepository.findById(dto.getId());
        if(plTemplateOptional.isPresent()) {
            template = plTemplateOptional.get();
            template.setPuzzleCategory(puzzleCategoryOptional.get());
            template.setTitle(dto.getTitle());
            template.setFromAge(dto.getFromAge());
            template.setToAge(dto.getToAge());
            template.setUpdated(DateUtils.getNow());
            template.setVersion(template.getVersion()+1);
            templateRepository.save(template);
        }
        return template;
    }
    @Override
    public <S extends PLTemplate> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PLTemplate> findById(Long id) {
        return templateRepository.findById(id);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<PLTemplate> findAll() {
        return templateRepository.findAll();
    }

    @Override
    public Iterable<PLTemplate> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(PLTemplate entity) {
        templateRepository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PLTemplate> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Collection<PLTemplate> findByTitle(String title) {
        return null;
    }
}
