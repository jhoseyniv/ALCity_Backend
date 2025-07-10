package com.alcity.dto.puzzle;

import java.io.Serializable;

public class PLTemplateDraftDTO implements Serializable {
    private Long id;
    private String title;
    private StringBuffer jsonTemplate;
    private Long puzzleLevelId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public StringBuffer getJsonTemplate() {
        return jsonTemplate;
    }

    public void setJsonTemplate(StringBuffer jsonTemplate) {
        this.jsonTemplate = jsonTemplate;
    }

    public Long getPuzzleLevelId() {
        return puzzleLevelId;
    }

    public void setPuzzleLevelId(Long puzzleLevelId) {
        this.puzzleLevelId = puzzleLevelId;
    }

    public PLTemplateDraftDTO() {
    }

    public PLTemplateDraftDTO(Long id, String title, StringBuffer jsonTemplate, Long puzzleLevelId) {
        this.id = id;
        this.title = title;
        this.jsonTemplate = jsonTemplate;
        this.puzzleLevelId = puzzleLevelId;
    }
}
