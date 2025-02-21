package com.alcity.dto.search;

public class SearchResultCityObjectDTO {
    private Long id;
    private String title;

    private Long categoryId;
    private String category;

    private Long puzzleGroupId;
    private String puzzleGroupTitle;

    private Long alcityObjectInPGId;
    private String alcityObjectInPGTitle;

    private Long pictureId;
    private Long iconId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getPuzzleGroupId() {
        return puzzleGroupId;
    }

    public void setPuzzleGroupId(Long puzzleGroupId) {
        this.puzzleGroupId = puzzleGroupId;
    }

    public String getPuzzleGroupTitle() {
        return puzzleGroupTitle;
    }

    public void setPuzzleGroupTitle(String puzzleGroupTitle) {
        this.puzzleGroupTitle = puzzleGroupTitle;
    }

    public Long getPictureId() {
        return pictureId;
    }

    public void setPictureId(Long pictureId) {
        this.pictureId = pictureId;
    }

    public Long getIconId() {
        return iconId;
    }

    public void setIconId(Long iconId) {
        this.iconId = iconId;
    }

    public SearchResultCityObjectDTO() {
    }

    public Long getAlcityObjectInPGId() {
        return alcityObjectInPGId;
    }

    public void setAlcityObjectInPGId(Long alcityObjectInPGId) {
        this.alcityObjectInPGId = alcityObjectInPGId;
    }

    public String getAlcityObjectInPGTitle() {
        return alcityObjectInPGTitle;
    }

    public void setAlcityObjectInPGTitle(String alcityObjectInPGTitle) {
        this.alcityObjectInPGTitle = alcityObjectInPGTitle;
    }

    public SearchResultCityObjectDTO(Long id, String title, Long categoryId, String category, Long puzzleGroupId, String puzzleGroupTitle,
                                     Long alcityObjectInPGId, String alcityObjectInPGTitle, Long pictureId, Long iconId) {
        this.id = id;
        this.title = title;
        this.categoryId = categoryId;
        this.category = category;
        this.puzzleGroupId = puzzleGroupId;
        this.puzzleGroupTitle = puzzleGroupTitle;
        this.alcityObjectInPGId = alcityObjectInPGId;
        this.alcityObjectInPGTitle = alcityObjectInPGTitle;
        this.pictureId = pictureId;
        this.iconId = iconId;
    }
}
