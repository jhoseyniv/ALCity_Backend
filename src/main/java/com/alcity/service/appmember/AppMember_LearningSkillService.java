package com.alcity.service.appmember;


import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.appmember.AppMember_LearningSkill;
import com.alcity.entity.learning.LearningSkill;
import com.alcity.repository.appmember.AppMember_LearningSkillRepository;
import com.alcity.repository.appmember.AppMember_WalletItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class AppMember_LearningSkillService implements AppMember_LearningSkillRepository {

    @Autowired
    AppMember_LearningSkillRepository appMember_LearningSkillRepository;
    @Override
    public Collection<AppMember_LearningSkill> findByApplicationMember(AppMember applicationMember) {
        return appMember_LearningSkillRepository.findByApplicationMember(applicationMember);
    }

    @Override
    public Optional<AppMember_LearningSkill> findByApplicationMemberAndLearningSkill(AppMember applicationMember, LearningSkill learningSkill) {
        return appMember_LearningSkillRepository.findByApplicationMemberAndLearningSkill(applicationMember,learningSkill);
    }

    @Override
    public <S extends AppMember_LearningSkill> S save(S entity) {
        return null;
    }

    @Override
    public <S extends AppMember_LearningSkill> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<AppMember_LearningSkill> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<AppMember_LearningSkill> findAll() {
        return null;
    }

    @Override
    public Iterable<AppMember_LearningSkill> findAllById(Iterable<Long> longs) {
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
    public void delete(AppMember_LearningSkill entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends AppMember_LearningSkill> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
