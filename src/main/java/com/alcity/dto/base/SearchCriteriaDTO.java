package com.alcity.dto.base;

public class SearchCriteriaDTO {

    public String fileName;

    public String tag1;
    public String tag2;
    public String tag3;
    private Integer contentTypeId;
    private Long lastIndex;
    private Integer pageSize;

    public String getTag1() {
        return tag1;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }

    public String getTag2() {
        return tag2;
    }

    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }

    public String getTag3() {
        return tag3;
    }

    public void setTag3(String tag3) {
        this.tag3 = tag3;
    }

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

    public SearchCriteriaDTO() {
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public SearchCriteriaDTO(String fileName,String tag1, String tag2, String tag3, Integer contentTypeId, Long lastIndex, Integer pageSize) {
        this.fileName = fileName;
        this.tag1 = tag1;
        this.tag2 = tag2;
        this.tag3 = tag3;
        this.contentTypeId = contentTypeId;
        this.lastIndex = lastIndex;
        this.pageSize = pageSize;
    }
}
