package com.alcity.repository.puzzle;

import com.alcity.entity.puzzle.ALCityObjectInPG;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface ALCityObjectInPGRepository extends CrudRepository<ALCityObjectInPG,Long> {
    Optional<ALCityObjectInPG> findById(Long id);
    Collection<ALCityObjectInPG> findAll();

    Collection<ALCityObjectInPG> findByTitle(String title);
    ALCityObjectInPG findByCode(String code);


}
