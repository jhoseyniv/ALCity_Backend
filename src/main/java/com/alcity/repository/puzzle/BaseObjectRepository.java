package com.alcity.repository.puzzle;

import com.alcity.entity.alobject.ObjectCategory;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.puzzle.BaseObject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface BaseObjectRepository extends CrudRepository<BaseObject,Long> {
    Optional<BaseObject> findById(Long id);
    Collection<BaseObject> findAll();
    Collection<BaseObject> findALCityObjectByObjectCategory(ObjectCategory category);
    Collection<BaseObject> findALCityObjectByTitleIgnoreCaseContains(String  criteria);
    Collection<BaseObject> findALCityObjectByObjectCategoryAndTitleIgnoreCase(ObjectCategory category, String title);
    Optional<BaseObject> findByIcon(BinaryContent icon);
    Optional<BaseObject> findByPic(BinaryContent pic);

    Optional<BaseObject> findByTitle(String title);

}
