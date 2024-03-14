package com.alcity.repository.alobject;

import com.alcity.entity.alobject.ObjectCategory;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface ObjectCategoryRepository extends CrudRepository<ObjectCategory,Long> {
    Optional<ObjectCategory> findById(Long id);
    Collection<ObjectCategory> findAll();
    ObjectCategory findByLabel(String label);
    ObjectCategory findByValue(String value);
}
