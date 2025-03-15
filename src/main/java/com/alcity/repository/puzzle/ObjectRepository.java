package com.alcity.repository.puzzle;

import com.alcity.entity.alobject.ObjectCategory;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.puzzle.ALCityObject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface ObjectRepository extends CrudRepository<ALCityObject,Long> {
    Optional<ALCityObject> findById(Long id);
    Collection<ALCityObject> findAll();
    Collection<ALCityObject> findALCityObjectByObjectCategory(ObjectCategory category);
    Collection<ALCityObject> findALCityObjectByTitleIgnoreCaseContains(String  criteria);
    Collection<ALCityObject> findALCityObjectByObjectCategoryAndTitleIgnoreCase(ObjectCategory category,String title);
    Optional<ALCityObject> findByIcon(BinaryContent icon);
    Optional<ALCityObject> findByPic(BinaryContent pic);

    Optional<ALCityObject> findByTitle(String title);

}
