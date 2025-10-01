package com.alcity.dto.search;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
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
