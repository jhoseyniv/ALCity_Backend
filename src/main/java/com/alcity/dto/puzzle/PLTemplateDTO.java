package com.alcity.dto.puzzle;

public class PLTemplateDTO {

    private Long id;
    private String title;
    private String url;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public StringBuffer getContent() {
        return content;
    }

    public void setContent(StringBuffer content) {
        this.content = content;
    }

    public PLTemplateDTO() {
    }

    public PLTemplateDTO(Long id, String title, String url, StringBuffer content) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.content = content;
    }
}
