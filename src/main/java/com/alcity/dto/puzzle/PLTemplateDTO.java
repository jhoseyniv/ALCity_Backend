package com.alcity.dto.puzzle;

public class PLTemplateDTO {

    private Long id;
    private String title;

    private Long puzzleCategoryId;
    private StringBuffer content;

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

    public Long getPuzzleCategoryId() {
        return puzzleCategoryId;
    }

    public void setPuzzleCategoryId(Long puzzleCategoryId) {
        this.puzzleCategoryId = puzzleCategoryId;
    }

    public StringBuffer getContent() {
        return content;
    }

    public void setContent(StringBuffer content) {
        this.content = content;
    }

    public PLTemplateDTO() {
    }

    public PLTemplateDTO(Long id, String title, Long puzzleCategoryId, StringBuffer content) {
        this.id = id;
        this.title = title;
        this.puzzleCategoryId = puzzleCategoryId;
        this.content = content;
    }
}
