package com.alcity.dto.alobject;

import com.alcity.dto.base.BaseItemSetDTO;
import com.alcity.entity.puzzle.BaseObject;

import java.util.Collection;

public class ObjectCategoryDTO extends BaseItemSetDTO {
    private Collection<BaseObject> puzzleObjectCollection;

    public Collection<BaseObject> getPuzzleObjectCollection() {
        return puzzleObjectCollection;
    }

    public void setPuzzleObjectCollection(Collection<BaseObject> puzzleObjectCollection) {
        this.puzzleObjectCollection = puzzleObjectCollection;
    }

    public ObjectCategoryDTO() {
    }

    public ObjectCategoryDTO(Long id, String label, String value, Long version, String created, String updated, Collection<BaseObject> puzzleObjectCollection) {
        super(id, label, value, version, created, updated);
        this.puzzleObjectCollection = puzzleObjectCollection;
    }
}
