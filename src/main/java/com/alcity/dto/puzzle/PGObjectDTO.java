package com.alcity.dto.puzzle;

public class PGObjectDTO {

    private Long id;
    private String title;
    private String code;

    private String puzzleGroup;

    private Long puzzleGroupId;

    private Long alCityObjectId;

    private String alCityObjectTitle;

    public String getAlCityObjectTitle() {
        return alCityObjectTitle;
    }

    public void setAlCityObjectTitle(String alCityObjectTitle) {
        this.alCityObjectTitle = alCityObjectTitle;
    }

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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getPuzzleGroup() {
        return puzzleGroup;
    }

    public void setPuzzleGroup(String puzzleGroup) {
        this.puzzleGroup = puzzleGroup;
    }

    public Long getPuzzleGroupId() {
        return puzzleGroupId;
    }

    public void setPuzzleGroupId(Long puzzleGroupId) {
        this.puzzleGroupId = puzzleGroupId;
    }

    public PGObjectDTO() {
    }


    public Long getAlCityObjectId() {
        return alCityObjectId;
    }

    public void setAlCityObjectId(Long alCityObjectId) {
        this.alCityObjectId = alCityObjectId;
    }


    public PGObjectDTO(Long id, String title, String code, String puzzleGroup, Long puzzleGroupId, Long alCityObjectId, String alCityObjectTitle
                             ) {
        this.id = id;
        this.title = title;
        this.code = code;
        this.puzzleGroup = puzzleGroup;
        this.puzzleGroupId = puzzleGroupId;
        this.alCityObjectId = alCityObjectId;
        this.alCityObjectTitle = alCityObjectTitle;
    }
}
