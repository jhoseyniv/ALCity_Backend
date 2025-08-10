package com.alcity.service.puzzle;

import com.alcity.dto.plimpexport.PLLearningTopicData;
import com.alcity.dto.puzzle.PlLearningTopicDTO;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.learning.LearningContent;
import com.alcity.entity.learning.LearningTopic;
import com.alcity.entity.puzzle.PLLearningTopic;
import com.alcity.entity.puzzle.PuzzleLevel;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.repository.puzzle.PLLearningTopicRepository;
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
    public <S extends PLLearningTopic> S save(S entity) {
        return plLearningTopicRepository.save(entity);
    }


    public PLLearningTopic importLearningTopic(PLLearningTopicData topicImport, PuzzleLevel puzzleLevel) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        Optional<LearningTopic> learningTopicOptional = learningTopicService.findById(topicImport.getId());
        Optional<LearningContent> learningContentOptional = learningContentService.findById(topicImport.getLearningContentId());

        PLLearningTopic importedLearningTopicInPL = new PLLearningTopic(puzzleLevel, learningTopicOptional.get(), learningContentOptional.get()
                , 1L, DateUtils.getNow(), DateUtils.getNow(), createdBy.get(), createdBy.get());
        plLearningTopicRepository.save(importedLearningTopicInPL);
        return importedLearningTopicInPL;
    }

    public Collection<PLLearningTopic> importLearningTopics(Collection<PLLearningTopicData> importTopics, PuzzleLevel puzzleLevel) {
        Collection<PLLearningTopic> importedTopics = new ArrayList<>();
        Iterator<PLLearningTopicData> iterator = importTopics.iterator();
        while(iterator.hasNext()){
            PLLearningTopicData importTopic = iterator.next();
            PLLearningTopic importedTopic = importLearningTopic(importTopic,puzzleLevel);
            importedTopics.add(importedTopic);
        }
        return importedTopics;
    }

    public Collection<PLLearningTopic> copyAll(Collection<PLLearningTopic> topics, PuzzleLevel puzzleLevel) {
        Collection<PLLearningTopic> copiedTopics = new ArrayList<>();
        Iterator<PLLearningTopic> iterator = topics.iterator();
        while(iterator.hasNext()){
            PLLearningTopic topic = iterator.next();
            PLLearningTopic copyTopic = copy(topic,puzzleLevel);
            copiedTopics.add(copyTopic);
        }
        return copiedTopics;
    }
    public PLLearningTopic copy(PLLearningTopic learningTopic, PuzzleLevel puzzleLevel) {
        PLLearningTopic copyLearningTopicInPL = new PLLearningTopic(puzzleLevel, learningTopic.getLearningTopic(), learningTopic.getLearningContent()
                , 1L, DateUtils.getNow(), DateUtils.getNow(), learningTopic.getCreatedBy(), learningTopic.getUpdatedBy());
        plLearningTopicRepository.save(copyLearningTopicInPL);
        return copyLearningTopicInPL;
    }

    public PLLearningTopic save(PlLearningTopicDTO dto, String code) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        PLLearningTopic learningTopicInPL=null;
        Optional<LearningContent> learningContentOptional = learningContentService.findById(dto.getLearningContentId());
        Optional<LearningTopic> learningTopicOptional = learningTopicService.findById(dto.getLearningTopicId());
        Optional<PuzzleLevel> puzzleLevelOptional = puzzleLevelService.findById(dto.getPuzzleLevelId());
        if(learningContentOptional.isEmpty() || learningTopicOptional.isEmpty() || puzzleLevelOptional.isEmpty()) return  null;
         if (code.equalsIgnoreCase("Save")) { //Save
             learningTopicInPL = new PLLearningTopic(puzzleLevelOptional.get(), learningTopicOptional.get(), learningContentOptional.get()
                    , 1L, DateUtils.getNow(), DateUtils.getNow(), createdBy.get(), createdBy.get());
             plLearningTopicRepository.save(learningTopicInPL);
        }else{//edit
            Optional<PLLearningTopic> learningTopicInPLOptional= plLearningTopicRepository.findById(dto.getId());
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
    public <S extends PLLearningTopic> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PLLearningTopic> findById(Long id) {
        return plLearningTopicRepository.findById(id);
    }

    @Override
    public Optional<PLLearningTopic> findByLearningTopicAndAndLearningContent(LearningTopic learningTopic, LearningContent learningContent) {
        return plLearningTopicRepository.findByLearningTopicAndAndLearningContent(learningTopic,learningContent);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<PLLearningTopic> findAll() {
        return null;
    }

    @Override
    public Iterable<PLLearningTopic> findAllById(Iterable<Long> longs) {
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
    public void delete(PLLearningTopic entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PLLearningTopic> entities) {
        plLearningTopicRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {

    }
}
