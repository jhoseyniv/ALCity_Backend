package com.alcity.repository.puzzle;

import com.alcity.entity.puzzle.PLRule;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface PLRuleRepository extends CrudRepository<PLRule,Long> {
    Optional<PLRule> findById(Long id);
    Collection<PLRule> findAll();
    Collection<PLRule> findByTitle(String title);
    Collection<PLRule> findByCondition(String condition);

}
