package com.alcity.dto.puzzle;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class PGObjectDTO {

    private Long id;
    private String title;
    private String code;

    private String puzzleGroup;

    private Long puzzleGroupId;

    private Long objectId;

    private String objectTitle;

    public PGObjectDTO(Long id, String title, String code, String puzzleGroup, Long puzzleGroupId,
                       Long objectId, String objectTitle  ) {
        this.id = id;
        this.title = title;
        this.code = code;
        this.puzzleGroup = puzzleGroup;
        this.puzzleGroupId = puzzleGroupId;
        this.objectId = objectId;
        this.objectTitle = objectTitle;
    }
}
