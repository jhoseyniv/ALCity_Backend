package com.alcity.repository.alobject;

import com.alcity.entity.alobject.ObjectAction;
import com.alcity.entity.alobject.PLRulePostActionType;
import com.alcity.entity.alobject.Renderer;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface PLRulePostActionTypeRepository extends CrudRepository<PLRulePostActionType,Long> {
    Collection<PLRulePostActionType> findAll();
    Optional<PLRulePostActionType> findByValue(String value);
    Optional<PLRulePostActionType> findById(Long id);

}
