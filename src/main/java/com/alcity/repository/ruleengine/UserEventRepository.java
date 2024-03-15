package com.alcity.repository.ruleengine;

import com.alcity.entity.ruleengine.SystemEvent;
import com.alcity.entity.ruleengine.UserEvent;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;


public interface UserEventRepository extends CrudRepository<UserEvent,Long> {
    Optional<UserEvent> findById(Long id);
    Collection<UserEvent> findAll();
    UserEvent findByLabel(String label);
    UserEvent findByValue(String value);

}
