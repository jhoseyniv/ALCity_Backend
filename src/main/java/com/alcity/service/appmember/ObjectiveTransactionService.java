package com.alcity.service.appmember;


import com.alcity.dto.appmember.PLObjectiveTransactionDTO;
import com.alcity.entity.alenum.PLObjectiveTransactionType;
import com.alcity.entity.appmember.*;
import com.alcity.entity.puzzle.PLGameInstance;
import com.alcity.entity.puzzle.PLObjective;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.repository.appmember.AppMember_LearningSkillRepository;
import com.alcity.repository.appmember.ObjectiveTransactionRepository;
import com.alcity.repository.learning.LearningSkillRepository;
import com.alcity.service.puzzle.PLGameInstanceService;
import com.alcity.service.puzzle.PLObjectiveService;
import com.alcity.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ObjectiveTransactionService implements ObjectiveTransactionRepository {

    @Autowired
    private AppMemberRepository appMemberRepository;

    @Autowired
    ObjectiveTransactionRepository objectiveTransactionRepository;

    @Autowired
    LearningSkillRepository learningSkillRepository;

    @Autowired
    AppMember_LearningSkillRepository appMember_LearningSkillRepository;

    @Autowired
    private AppMember_WalletItemService appMember_WalletItemRepository;

    @Autowired
    private PLObjectiveService plObjectiveService;

    @Autowired
    private PLGameInstanceService gameInstanceService;

    @Override
    public <S extends ObjectiveTransaction> S save(S entity) {
        return null;
    }

    public ObjectiveTransaction save(PLObjectiveTransactionDTO dto, String code) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        Optional<PLGameInstance> plGameInstanceOptional = gameInstanceService.findById(dto.getGameInstanceId());
        Optional<PLObjective> objectiveOptional = plObjectiveService.findById(dto.getObjectiveId());
        Optional<AppMember> appMemberOptional = appMemberRepository.findById(dto.getAppMemberId());
        PLObjectiveTransactionType transactionType = PLObjectiveTransactionType.getByTitle(dto.getObjectiveType());
        if(plGameInstanceOptional.isEmpty()) return  null;

        ObjectiveTransaction transaction = new ObjectiveTransaction(DateUtils.getNow(),appMemberOptional.get(),dto.getAmount(),plGameInstanceOptional.get(),objectiveOptional.get(),transactionType
                ,1L,DateUtils.getNow(),DateUtils.getNow(),createdBy.get(),createdBy.get());
        objectiveTransactionRepository.save(transaction);
        return  transaction;
    }

    @Override
    public <S extends ObjectiveTransaction> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<ObjectiveTransaction> findById(Long aLong) {
        return objectiveTransactionRepository.findById(aLong);
    }

    @Override
    public Collection<ObjectiveTransaction> findAll() {
        return null;
    }


    @Override
    public boolean existsById(Long aLong) {
        return false;
    }



    @Override
    public Collection<ObjectiveTransaction> findByAmount(Float amount) {
        return null;
    }

    @Override
    public Optional<ObjectiveTransaction> findByPlObjectiveAndGameInstance(PLObjective plObjective, PLGameInstance gameInstance) {
        return Optional.empty();
    }


    @Override
    public Collection<ObjectiveTransaction> findByPlObjective(PLObjective plObjective) {
        return objectiveTransactionRepository.findByPlObjective(plObjective);
    }

    @Override
    public Collection<ObjectiveTransaction> findByPlObjectiveAndTransactionType(PLObjective plObjective, PLObjectiveTransactionType transactionType) {
        return List.of();
    }

    @Override
    public Collection<ObjectiveTransaction> findByPlObjectiveAndTransactionTypeAndAppMember(PLObjective plObjective, PLObjectiveTransactionType transactionType, AppMember appMember) {
        return objectiveTransactionRepository.findByPlObjectiveAndTransactionTypeAndAppMember(plObjective,transactionType,appMember);
    }

    @Override
    public Collection<ObjectiveTransaction> findByTransactionDateContaining(String transactionDate) {
        return objectiveTransactionRepository.findByTransactionDateContaining(transactionDate);
    }

    @Override
    public Collection<ObjectiveTransaction> findByTransactionDateContainingAndAppMember(String transactionDate, AppMember appMember) {
        return List.of();
    }


    public Collection<ObjectiveTransaction> findByTransactionAndAppMember(String transactionDate, AppMember appMember) {
        Collection<ObjectiveTransaction> transactions = objectiveTransactionRepository.findByAppMember(appMember);
        Collection<ObjectiveTransaction> filteredTransactions = transactions.stream().filter(skillTransaction -> skillTransaction.getTransactionDate().contains(transactionDate)).collect(Collectors.toList());
        return filteredTransactions;
    }

    @Override
    public Collection<ObjectiveTransaction> findByAppMemberAndTransactionDateContaining(AppMember appMember, String transactionDate) {
        return objectiveTransactionRepository.findByAppMemberAndTransactionDateContaining(appMember,transactionDate);
    }

    @Override
    public Collection<ObjectiveTransaction> findByCreatedContaining(String created) {
        return objectiveTransactionRepository.findByCreatedContaining(created);
    }

    @Override
    public Collection<ObjectiveTransaction> findByAppMember(AppMember appMember) {
        return objectiveTransactionRepository.findByAppMember(appMember);
    }

    @Override
    public Iterable<ObjectiveTransaction> findAllById(Iterable<Long> longs) {
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
    public void delete(ObjectiveTransaction entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends ObjectiveTransaction> entities) {
        objectiveTransactionRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {

    }
}
