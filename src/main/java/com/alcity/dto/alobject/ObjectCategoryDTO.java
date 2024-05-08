package com.alcity.dto.alobject;

import com.alcity.dto.base.BaseItemSetDTO;
import com.alcity.entity.puzzle.ALCityObject;

import java.util.Collection;

public class ObjectCategoryDTO extends BaseItemSetDTO {
    private Collection<ALCityObject> puzzleObjectCollection;

    public Collection<ALCityObject> getPuzzleObjectCollection() {
        return puzzleObjectCollection;
    }

    public void setPuzzleObjectCollection(Collection<ALCityObject> puzzleObjectCollection) {
        this.puzzleObjectCollection = puzzleObjectCollection;
    }

    public ObjectCategoryDTO() {
    }

    public ObjectCategoryDTO(Long id, String label, String value, Long version, String created, String updated, Collection<ALCityObject> puzzleObjectCollection) {
        super(id, label, value, version, created, updated);
        this.puzzleObjectCollection = puzzleObjectCollection;
    }
}
