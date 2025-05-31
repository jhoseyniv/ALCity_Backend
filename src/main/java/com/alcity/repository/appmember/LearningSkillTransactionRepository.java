package com.alcity.repository.appmember;

import com.alcity.entity.appmember.LearningSkillTransaction;
import com.alcity.entity.appmember.WalletItem;
import com.alcity.entity.appmember.WalletTransaction;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface LearningSkillTransactionRepository extends CrudRepository<LearningSkillTransaction,Long> {

    Optional<LearningSkillTransaction> findById(Long id);
    Collection<LearningSkillTransaction> findAll();
    LearningSkillTransaction findByTransactionDate(String label);
    Collection<LearningSkillTransaction> findByAmount(Float amount);

}
