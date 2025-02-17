package com.alcity.dto.puzzle;

public class CityObjectInPLDTO {
    private Long id;
    private String name;
    private Integer row;
    private Integer col;
    private Integer zorder;
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

    public Integer getZorder() {
        return zorder;
    }

    public void setZorder(Integer zorder) {
        this.zorder = zorder;
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

    public CityObjectInPLDTO(Long id,String name, Integer row, Integer col, Integer zorder, Long alCityObjectInPGId, Long puzzleLevelId) {
        this.id = id;
        this.name = name;
        this.row = row;
        this.col = col;
        this.zorder = zorder;
        this.alCityObjectInPGId = alCityObjectInPGId;
        this.puzzleLevelId = puzzleLevelId;
    }
}
