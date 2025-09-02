package com.alcity.repository.appmember;

import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.appmember.LearningSkillTransaction;
import com.alcity.entity.appmember.WalletItem;
import com.alcity.entity.appmember.WalletTransaction;
import com.alcity.entity.puzzle.PLObjective;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

public interface LearningSkillTransactionRepository extends CrudRepository<LearningSkillTransaction,Long> {

    Optional<LearningSkillTransaction> findById(Long id);
    Collection<LearningSkillTransaction> findAll();

    Collection<LearningSkillTransaction> findByAmount(Float amount);

    Optional<LearningSkillTransaction> findByPlObjectiveAndAppMember(PLObjective plObjective, AppMember appMember);

    Collection<LearningSkillTransaction> findByTransactionDateContaining(String transactionDate);
    Collection<LearningSkillTransaction> findByTransactionDateContainingAndAppMember(String transactionDate, AppMember appMember);
    Collection<LearningSkillTransaction> findByCreatedContaining(String created);

    Collection<LearningSkillTransaction> findByAppMember(AppMember appMember);

}
