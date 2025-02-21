package com.alcity.dto.puzzle;

public class CityObjectInPLDTO {
    private Long id;
    private String name;
    private Integer row;
    private Integer col;
    private Integer zOrder;
    private Long alCityObjectInPGId;
    private Long puzzleLevelId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Long getAlCityObjectInPGId() {
        return alCityObjectInPGId;
    }

    public void setAlCityObjectInPGId(Long alCityObjectInPGId) {
        this.alCityObjectInPGId = alCityObjectInPGId;
    }

    public Long getPuzzleLevelId() {
        return puzzleLevelId;
    }

    public void setPuzzleLevelId(Long puzzleLevelId) {
        this.puzzleLevelId = puzzleLevelId;
    }

    public CityObjectInPLDTO() {
    }

    public CityObjectInPLDTO(Long id,String name, Integer row, Integer col, Integer zOrder, Long alCityObjectInPGId, Long puzzleLevelId) {
        this.id = id;
        this.name = name;
        this.row = row;
        this.col = col;
        this.zOrder = zOrder;
        this.alCityObjectInPGId = alCityObjectInPGId;
        this.puzzleLevelId = puzzleLevelId;
    }
}
