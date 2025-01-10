package com.alcity.entity.journey;


import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.base.BaseTable;
import com.alcity.entity.appmember.AppMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name="Journey")
public class Journey  extends BaseTable implements Serializable {
    @Column(name="title",unique = true)
    private String title;
    @Column(name="ordering",unique = true)
    private Integer ordering;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "binary_content_id", nullable = true)
    @JsonIgnore
    private BinaryContent graphic;


    @OneToMany(mappedBy = "journey", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Collection<JourneyStep> journeyStepCollection;

    @OneToMany(mappedBy = "journey", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<JourneyLearningSkill> journeyLearningSkillCollection;

    @OneToMany(mappedBy = "journey", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<RoadMap> roadMaps;

    @Column(name="maxStar",unique = true)
    private Integer maxStar;

    @Column(name="minStar",unique = true)
    private Integer minStar;

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

    public Integer getMaxStar() {
        return maxStar;
    }

    public void setMaxStar(Integer maxStar) {
        this.maxStar = maxStar;
    }

    public Integer getMinStar() {
        return minStar;
    }

    public void setMinStar(Integer minStar) {
        this.minStar = minStar;
    }

    public Collection<RoadMap> getRoadMaps() {
        return roadMaps;
    }

    public void setRoadMaps(Collection<RoadMap> roadMaps) {
        this.roadMaps = roadMaps;
    }

    public Integer getOrdering() {
        return ordering;
    }

    public void setOrdering(Integer ordering) {
        this.ordering = ordering;
    }

    public Journey() {
    }

    public Journey(String title,Integer ordering,Integer minStar,Integer maxStar, BinaryContent graphic, Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.title = title;
        this.ordering = ordering;
        this.minStar = minStar;
        this.maxStar = maxStar;
        this.graphic = graphic;
    }
}
