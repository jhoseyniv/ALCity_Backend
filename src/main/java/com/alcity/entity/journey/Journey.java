package com.alcity.entity.journey;


import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.base.BaseTable;
import com.alcity.entity.users.ApplicationMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name="Journey")
public class Journey  extends BaseTable implements Serializable {
    @Column(name="title",unique = true)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "binary_content_id", nullable = false)
    @JsonIgnore
    private BinaryContent graphic;

    @OneToMany(mappedBy = "journey", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Collection<JourneyStep> journeyStepCollection;

    @OneToMany(mappedBy = "journey", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Collection<JourneyLearningSkill> journeyLearningSkillCollection;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BinaryContent getGraphic() {
        return graphic;
    }

    public void setGraphic(BinaryContent graphic) {
        this.graphic = graphic;
    }

    public Collection<JourneyStep> getJourneyStepCollection() {
        return journeyStepCollection;
    }

    public void setJourneyStepCollection(Collection<JourneyStep> journeyStepCollection) {
        this.journeyStepCollection = journeyStepCollection;
    }

    public Collection<JourneyLearningSkill> getJourneyLearningSkillCollection() {
        return journeyLearningSkillCollection;
    }

    public void setJourneyLearningSkillCollection(Collection<JourneyLearningSkill> journeyLearningSkillCollection) {
        this.journeyLearningSkillCollection = journeyLearningSkillCollection;
    }

    public Journey() {
    }

    public Journey(String title, BinaryContent graphic,Long version, String created, String updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.title = title;
        this.graphic = graphic;
    }
}
