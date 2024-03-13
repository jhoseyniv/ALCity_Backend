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
    @Column(name="title")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "binary_content_id", nullable = false)
    @JsonIgnore
    private BinaryContent graphic;

    @OneToMany(mappedBy = "journey", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<JourneyStep> journeyStepSet;

    @OneToMany(mappedBy = "journey", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<JourneyLearningSkill> journeyLearningSkillSet;

    public Journey() {
    }

    public Journey(String title, BinaryContent graphic,Long version, Long created, Long updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.title = title;
        this.graphic = graphic;
    }
}
