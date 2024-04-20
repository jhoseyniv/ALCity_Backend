package com.alcity.dto.puzzle;

import com.alcity.dto.base.BaseItemSetDTO;

import java.util.Collection;

public class PuzzleCategoryDTO extends BaseItemSetDTO {
    private Collection<PGDTO> puzzleGroupDTOSet;

    public PuzzleCategoryDTO() {
    }

    public Collection<PGDTO> getPuzzleGroupDTOSet() {
        return puzzleGroupDTOSet;
    }

    public void setPuzzleGroupDTOSet(Collection<PGDTO> puzzleGroupDTOSet) {
        this.puzzleGroupDTOSet = puzzleGroupDTOSet;
    }
}
