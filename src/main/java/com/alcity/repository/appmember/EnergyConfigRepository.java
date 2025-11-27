package com.alcity.repository.appmember;

import com.alcity.entity.appmember.EnergyConfig;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EnergyConfigRepository extends CrudRepository<EnergyConfig,Long> {
    Optional<EnergyConfig> findById(long id);
    Optional<EnergyConfig> findByExpireIsFalse();

}
