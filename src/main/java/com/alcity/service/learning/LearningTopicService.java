package com.alcity.service.learning;

import com.alcity.dto.learning.LearningTopicDTO;
import com.alcity.entity.learning.LearningTopic;
import com.alcity.entity.appmember.AppMember;
import com.alcity.repository.learning.LearningTopicRepository;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class LearningTopicService implements LearningTopicRepository {

   @Autowired
    LearningTopicRepository learningTopicRepository;

    @Override
    public <S extends LearningTopic> S save(S entity) {
        return learningTopicRepository.save(entity);
    }

    @Override
    public <S extends LearningTopic> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<LearningTopic> findById(Long id) {
        if(id==null) return Optional.empty();
        return learningTopicRepository.findById(id);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<LearningTopic> findAll() {
        return learningTopicRepository.findAll();
    }

    @Override
    public Iterable<LearningTopic> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {
      learningTopicRepository.deleteById(aLong);
    }
 @Autowired
 private AppMemberRepository appMemberRepository;

 public LearningTopic save(LearningTopicDTO dto, String code) {
  Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
     LearningTopic learningTopic=null;
     LearningTopic parentTopic=null;
  Optional<LearningTopic> learningTopicParentOptional= learningTopicRepository.findById(dto.getParentId());
  if(learningTopicParentOptional.isPresent())
      parentTopic =learningTopicParentOptional.get();

  if (code.equalsIgnoreCase("Save")) { //Save
   learningTopic = new LearningTopic(dto.getTitle(),parentTopic , 1L,
           DateUtils.getNow(), DateUtils.getNow(), createdBy.get(), createdBy.get());
   learningTopicRepository.save(learningTopic);
  }else{//edit
   Optional<LearningTopic> learningTopicOptional= learningTopicRepository.findById(dto.getId());
   if(learningTopicOptional.isPresent()) {
    learningTopic = learningTopicOptional.get();
    learningTopic.setTitle(dto.getTitle());
    learningTopic.setParentTopic(parentTopic);
    learningTopic.setVersion(learningTopic.getVersion()+1);
    learningTopic.setUpdated(DateUtils.getNow());
    learningTopic.setUpdatedBy(createdBy.get());
    learningTopicRepository.save(learningTopic);
   }
  }
  return learningTopic;
 }

    @Override
    public void delete(LearningTopic entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends LearningTopic> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Optional<LearningTopic> findByTitle(String title) {
        return learningTopicRepository.findByTitle(title);
    }
}
