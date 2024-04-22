package com.alcity.repository.puzzle;

import com.alcity.entity.puzzle.PLRulePostAction;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface PLRulePostActionRepository extends CrudRepository<PLRulePostAction,Long> {
    Optional<PLRulePostAction> findById(Long id);
    Collection<PLRulePostAction> findAll();
    Collection<PLRulePostAction> findByOrdering(Integer ordering);
    Collection<PLRulePostAction> findByActionExpression(String experssion);
}
