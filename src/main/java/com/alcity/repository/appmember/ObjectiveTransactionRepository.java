package com.alcity.repository.appmember;

import com.alcity.entity.alenum.PLObjectiveTransactionType;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.appmember.ObjectiveTransaction;
import com.alcity.entity.puzzle.PLGameInstance;
import com.alcity.entity.puzzle.PLObjective;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
@Repository
public interface ObjectiveTransactionRepository extends CrudRepository<ObjectiveTransaction,Long> {

    Optional<ObjectiveTransaction> findById(Long id);
    Collection<ObjectiveTransaction> findAll();

    Collection<ObjectiveTransaction> findByAmount(Float amount);

    Collection<ObjectiveTransaction> findByGameInstance(PLGameInstance gameInstance);

    Optional<ObjectiveTransaction> findByPlObjectiveAndGameInstance(PLObjective plObjective, PLGameInstance gameInstance);
    Collection<ObjectiveTransaction> findByPlObjective(PLObjective plObjective);

    Collection<ObjectiveTransaction> findByPlObjectiveAndTransactionType(PLObjective plObjective, PLObjectiveTransactionType transactionType);

    Collection<ObjectiveTransaction> findByPlObjectiveAndTransactionTypeAndAppMember(PLObjective plObjective, PLObjectiveTransactionType transactionType, AppMember appMember);

    Collection<ObjectiveTransaction> findByTransactionDateContaining(String transactionDate);
    Collection<ObjectiveTransaction> findByTransactionDateContainingAndAppMember(String transactionDate, AppMember appMember);

    Collection<ObjectiveTransaction> findByAppMemberAndTransactionDateContaining(AppMember appMember, String transactionDate);
    Collection<ObjectiveTransaction> findByCreatedContaining(String created);

    Collection<ObjectiveTransaction> findByAppMember(AppMember appMember);

    Collection<ObjectiveTransaction> findByAppMemberAndTransactionType(AppMember appMember, PLObjectiveTransactionType transactionType);


}
