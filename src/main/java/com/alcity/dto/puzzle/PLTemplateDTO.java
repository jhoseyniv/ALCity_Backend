package com.alcity.dto.puzzle;

public class PLTemplateDTO {

    private Long id;
    private String title;
    private Integer fromAge;
    private Integer toAge;

    private Long puzzleCategoryId;
    private Long puzzleGroupId;
    private Long puzzleLevelId;
    private StringBuffer content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getFromAge() {
        return fromAge;
    }

    public void setFromAge(Integer fromAge) {
        this.fromAge = fromAge;
    }

    public Integer getToAge() {
        return toAge;
    }

    public void setToAge(Integer toAge) {
        this.toAge = toAge;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getPuzzleCategoryId() {
        return puzzleCategoryId;
    }

    public void setPuzzleCategoryId(Long puzzleCategoryId) {
        this.puzzleCategoryId = puzzleCategoryId;
    }

    public StringBuffer getContent() {
        return content;
    }

    public void setContent(StringBuffer content) {
        this.content = content;
    }

    public Long getPuzzleGroupId() {
        return puzzleGroupId;
    }

    public void setPuzzleGroupId(Long puzzleGroupId) {
        this.puzzleGroupId = puzzleGroupId;
    }

    public Long getPuzzleLevelId() {
        return puzzleLevelId;
    }

    public void setPuzzleLevelId(Long puzzleLevelId) {
        this.puzzleLevelId = puzzleLevelId;
    }

    public PLTemplateDTO() {
    }

    public PLTemplateDTO(Long id, String title,Integer fromAge,Integer toAge, Long puzzleCategoryId,Long puzzleGroupId,Long puzzleLevelId, StringBuffer content) {
        this.id = id;
        this.title = title;
        this.fromAge = fromAge;
        this.toAge = toAge;
        this.puzzleCategoryId = puzzleCategoryId;
        this.puzzleGroupId = puzzleGroupId;
        this.puzzleLevelId = puzzleLevelId;
        this.content = content;
    }
}
