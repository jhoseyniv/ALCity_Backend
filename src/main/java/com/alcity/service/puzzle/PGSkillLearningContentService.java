package com.alcity.service.puzzle;

import com.alcity.dto.puzzle.PGLearningSkillContentDTO;
import com.alcity.dto.puzzle.PlLearningTopicDTO;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.learning.LearningContent;
import com.alcity.entity.learning.LearningSkill;
import com.alcity.entity.learning.LearningTopic;
import com.alcity.entity.puzzle.LearningTopicInPL;
import com.alcity.entity.puzzle.PGLearningSkillContent;
import com.alcity.entity.puzzle.PuzzleGroup;
import com.alcity.entity.puzzle.PuzzleLevel;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.repository.puzzle.PGSkillLearningContentRepository;
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
public class PGSkillLearningContentService implements PGSkillLearningContentRepository {

    @Qualifier("PGSkillLearningContentRepository")
    @Autowired
    PGSkillLearningContentRepository pgSkillLearningContentRepository;
    @Autowired
    private AppMemberRepository appMemberRepository;
    @Autowired
    private LearningContentService learningContentService;
    @Autowired
    private LearningSkillService learningSkillService;
    @Autowired
    private PGService pgService;

    @Override
    public <S extends PGLearningSkillContent> S save(S entity) {
        return pgSkillLearningContentRepository.save(entity);
    }
    public PGLearningSkillContent save(PGLearningSkillContentDTO dto, String code) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        PGLearningSkillContent pgLearningSkillContent=null;
        Optional<LearningContent> learningContentOptional = learningContentService.findById(dto.getLearningContentId());
        Optional<LearningSkill> learningSkillOptional = learningSkillService.findById(dto.getLearningSkillId());
        Optional<PuzzleGroup> puzzleGroupOptional = pgService.findById(dto.getPuzzleGroupId());

        if(learningContentOptional.isEmpty() || learningSkillOptional.isEmpty() || puzzleGroupOptional.isEmpty()) return  null;
        if (code.equalsIgnoreCase("Save")) { //Save
            pgLearningSkillContent = new PGLearningSkillContent(learningSkillOptional.get(), puzzleGroupOptional.get(), learningContentOptional.get()
                    , 1L, DateUtils.getNow(), DateUtils.getNow(), createdBy.get(), createdBy.get());
            pgSkillLearningContentRepository.save(pgLearningSkillContent);
        }else{//edit
            Optional<PGLearningSkillContent> pgLearningSkillContentOptional= pgSkillLearningContentRepository.findById(dto.getId());
            if(pgLearningSkillContentOptional.isPresent()) {
                pgLearningSkillContent = pgLearningSkillContentOptional.get();
                pgLearningSkillContent.setLearningContent(learningContentOptional.get());
                pgLearningSkillContent.setLearningSkill(learningSkillOptional.get());
                pgLearningSkillContent.setPuzzleGroup(puzzleGroupOptional.get());
                pgSkillLearningContentRepository.save(pgLearningSkillContent);
            }
        }
        return pgLearningSkillContent;
    }

    @Override
    public <S extends PGLearningSkillContent> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PGLearningSkillContent> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<PGLearningSkillContent> findAll() {
        return null;
    }

    @Override
    public Iterable<PGLearningSkillContent> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {
        pgSkillLearningContentRepository.deleteById(aLong);
    }

    @Override
    public void delete(PGLearningSkillContent entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PGLearningSkillContent> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
