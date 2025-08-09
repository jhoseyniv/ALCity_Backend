package com.alcity.service.puzzle;

import com.alcity.dto.pgimport.PGLearningSkillContentImportDTO;
import com.alcity.dto.puzzle.PGLearningSkillContentDTO;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.learning.LearningContent;
import com.alcity.entity.learning.LearningSkill;
import com.alcity.entity.puzzle.PGLearningSkill;
import com.alcity.entity.puzzle.PuzzleGroup;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.repository.puzzle.PGLearningSkillRepository;
import com.alcity.service.learning.LearningContentService;
import com.alcity.service.learning.LearningSkillService;
import com.alcity.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;;
import java.util.Collection;
import java.util.Optional;


@Service
@Transactional
public class PGLearningSkillService implements PGLearningSkillRepository {

    @Qualifier("PGLearningSkillRepository")
    @Autowired
    PGLearningSkillRepository pgLearningSkillRepository;
    @Autowired
    private AppMemberRepository appMemberRepository;
    @Autowired
    private LearningContentService learningContentService;
    @Autowired
    private LearningSkillService learningSkillService;
    @Autowired
    private PGService pgService;

    @Override
    public <S extends PGLearningSkill> S save(S entity) {
        return pgLearningSkillRepository.save(entity);
    }
    public PGLearningSkill importPGLearningSkill(PGLearningSkillContentImportDTO dto, PuzzleGroup puzzleGroup) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        PGLearningSkill pgLearningSkillContent=null;
        Optional<LearningContent> learningContentOptional = learningContentService.findById(dto.getLearningContentId());
        Optional<LearningSkill> learningSkillOptional = learningSkillService.findById(dto.getLearningSkillId());

        if(learningContentOptional.isEmpty() || learningSkillOptional.isEmpty() || puzzleGroup==null) return  null;
        pgLearningSkillContent = new PGLearningSkill(learningSkillOptional.get(), puzzleGroup, learningContentOptional.get()
                    , 1L, DateUtils.getNow(), DateUtils.getNow(), createdBy.get(), createdBy.get());
        pgLearningSkillRepository.save(pgLearningSkillContent);

        return pgLearningSkillContent;
    }


    public PGLearningSkill save(PGLearningSkillContentDTO dto, String code) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        PGLearningSkill pgLearningSkillContent=null;
        Optional<LearningContent> learningContentOptional = learningContentService.findById(dto.getLearningContentId());
        Optional<LearningSkill> learningSkillOptional = learningSkillService.findById(dto.getLearningSkillId());
        Optional<PuzzleGroup> puzzleGroupOptional = pgService.findById(dto.getPuzzleGroupId());

        if(learningContentOptional.isEmpty() || learningSkillOptional.isEmpty() || puzzleGroupOptional.isEmpty()) return  null;
        if (code.equalsIgnoreCase("Save")) { //Save
            pgLearningSkillContent = new PGLearningSkill(learningSkillOptional.get(), puzzleGroupOptional.get(), learningContentOptional.get()
                    , 1L, DateUtils.getNow(), DateUtils.getNow(), createdBy.get(), createdBy.get());
            pgLearningSkillRepository.save(pgLearningSkillContent);
        }else{//edit
            Optional<PGLearningSkill> pgLearningSkillContentOptional= pgLearningSkillRepository.findById(dto.getId());
            if(pgLearningSkillContentOptional.isPresent()) {
                pgLearningSkillContent = pgLearningSkillContentOptional.get();
                pgLearningSkillContent.setLearningContent(learningContentOptional.get());
                pgLearningSkillContent.setLearningSkill(learningSkillOptional.get());
                pgLearningSkillContent.setPuzzleGroup(puzzleGroupOptional.get());
                pgLearningSkillRepository.save(pgLearningSkillContent);
            }
        }
        return pgLearningSkillContent;
    }
    @Override
    public <S extends PGLearningSkill> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PGLearningSkill> findById(Long id) {
        return pgLearningSkillRepository.findById(id);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<PGLearningSkill> findAll() {
        return null;
    }

    @Override
    public Iterable<PGLearningSkill> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {
        pgLearningSkillRepository.deleteById(aLong);
    }

    @Override
    public void delete(PGLearningSkill entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PGLearningSkill> entities) {
        pgLearningSkillRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {

    }
}
