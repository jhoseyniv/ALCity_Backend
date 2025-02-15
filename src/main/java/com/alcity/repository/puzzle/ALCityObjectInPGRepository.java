package com.alcity.repository.puzzle;

import com.alcity.entity.puzzle.ALCityObject;
import com.alcity.entity.puzzle.ALCityObjectInPG;
import com.alcity.entity.puzzle.PuzzleGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface ALCityObjectInPGRepository extends CrudRepository<ALCityObjectInPG,Long> {
    Optional<ALCityObjectInPG> findById(Long id);
    Collection<ALCityObjectInPG> findAll();

    Collection<ALCityObjectInPG> findByTitle(String title);
    Collection<ALCityObjectInPG> findByCode(String code);
    Optional<ALCityObjectInPG> findByCodeAndTitle(String code,String title);
    Collection<ALCityObjectInPG> findByalCityObject(ALCityObject cityObject);
    Collection<ALCityObjectInPG> findByPuzzleGroup(PuzzleGroup puzzleGroup);

}
