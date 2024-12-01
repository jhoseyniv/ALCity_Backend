package com.alcity.dto.base;

public class ContentSearchCriteriaDTO {

    public String criteria;
    private Integer contentTypeId;
    private Long lastIndex;
    private Integer pageSize;


    public Integer getContentTypeId() {
        return contentTypeId;
    }

    public void setContentTypeId(Integer contentTypeId) {
        this.contentTypeId = contentTypeId;
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

    public ContentSearchCriteriaDTO() {
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public ContentSearchCriteriaDTO(String criteria, String tag1, String tag2, String tag3, Integer contentTypeId, Long lastIndex, Integer pageSize) {
        this.criteria = criteria;
        this.contentTypeId = contentTypeId;
        this.lastIndex = lastIndex;
        this.pageSize = pageSize;
    }
}
