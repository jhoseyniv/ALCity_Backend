package com.alcity.repository.ruleengine;

import com.alcity.entity.alobject.ObjectActionEvent;
import com.alcity.entity.ruleengine.RulePostActionEvent;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface RulePostActionEventRepository extends CrudRepository<RulePostActionEvent,Long> {
    Optional<RulePostActionEvent> findById(Long id);
    Collection<RulePostActionEvent> findAll();

}
