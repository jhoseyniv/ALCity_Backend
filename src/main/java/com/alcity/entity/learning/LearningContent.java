package com.alcity.entity.learning;

import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.base.BaseTable;
import com.alcity.entity.appmember.AppMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

@Entity
public class LearningContent  extends BaseTable implements Serializable {


    @NotNull(message = "{bHeight.notempty}")
    private String descText;


    @NotNull(message = "{bHeight.notempty}")
    private String descBrief;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "binary_content_id")
    @JsonIgnore
    private BinaryContent binaryContent;

    public String getDescText() {
        return descText;
    }

    public void setDescText(String descText) {
        this.descText = descText;
    }

    public String getDescBrief() {
        return descBrief;
    }

    public void setDescBrief(String descBrief) {
        this.descBrief = descBrief;
    }

    public BinaryContent getBinaryContent() {
        return binaryContent;
    }

    public void setBinaryContent(BinaryContent binaryContent) {
        this.binaryContent = binaryContent;
    }

    public LearningContent() {
    }

    public LearningContent(String descText, String descBrief, BinaryContent binaryContent, Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.descText = descText;
        this.descBrief = descBrief;
        this.binaryContent = binaryContent;
    }
}
