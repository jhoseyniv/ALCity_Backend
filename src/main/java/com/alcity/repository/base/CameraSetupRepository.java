package com.alcity.repository.base;

import com.alcity.entity.base.CameraSetup;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface CameraSetupRepository extends CrudRepository<CameraSetup,Long> {
    Collection<CameraSetup> findAll();
    Optional<CameraSetup> findById(Long id);

}
