package com.alcity.repository.alobject;

import com.alcity.entity.alobject.RulePostActionEvent;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface RulePostActionEventRepository extends CrudRepository<RulePostActionEvent,Long> {
    Optional<RulePostActionEvent> findById(Long id);
    Collection<RulePostActionEvent> findAll();

}
