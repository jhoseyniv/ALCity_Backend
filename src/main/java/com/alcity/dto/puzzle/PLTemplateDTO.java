package com.alcity.dto.puzzle;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PLTemplateDTO {

    private Long id;
    private String title;
    private Integer fromAge;
    private Integer toAge;

    private Long puzzleCategoryId;
    private Long puzzleGroupId;
    private Long puzzleLevelId;
    private StringBuffer content;

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
