package com.alcity.entity.journey;


import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.base.BaseTable;
import com.alcity.entity.appmember.AppMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor

@Table(name="Journey")
public class Journey  extends BaseTable implements Serializable {
    @Column(name="title",unique = true)
    private String title;
    @Column(name="ordering",unique = true)
    private Integer ordering;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pic_id", nullable = true)
    @JsonIgnore
    private BinaryContent pic;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "button_passed_icon_id", nullable = false)
    @JsonIgnore
    private BinaryContent buttonPassedIcon;


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
