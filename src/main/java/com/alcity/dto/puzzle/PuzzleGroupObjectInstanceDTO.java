package com.alcity.dto.puzzle;

public class PuzzleGroupObjectInstanceDTO {

    private Long id;
    private Long version;
    private String created;
    private String updated;
    private String createdBy;

    private String updatedBy;

    private Integer row;
    private Integer col;
    private Integer zOrder;
    private ALCityObjectInPuzzleGroupDTO puzzleGroup_puzzleObjectDTO;

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

    public ALCityObjectInPuzzleGroupDTO getPuzzleGroup_puzzleObjectDTO() {
        return puzzleGroup_puzzleObjectDTO;
    }

    public void setPuzzleGroup_puzzleObjectDTO(ALCityObjectInPuzzleGroupDTO puzzleGroup_puzzleObjectDTO) {
        this.puzzleGroup_puzzleObjectDTO = puzzleGroup_puzzleObjectDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public PuzzleGroupObjectInstanceDTO(Long id, Long version, String created, String updated, String createdBy, String updatedBy,
                                        Integer row, Integer col, Integer zOrder) {
        this.id = id;
        this.version = version;
        this.created = created;
        this.updated = updated;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.row = row;
        this.col = col;
        this.zOrder = zOrder;
    }

}
