package com.alcity.repository.base;

import com.alcity.entity.base.PLPrivacy;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface PLPrivacyRepository extends CrudRepository<PLPrivacy,Long> {
    Optional<PLPrivacy> findById(Long id);
    Collection<PLPrivacy> findAll();
    PLPrivacy findByLabel(String label);
    PLPrivacy findByValue(String value);

}
