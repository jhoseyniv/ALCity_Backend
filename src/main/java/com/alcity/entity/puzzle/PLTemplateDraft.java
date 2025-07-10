package com.alcity.entity.puzzle;


import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.base.BaseTable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.io.Serializable;

@Entity
public class PLTemplateDraft extends BaseTable implements Serializable {
    @Column(name="title" ,unique = true)
    private String title;

    @Column(name="jsonTemplate")
    private StringBuffer jsonTemplate;

    @Column(name="puzzleLevelId")
    private Long puzzleLevelId;

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

    public PLTemplateDraft() {
    }

    public PLTemplateDraft(String title, StringBuffer jsonTemplate, Long puzzleLevelId ,
                           Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.title = title;
        this.jsonTemplate = jsonTemplate;
        this.puzzleLevelId = puzzleLevelId;
    }
}
