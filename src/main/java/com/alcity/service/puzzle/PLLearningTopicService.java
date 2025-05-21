package com.alcity.service.puzzle;

import com.alcity.dto.puzzle.PLDTO;
import com.alcity.dto.puzzle.PlLearningTopicDTO;
import com.alcity.entity.alenum.PLDifficulty;
import com.alcity.entity.alenum.PLStatus;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.base.PLPrivacy;
import com.alcity.entity.learning.LearningContent;
import com.alcity.entity.learning.LearningTopic;
import com.alcity.entity.puzzle.LearningTopicInPL;
import com.alcity.entity.puzzle.PLRule;
import com.alcity.entity.puzzle.PuzzleGroup;
import com.alcity.entity.puzzle.PuzzleLevel;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.repository.base.BinaryContentRepository;
import com.alcity.repository.learning.LearningContentRepository;
import com.alcity.repository.learning.LearningTopicRepository;
import com.alcity.repository.puzzle.PLLearningTopicRepository;
import com.alcity.repository.puzzle.PuzzleLevelRepository;
import com.alcity.service.learning.LearningContentService;
import com.alcity.service.learning.LearningTopicService;
import com.alcity.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

@Service
@Transactional
public class PLLearningTopicService implements PLLearningTopicRepository {

    @Autowired
    @Qualifier("PLLearningTopicRepository")

    PLLearningTopicRepository plLearningTopicRepository;
    @Autowired
    private AppMemberRepository appMemberRepository;

    @Autowired
    private LearningContentService learningContentService;

    @Autowired
    private LearningTopicService learningTopicService;

    @Autowired
    private PuzzleLevelService  puzzleLevelService;


    @Override
    public <S extends LearningTopicInPL> S save(S entity) {
        return plLearningTopicRepository.save(entity);
    }



    public Collection<LearningTopicInPL> copyAll(Collection<LearningTopicInPL> topics, PuzzleLevel puzzleLevel) {
        Collection<LearningTopicInPL> copiedTopics = new ArrayList<>();
        Iterator<LearningTopicInPL> iterator = topics.iterator();
        while(iterator.hasNext()){
            LearningTopicInPL topic = iterator.next();
            LearningTopicInPL copyTopic = copy(topic,puzzleLevel);
            copiedTopics.add(copyTopic);
        }
        return copiedTopics;
    }
    public LearningTopicInPL copy(LearningTopicInPL learningTopic, PuzzleLevel puzzleLevel) {
        LearningTopicInPL copyLearningTopicInPL = new LearningTopicInPL(puzzleLevel, learningTopic.getLearningTopic(), learningTopic.getLearningContent()
                , 1L, DateUtils.getNow(), DateUtils.getNow(), learningTopic.getCreatedBy(), learningTopic.getUpdatedBy());
        plLearningTopicRepository.save(copyLearningTopicInPL);
        return copyLearningTopicInPL;
    }

    public LearningTopicInPL save(PlLearningTopicDTO dto, String code) {
            Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        LearningTopicInPL learningTopicInPL=null;
        Optional<LearningContent> learningContentOptional = learningContentService.findById(dto.getLearningContentId());
        Optional<LearningTopic> learningTopicOptional = learningTopicService.findById(dto.getLearningTopicId());
        Optional<PuzzleLevel> puzzleLevelOptional = puzzleLevelService.findById(dto.getPuzzleLevelId());
        if(learningContentOptional.isEmpty() || learningTopicOptional.isEmpty() || puzzleLevelOptional.isEmpty()) return  null;
         if (code.equalsIgnoreCase("Save")) { //Save
             learningTopicInPL = new LearningTopicInPL(puzzleLevelOptional.get(), learningTopicOptional.get(), learningContentOptional.get()
                    , 1L, DateUtils.getNow(), DateUtils.getNow(), createdBy.get(), createdBy.get());
             plLearningTopicRepository.save(learningTopicInPL);
        }else{//edit
            Optional<LearningTopicInPL> learningTopicInPLOptional= plLearningTopicRepository.findById(dto.getId());
            if(learningTopicInPLOptional.isPresent()) {
                learningTopicInPL = learningTopicInPLOptional.get();
                learningTopicInPL.setLearningContent(learningContentOptional.get());
                learningTopicInPL.setLearningTopic(learningTopicOptional.get());
                learningTopicInPL.setVersion(learningTopicInPL.getVersion()+1);
                learningTopicInPL.setPuzzleLevel(puzzleLevelOptional.get());
                plLearningTopicRepository.save(learningTopicInPL);
            }
        }
        return learningTopicInPL;
    }
    @Override
    public <S extends LearningTopicInPL> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<LearningTopicInPL> findById(Long id) {
        return plLearningTopicRepository.findById(id);
    }

    @Override
    public Optional<LearningTopicInPL> findByLearningTopicAndAndLearningContent(LearningTopic learningTopic, LearningContent learningContent) {
        return plLearningTopicRepository.findByLearningTopicAndAndLearningContent(learningTopic,learningContent);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<LearningTopicInPL> findAll() {
        return null;
    }

    @Override
    public Iterable<LearningTopicInPL> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {
        plLearningTopicRepository.deleteById(aLong);
    }

    @Override
    public void delete(LearningTopicInPL entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends LearningTopicInPL> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
