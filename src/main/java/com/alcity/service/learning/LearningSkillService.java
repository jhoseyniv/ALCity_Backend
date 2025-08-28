package com.alcity.service.learning;

import com.alcity.dto.learning.LearningSkillDTO;
import com.alcity.entity.alenum.SkillType;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.learning.LearningSkill;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.repository.learning.LearningSkillRepository;
import com.alcity.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public abstract class LearningSkillService implements LearningSkillRepository {

    @Autowired
    LearningSkillRepository learningSkillRepository;
    @Override
    public <S extends LearningSkill> S save(S entity) {
        return learningSkillRepository.save(entity);
    }

    @Override
    public <S extends LearningSkill> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<LearningSkill> findById(Long id) {
        if(id==null) return Optional.empty();
        return learningSkillRepository.findById(id);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<LearningSkill> findAll() {
        return learningSkillRepository.findAll();
    }

    @Override
    public Collection<LearningSkill> findByType(String type) {
        return learningSkillRepository.findByType(type);
    }

    @Override
    public Collection<LearningSkill> findByTitleContains(String criteria) {
        return learningSkillRepository.findByTitleContains(criteria);
    }

    @Override
    public Iterable<LearningSkill> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {
        learningSkillRepository.deleteById(aLong);
    }

    @Override
    public void delete(LearningSkill entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends LearningSkill> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public LearningSkill findByTitle(String label) {

        return learningSkillRepository.findByTitle(label);
    }
    @Autowired
    private AppMemberRepository appMemberRepository;

   public LearningSkill save(LearningSkillDTO dto, String code) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        SkillType type = SkillType.valueOf(dto.getType());
        Optional<LearningSkill> parentSkillOptional = learningSkillRepository.findById(dto.getParentId());
        if(parentSkillOptional.isEmpty()) {}
        LearningSkill learningSkill=null;
        if (code.equalsIgnoreCase("Save")) { //Save
            learningSkill = new LearningSkill(1L, DateUtils.getNow(), DateUtils.getNow(), createdBy.get(), createdBy.get(),
                    dto.getTitle(),type, parentSkillOptional.get());
            learningSkillRepository.save(learningSkill);
        }else{//edit
            Optional<LearningSkill> learningSkillOptional= learningSkillRepository.findById(dto.getId());
            if(learningSkillOptional.isPresent()) {
                learningSkill = learningSkillOptional.get();
                learningSkill.setTitle(dto.getTitle());
                learningSkill.setType(type);
                learningSkill.setParentSkill(parentSkillOptional.get());
                learningSkill.setVersion(learningSkill.getVersion()+1);
                learningSkill.setCreated(DateUtils.getNow());
                learningSkill.setUpdated(DateUtils.getNow());
                learningSkill.setCreatedBy(createdBy.get());
                learningSkill.setUpdatedBy(createdBy.get());
                learningSkillRepository.save(learningSkill);
            }
        }
        return learningSkill;
    }




}
