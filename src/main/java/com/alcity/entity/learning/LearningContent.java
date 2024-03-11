package com.alcity.entity.learning;

import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.base.RecordInformation;
import com.alcity.entity.journey.Journey;
import com.alcity.entity.users.ApplicationMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class LearningContent  extends RecordInformation implements Serializable {


    @NotNull(message = "{bHeight.notempty}")
    private String descText;


    @NotNull(message = "{bHeight.notempty}")
    private String descBrief;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "binary_content_id", nullable = true)
    @JsonIgnore
    private BinaryContent binaryContent;

    public LearningContent() {
    }

    public LearningContent(String descText, String descBrief, BinaryContent binaryContent, Long version, Long created, Long updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.descText = descText;
        this.descBrief = descBrief;
        this.binaryContent = binaryContent;
    }
}
