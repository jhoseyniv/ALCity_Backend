package com.alcity.repository.ruleengine;

import com.alcity.entity.base.MemberType;
import com.alcity.entity.ruleengine.SystemEvent;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface SystemEventRepository extends CrudRepository<SystemEvent,Long> {
    Optional<SystemEvent> findById(Long id);
    Collection<SystemEvent> findAll();
    SystemEvent findByLabel(String label);
    SystemEvent findByValue(String value);

}
