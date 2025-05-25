package com.alcity.entity.puzzle;

import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.base.BaseTable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.io.Serializable;

@Entity
public class PLTemplate extends BaseTable implements Serializable {

    @Column(name="title" ,unique = true)
    private String title;

    @Column(name="url" ,unique = true)
    private String url;

    @Column(name="content")
    private StringBuffer content;

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

    public PLTemplate() {
    }

    public PLTemplate(String title, String url, StringBuffer content ,Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.title = title;
        this.url = url;
        this.content = content;
    }
}
