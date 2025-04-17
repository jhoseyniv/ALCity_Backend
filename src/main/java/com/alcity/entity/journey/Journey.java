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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pic_id")
    private BinaryContent pic;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "button_passed_icon_id", nullable = false)
    @JsonIgnore
    private BinaryContent buttonPassedIcon;

    public BinaryContent getPic() {
        return pic;
    }

    public void setPic(BinaryContent pic) {
        this.pic = pic;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "button_current_icon_id", nullable = false)
    @JsonIgnore
    private BinaryContent buttonCurrenIcon;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "button_locked_icon_id", nullable = false)
    @JsonIgnore
    private BinaryContent buttonLockedIcon;


    @OneToMany(mappedBy = "journey", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Collection<JourneyStep> journeyStepCollection;

    @OneToMany(mappedBy = "journey", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<JourneyLearningSkill> journeyLearningSkillCollection;

    @OneToMany(mappedBy = "journey", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<RoadMap> roadMaps;

    @Column(name="minToPassStar")
    private Integer minToPassStar;

    @Column(name="minToOpenStar")
    private Integer minToOpenStar;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public BinaryContent getButtonPassedIcon() {
        return buttonPassedIcon;
    }

    public void setButtonPassedIcon(BinaryContent buttonPassedIcon) {
        this.buttonPassedIcon = buttonPassedIcon;
    }

    public BinaryContent getButtonCurrenIcon() {
        return buttonCurrenIcon;
    }

    public void setButtonCurrenIcon(BinaryContent buttonCurrenIcon) {
        this.buttonCurrenIcon = buttonCurrenIcon;
    }

    public BinaryContent getButtonLockedIcon() {
        return buttonLockedIcon;
    }

    public void setButtonLockedIcon(BinaryContent buttonLockedIcon) {
        this.buttonLockedIcon = buttonLockedIcon;
    }

    public Integer getMinToPassStar() {
        return minToPassStar;
    }

    public void setMinToPassStar(Integer minToPassStar) {
        this.minToPassStar = minToPassStar;
    }

    public Integer getMinToOpenStar() {
        return minToOpenStar;
    }

    public void setMinToOpenStar(Integer minToOpenStar) {
        this.minToOpenStar = minToOpenStar;
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

    public Journey(Long version, String created, String updated, AppMember createdBy, AppMember updatedBy, String title, Integer ordering, BinaryContent pic, BinaryContent buttonPassedIcon, BinaryContent buttonCurrenIcon, BinaryContent buttonLockedIcon,  Integer minToPassStar, Integer minToOpenStar) {
        super(version, created, updated, createdBy, updatedBy);
        this.title = title;
        this.ordering = ordering;
        this.pic = pic;
        this.buttonPassedIcon = buttonPassedIcon;
        this.buttonCurrenIcon = buttonCurrenIcon;
        this.buttonLockedIcon = buttonLockedIcon;
        this.minToPassStar = minToPassStar;
        this.minToOpenStar = minToOpenStar;
    }
}
