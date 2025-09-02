package com.alcity.service.appmember;


import com.alcity.dto.appmember.LearningSkillTransactionDTO;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.appmember.AppMember_LearningSkill;
import com.alcity.entity.appmember.LearningSkillTransaction;
import com.alcity.entity.appmember.WalletTransaction;
import com.alcity.entity.learning.LearningSkill;
import com.alcity.entity.puzzle.PLObjective;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.repository.appmember.AppMember_LearningSkillRepository;
import com.alcity.repository.appmember.LearningSkillTransactionRepository;
import com.alcity.repository.learning.LearningSkillRepository;
import com.alcity.service.puzzle.PLObjectiveService;
import com.alcity.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
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

    @Autowired
    AppMember_LearningSkillRepository appMember_LearningSkillRepository;

    @Autowired
    private PLObjectiveService plObjectiveService;

    @Override
    public <S extends LearningSkillTransaction> S save(S entity) {
        return null;
    }

    public LearningSkillTransaction save(LearningSkillTransactionDTO dto, String code) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        Optional<AppMember> appMemberOptional = appMemberRepository.findById(dto.getAppMemberId());
        Optional<PLObjective> objectiveOptional = plObjectiveService.findById(dto.getObjectiveId());
        if(appMemberOptional.isEmpty()) return  null;

        Optional<LearningSkill> learningSkillOptional = learningSkillRepository.findById(dto.getLearningSkillId());

        LearningSkillTransaction transaction = new LearningSkillTransaction(1L,DateUtils.getNow(),DateUtils.getNow(),createdBy.get(),createdBy.get()
                ,DateUtils.getNow(),dto.getAmount() ,dto.getDescription(),learningSkillOptional.get(),appMemberOptional.get(),objectiveOptional.get());
        learningSkillTransactionRepository.save(transaction);
        return  transaction;
    }

    public void updateAppMemberSkills(LearningSkillTransaction transaction) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        AppMember_LearningSkill appMemberLearningSkill = null;
        AppMember appMember = transaction.getAppMember();
        LearningSkill learningSkill = transaction.getLearningSkill();
        Optional<AppMember_LearningSkill> appMemberLearningSkillOptional = appMember_LearningSkillRepository.findByApplicationMemberAndLearningSkill(appMember,learningSkill);
        if(appMemberLearningSkillOptional.isEmpty()) {
            appMemberLearningSkill = new AppMember_LearningSkill(appMember,learningSkill, transaction.getAmount(),0L,1L,DateUtils.getNow(),DateUtils.getNow(),
                    createdBy.get(),createdBy.get());
            appMember_LearningSkillRepository.save(appMemberLearningSkill);
        }else{
            appMemberLearningSkill = appMemberLearningSkillOptional.get();
            Float sumAmount = transaction.getAmount() + appMemberLearningSkill.getAmount();
            Long  levelUpSize = learningSkill.getLevelUpSize();
            Long level = (long) (sumAmount / levelUpSize);
            Float reminder = (Float) (sumAmount % levelUpSize);
            appMemberLearningSkill.setAmount(reminder);
            appMemberLearningSkill.setLevel(level);
            appMemberLearningSkill.setVersion(appMemberLearningSkill.getVersion()+1);
            appMember_LearningSkillRepository.save(appMemberLearningSkill);
        }
    }

    public void updateAppMemberWalletItem(WalletTransaction transaction) {
    }

    @Override
    public <S extends LearningSkillTransaction> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<LearningSkillTransaction> findById(Long aLong) {
        return learningSkillTransactionRepository.findById(aLong);
    }

    @Override
    public Collection<LearningSkillTransaction> findAll() {
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
    public Optional<LearningSkillTransaction> findByPlObjectiveAndAppMember(PLObjective plObjective, AppMember appMember) {
        return learningSkillTransactionRepository.findByPlObjectiveAndAppMember(plObjective,appMember);
    }

    @Override
    public Collection<LearningSkillTransaction> findByTransactionDateContaining(String transactionDate) {
        return learningSkillTransactionRepository.findByTransactionDateContaining(transactionDate);
    }

    @Override
    public Collection<LearningSkillTransaction> findByTransactionDateContainingAndAppMember(String transactionDate, AppMember appMember) {
        return learningSkillTransactionRepository.findByTransactionDateContainingAndAppMember(transactionDate, appMember);
    }

    @Override
    public Collection<LearningSkillTransaction> findByCreatedContaining(String created) {
        return learningSkillTransactionRepository.findByCreatedContaining(created);
    }

    @Override
    public Collection<LearningSkillTransaction> findByAppMember(AppMember appMember) {
        return learningSkillTransactionRepository.findByAppMember(appMember);
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
