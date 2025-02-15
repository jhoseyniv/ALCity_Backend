package com.alcity.dto.search;

public class ObjectSearchCriteriaDTO {

    public String title;
    private Long objectCategoryId;
    private Long puzzleGroupdId;
    private Long lastIndex;
    private Integer pageSize;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getObjectCategoryId() {
        return objectCategoryId;
    }

    public void setObjectCategoryId(Long objectCategoryId) {
        this.objectCategoryId = objectCategoryId;
    }

    public Long getPuzzleGroupdId() {
        return puzzleGroupdId;
    }

    public void setPuzzleGroupdId(Long puzzleGroupdId) {
        this.puzzleGroupdId = puzzleGroupdId;
    }

    public Long getLastIndex() {
        return lastIndex;
    }

    public void setLastIndex(Long lastIndex) {
        this.lastIndex = lastIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public ObjectSearchCriteriaDTO() {
    }

    public ObjectSearchCriteriaDTO(String title, Long objectCategoryId, Long puzzleGroupdId, Long lastIndex, Integer pageSize) {
        this.title = title;
        this.objectCategoryId = objectCategoryId;
        this.puzzleGroupdId = puzzleGroupdId;
        this.lastIndex = lastIndex;
        this.pageSize = pageSize;
    }
}
