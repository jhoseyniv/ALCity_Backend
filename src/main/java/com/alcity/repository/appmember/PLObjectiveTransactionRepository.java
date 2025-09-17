package com.alcity.repository.appmember;

import com.alcity.entity.alenum.PLObjectiveTransactionType;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.appmember.PLObjectiveTransaction;
import com.alcity.entity.learning.LearningSkill;
import com.alcity.entity.puzzle.PLGameInstance;
import com.alcity.entity.puzzle.PLObjective;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
@Repository
public interface PLObjectiveTransactionRepository extends CrudRepository<PLObjectiveTransaction,Long> {

    Optional<PLObjectiveTransaction> findById(Long id);
    Collection<PLObjectiveTransaction> findAll();

    Collection<PLObjectiveTransaction> findByAmount(Float amount);

    Optional<PLObjectiveTransaction> findByPlObjectiveAndGameInstance(PLObjective plObjective, PLGameInstance gameInstance);
    Collection<PLObjectiveTransaction> findByPlObjective(PLObjective plObjective);

    Collection<PLObjectiveTransaction> findByPlObjectiveAndTransactionType(PLObjective plObjective, PLObjectiveTransactionType transactionType);

    Collection<PLObjectiveTransaction> findByPlObjectiveAndTransactionTypeAndAppMember(PLObjective plObjective, PLObjectiveTransactionType transactionType, AppMember appMember);

    Collection<PLObjectiveTransaction> findByTransactionDateContaining(String transactionDate);
    Collection<PLObjectiveTransaction> findByTransactionDateContainingAndAppMember(String transactionDate, AppMember appMember);

    Collection<PLObjectiveTransaction> findByAppMemberAndTransactionDateContaining(AppMember appMember, String transactionDate);
    Collection<PLObjectiveTransaction> findByCreatedContaining(String created);

    Collection<PLObjectiveTransaction> findByAppMember(AppMember appMember);


}
