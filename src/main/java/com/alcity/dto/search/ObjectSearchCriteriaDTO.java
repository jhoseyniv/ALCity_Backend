package com.alcity.dto.search;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ObjectSearchCriteriaDTO {

    public String title;
    private Long objectCategoryId;
    private Long puzzleGroupdId;
    private Long lastIndex;
    private Integer pageSize;

     public ObjectSearchCriteriaDTO(String title, Long objectCategoryId, Long puzzleGroupdId, Long lastIndex, Integer pageSize) {
        this.title = title;
        this.objectCategoryId = objectCategoryId;
        this.puzzleGroupdId = puzzleGroupdId;
        this.lastIndex = lastIndex;
        this.pageSize = pageSize;
    }
}
