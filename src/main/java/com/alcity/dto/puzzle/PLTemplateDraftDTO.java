package com.alcity.dto.puzzle;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor
public class PLTemplateDraftDTO implements Serializable {
    private Long id;
    private String title;
    private StringBuffer jsonTemplate;
    private Long puzzleLevelId;

    public PLTemplateDraftDTO(Long id, String title, StringBuffer jsonTemplate, Long puzzleLevelId) {
        this.id = id;
        this.title = title;
        this.jsonTemplate = jsonTemplate;
        this.puzzleLevelId = puzzleLevelId;
    }
}
