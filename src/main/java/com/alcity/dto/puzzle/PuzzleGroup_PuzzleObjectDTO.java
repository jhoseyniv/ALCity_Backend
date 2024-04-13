package com.alcity.dto.puzzle;

import com.alcity.dto.base.BaseTableDTO;
import com.alcity.entity.puzzle.PuzzleObject;

public class PuzzleGroup_PuzzleObjectDTO extends BaseTableDTO {
    private String title;
    private String code;
    private PuzzleObjectDTO puzzleObjectDTO;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public PuzzleObjectDTO getPuzzleObjectDTO() {
        return puzzleObjectDTO;
    }

    public void setPuzzleObjectDTO(PuzzleObjectDTO puzzleObjectDTO) {
        this.puzzleObjectDTO = puzzleObjectDTO;
    }

    public PuzzleGroup_PuzzleObjectDTO() {
    }
}
