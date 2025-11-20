package com.alcity.dto.search;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AppMemberSearchCriteriaDTO {
    public String criteria;
    private Integer age;

    private Long lastIndex;
    private Integer pageSize;

    public AppMemberSearchCriteriaDTO(String criteria,Integer age, Long lastIndex, Integer pageSize) {
        this.criteria = criteria;
        this.age = age;
        this.lastIndex = lastIndex;
        this.pageSize = pageSize;
    }
    public String toString() {
        return "criteria: '" + this.criteria + "', age: '" + this.age + "', lastIndex: '" + this.lastIndex + "'"+
                "', pageSize: '" + this.pageSize + "'";
    }
}
