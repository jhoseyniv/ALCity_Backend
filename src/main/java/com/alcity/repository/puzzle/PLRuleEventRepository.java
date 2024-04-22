package com.alcity.repository.puzzle;

import com.alcity.entity.puzzle.PLRuleEvent;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface PLRuleEventRepository extends CrudRepository<PLRuleEvent,Long> {
    Optional<PLRuleEvent> findById(Long id);
    Collection<PLRuleEvent> findAll();
    Collection<PLRuleEvent> findByName(String name);

}
