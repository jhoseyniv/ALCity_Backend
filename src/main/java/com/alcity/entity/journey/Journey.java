package com.alcity.entity.journey;


import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.base.BaseTable;
import com.alcity.entity.users.ApplicationMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
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

    @OneToMany(mappedBy = "journey", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<JourneyStep> journeyStepSet;

    @OneToMany(mappedBy = "journey", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<JourneyLearningSkill> journeyLearningSkillSet;

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

    public Set<JourneyStep> getJourneyStepSet() {
        return journeyStepSet;
    }

    public void setJourneyStepSet(Set<JourneyStep> journeyStepSet) {
        this.journeyStepSet = journeyStepSet;
    }

    public Set<JourneyLearningSkill> getJourneyLearningSkillSet() {
        return journeyLearningSkillSet;
    }

    public void setJourneyLearningSkillSet(Set<JourneyLearningSkill> journeyLearningSkillSet) {
        this.journeyLearningSkillSet = journeyLearningSkillSet;
    }

    public Journey() {
    }

    public Journey(String title, BinaryContent graphic,Long version, Long created, Long updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.title = title;
        this.graphic = graphic;
    }
}
