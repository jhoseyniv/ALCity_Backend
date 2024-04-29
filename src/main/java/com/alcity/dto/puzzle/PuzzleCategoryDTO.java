package com.alcity.dto.puzzle;

import com.alcity.dto.base.BaseItemSetDTO;

import java.util.Collection;

public class PuzzleCategoryDTO extends BaseItemSetDTO {
    private Collection<PGDTO> puzzleGroupDTOCollection;

    public PuzzleCategoryDTO() {
    }

    public Collection<PGDTO> getPuzzleGroupDTOCollection() {
        return puzzleGroupDTOCollection;
    }

    public void setPuzzleGroupDTOCollection(Collection<PGDTO> puzzleGroupDTOCollection) {
        this.puzzleGroupDTOCollection = puzzleGroupDTOCollection;
    }
}
