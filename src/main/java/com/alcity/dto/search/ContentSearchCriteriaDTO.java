package com.alcity.dto.search;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ContentSearchCriteriaDTO {

    public String criteria;
    private Integer contentTypeId;
    private Long lastIndex;
    private Integer pageSize;

    public ContentSearchCriteriaDTO(String criteria, String tag1, String tag2, String tag3, Integer contentTypeId, Long lastIndex, Integer pageSize) {
        this.criteria = criteria;
        this.contentTypeId = contentTypeId;
        this.lastIndex = lastIndex;
        this.pageSize = pageSize;
    }
}
