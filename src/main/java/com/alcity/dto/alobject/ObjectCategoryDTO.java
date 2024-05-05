package com.alcity.dto.alobject;

import com.alcity.dto.base.BaseItemSetDTO;
import com.alcity.entity.puzzle.PuzzleObject;

import java.util.Collection;

public class ObjectCategoryDTO extends BaseItemSetDTO {
    private Collection<PuzzleObject> puzzleObjectCollection;

    public Collection<PuzzleObject> getPuzzleObjectCollection() {
        return puzzleObjectCollection;
    }

    public void setPuzzleObjectCollection(Collection<PuzzleObject> puzzleObjectCollection) {
        this.puzzleObjectCollection = puzzleObjectCollection;
    }

    public ObjectCategoryDTO() {
    }

    public ObjectCategoryDTO(Long id, String label, String value, Long version, String created, String updated, Collection<PuzzleObject> puzzleObjectCollection) {
        super(id, label, value, version, created, updated);
        this.puzzleObjectCollection = puzzleObjectCollection;
    }
}
