package com.alcity.repository.puzzle;

import com.alcity.entity.puzzle.PLRuleEvent;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface PLRuleEventRepository extends CrudRepository<PLRuleEvent,Long> {
    Optional<PLRuleEvent> findById(Long id);
    Collection<PLRuleEvent> findAll();
    Optional<PLRuleEvent> findByNameIgnoreCase(String name);
   // Optional<PLRuleEvent> findByNameAndSubEvent(String name,String subEvent);

}
