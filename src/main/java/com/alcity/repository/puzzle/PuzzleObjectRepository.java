package com.alcity.repository.puzzle;

import com.alcity.entity.puzzle.ALCityObject;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface PuzzleObjectRepository extends CrudRepository<ALCityObject,Long> {
    Optional<ALCityObject> findById(Long id);
    Collection<ALCityObject> findAll();

    Collection<ALCityObject> findByTitle(String title);

}
