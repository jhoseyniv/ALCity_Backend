package com.alcity.repository.puzzle;

import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.puzzle.ALCityObject;
import com.alcity.entity.puzzle.PuzzleGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface ALCityObjectRepository extends CrudRepository<ALCityObject,Long> {
    Optional<ALCityObject> findById(Long id);
    Collection<ALCityObject> findAll();
    Optional<ALCityObject> findByIcon(BinaryContent icon);
    Optional<ALCityObject> findByPicture(BinaryContent pic);

    Optional<ALCityObject> findByTitle(String title);

}
