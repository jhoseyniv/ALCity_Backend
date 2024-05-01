package com.alcity.dto.puzzle;

public class PuzzleGroupObjectInstanceDTO extends BaseTableDTO {
    private Integer row;
    private Integer col;
    private Integer zOrder;
    private PuzzleGroup_PuzzleObjectDTO puzzleGroup_puzzleObjectDTO;

    public PuzzleGroupObjectInstanceDTO() {
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getCol() {
        return col;
    }

    public void setCol(Integer col) {
        this.col = col;
    }

    public Integer getzOrder() {
        return zOrder;
    }

    public void setzOrder(Integer zOrder) {
        this.zOrder = zOrder;
    }

    public PuzzleGroup_PuzzleObjectDTO getPuzzleGroup_puzzleObjectDTO() {
        return puzzleGroup_puzzleObjectDTO;
    }

    public void setPuzzleGroup_puzzleObjectDTO(PuzzleGroup_PuzzleObjectDTO puzzleGroup_puzzleObjectDTO) {
        this.puzzleGroup_puzzleObjectDTO = puzzleGroup_puzzleObjectDTO;
    }
}
