package com.alcity.dto.search;

public class AppMemberSearchCriteriaDTO {
    public String criteria;
    private Integer age;

    private Long lastIndex;
    private Integer pageSize;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public AppMemberSearchCriteriaDTO() {
    }

    public AppMemberSearchCriteriaDTO(String criteria,Integer age, Long lastIndex, Integer pageSize) {
        this.criteria = criteria;
        this.age = age;
        this.lastIndex = lastIndex;
        this.pageSize = pageSize;
    }
}
