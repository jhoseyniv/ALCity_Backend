package com.alcity.service.appmember;


import com.alcity.dto.appmember.LearningSkillTransactionDTO;
import com.alcity.dto.appmember.WalletItemTransactionDTO;
import com.alcity.entity.alenum.WalletTransactionType;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.appmember.LearningSkillTransaction;
import com.alcity.entity.appmember.WalletItem;
import com.alcity.entity.appmember.WalletTransaction;
import com.alcity.entity.learning.LearningSkill;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.repository.appmember.LearningSkillTransactionRepository;
import com.alcity.repository.appmember.WalletTransactionRepository;
import com.alcity.repository.learning.LearningSkillRepository;
import com.alcity.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class LearningSkillTransactionService implements LearningSkillTransactionRepository {

    @Autowired
    private AppMemberRepository appMemberRepository;

    @Autowired
    LearningSkillTransactionRepository learningSkillTransactionRepository;
    @Autowired
    LearningSkillRepository learningSkillRepository;

    @Override
    public <S extends LearningSkillTransaction> S save(S entity) {
        return null;
    }
    public LearningSkillTransaction save(LearningSkillTransactionDTO dto, String code) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        Optional<AppMember> appMemberOptional = appMemberRepository.findById(dto.getAppMemberId());
        if(appMemberOptional.isEmpty()) return  null;

        Optional<LearningSkill> learningSkillOptional = learningSkillRepository.findById(dto.getLearningSkillId());

        LearningSkillTransaction transaction = new LearningSkillTransaction(DateUtils.getNow(),appMemberOptional.get(),
                dto.getAmount() ,dto.getDescription(),learningSkillOptional.get());
        learningSkillTransactionRepository.save(transaction);
        return  transaction;
    }

    @Override
    public <S extends LearningSkillTransaction> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<LearningSkillTransaction> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Collection<LearningSkillTransaction> findAll() {
        return null;
    }

    @Override
    public LearningSkillTransaction findByTransactionDate(String label) {
        return null;
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }



    @Override
    public Collection<LearningSkillTransaction> findByAmount(Float amount) {
        return null;
    }

    @Override
    public Iterable<LearningSkillTransaction> findAllById(Iterable<Long> longs) {
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
    public void delete(LearningSkillTransaction entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends LearningSkillTransaction> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
