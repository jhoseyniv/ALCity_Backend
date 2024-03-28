package com.alcity.dto.puzzle;

import com.alcity.dto.base.BaseItemSetDTO;
import com.alcity.entity.puzzle.PuzzleGroup;

import java.util.Collection;
import java.util.Set;

public class PuzzleCategoryDTO extends BaseItemSetDTO {
    private Collection<PuzzleGroupDTO> puzzleGroupDTOSet;

    public PuzzleCategoryDTO() {
    }

    public Collection<PuzzleGroupDTO> getPuzzleGroupDTOSet() {
        return puzzleGroupDTOSet;
    }

    public void setPuzzleGroupDTOSet(Collection<PuzzleGroupDTO> puzzleGroupDTOSet) {
        this.puzzleGroupDTOSet = puzzleGroupDTOSet;
    }
}
