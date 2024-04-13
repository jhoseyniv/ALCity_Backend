package com.alcity.entity.puzzle;


import com.alcity.entity.base.*;
import com.alcity.entity.journey.JourneyStep;
import com.alcity.entity.play.PermitedPlayer;
import com.alcity.entity.users.ApplicationMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

@Entity
public class PuzzleLevel extends BaseTable implements Serializable {

    @Column(name="approveDate")
    private Long approveDate;

    @Column(name="ordering")
    private Long ordering;

    @Column(name="title")
    private String title;

    @Column(name="code")
    private String code;


    @Column(name="fromAge")
    private Integer fromAge;

    @Column(name="toAge")
    private Integer toAge;

    @Column(name="maxScore")
    private Float maxScore;

    public Long getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Long approveDate) {
        this.approveDate = approveDate;
    }

    public Long getOrdering() {
        return ordering;
    }

    public void setOrdering(Long ordering) {
        this.ordering = ordering;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getFromAge() {
        return fromAge;
    }

    public void setFromAge(Integer fromAge) {
        this.fromAge = fromAge;
    }

    public Integer getToAge() {
        return toAge;
    }

    public void setToAge(Integer toAge) {
        this.toAge = toAge;
    }

    public Float getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Float maxScore) {
        this.maxScore = maxScore;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "puzzle_group_id", nullable = false)
    @JsonIgnore
    private PuzzleGroup puzzleGroup;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "puzzle_difficulty_id", nullable = false)
    @JsonIgnore
    private PuzzleLevelDifficulty puzzleDifficulty;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "puzzle_level_status_id", nullable = false)
    @JsonIgnore
    private PuzzleLevelStatus puzzleLevelStatus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "puzzle_level_privacy_id", nullable = false)
    @JsonIgnore
    private PuzzleLevelPrivacy puzzleLevelPrivacy;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "picture_id", nullable = true)
    @JsonIgnore
    private BinaryContent picture;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "icon_id", nullable = true)
    @JsonIgnore
    private BinaryContent icon;

    @OneToMany(mappedBy = "puzzleLevel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<PuzzleLevelRule> puzzleLevelRuleSet;

    @OneToMany(mappedBy = "puzzleLevel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Collection<PuzzleLevelObjective> puzzleLevelObjectiveCollection;

    @OneToMany(mappedBy = "puzzleLevel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Collection<PuzzleLevelGround> puzzleLevelGroundCollection;

    @OneToMany(mappedBy = "puzzleLevel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Collection<PermitedPlayer> permitedPlayerCollection;

    public Collection<PermitedPlayer> getPermitedPlayerCollection() {
        return permitedPlayerCollection;
    }

    public void setPermitedPlayerCollection(Collection<PermitedPlayer> permitedPlayerCollection) {
        this.permitedPlayerCollection = permitedPlayerCollection;
    }

    public Collection<PuzzleLevelGround> getPuzzleLevelGroundCollection() {
        return puzzleLevelGroundCollection;
    }

    public void setPuzzleLevelGroundCollection(Collection<PuzzleLevelGround> puzzleLevelGroundCollection) {
        this.puzzleLevelGroundCollection = puzzleLevelGroundCollection;
    }

    public Collection<PuzzleLevelObjective> getPuzzleLevelObjectiveCollection() {
        return puzzleLevelObjectiveCollection;
    }

    public void setPuzzleLevelObjectiveCollection(Collection<PuzzleLevelObjective> puzzleLevelObjectiveCollection) {
        this.puzzleLevelObjectiveCollection = puzzleLevelObjectiveCollection;
    }

    public PuzzleGroup getPuzzleGroup() {
        return puzzleGroup;
    }

    public void setPuzzleGroup(PuzzleGroup puzzleGroup) {
        this.puzzleGroup = puzzleGroup;
    }

    public PuzzleLevelDifficulty getPuzzleDifficulty() {
        return puzzleDifficulty;
    }

    public void setPuzzleDifficulty(PuzzleLevelDifficulty puzzleDifficulty) {
        this.puzzleDifficulty = puzzleDifficulty;
    }

    public PuzzleLevelStatus getPuzzleLevelStatus() {
        return puzzleLevelStatus;
    }

    public void setPuzzleLevelStatus(PuzzleLevelStatus puzzleLevelStatus) {
        this.puzzleLevelStatus = puzzleLevelStatus;
    }

    public PuzzleLevelPrivacy getPuzzleLevelPrivacy() {
        return puzzleLevelPrivacy;
    }

    public void setPuzzleLevelPrivacy(PuzzleLevelPrivacy puzzleLevelPrivacy) {
        this.puzzleLevelPrivacy = puzzleLevelPrivacy;
    }

    public BinaryContent getPicture() {
        return picture;
    }

    public void setPicture(BinaryContent picture) {
        this.picture = picture;
    }

    public BinaryContent getIcon() {
        return icon;
    }

    public void setIcon(BinaryContent icon) {
        this.icon = icon;
    }

    public Set<PuzzleLevelRule> getPuzzleLevelRuleSet() {
        return puzzleLevelRuleSet;
    }

    public void setPuzzleLevelRuleSet(Set<PuzzleLevelRule> puzzleLevelRuleSet) {
        this.puzzleLevelRuleSet = puzzleLevelRuleSet;
    }

    @OneToMany(mappedBy = "puzzleLevel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Collection<PuzzleLevel_LearningTopic> puzzleLevel_learningTopics;

    public Collection<PuzzleLevel_LearningTopic> getPuzzleLevel_learningTopics() {
        return puzzleLevel_learningTopics;
    }

    public void setPuzzleLevel_learningTopics(Collection<PuzzleLevel_LearningTopic> puzzleLevel_learningTopics) {
        this.puzzleLevel_learningTopics = puzzleLevel_learningTopics;
    }

    @OneToMany(mappedBy = "puzzleLevel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Collection<PuzzleGroupObjectInstance> puzzleGroupObjectInstanceCollection;

    public Collection<PuzzleGroupObjectInstance> getPuzzleGroupObjectInstanceCollection() {
        return puzzleGroupObjectInstanceCollection;
    }

    public void setPuzzleGroupObjectInstanceCollection(Collection<PuzzleGroupObjectInstance> puzzleGroupObjectInstanceCollection) {
        this.puzzleGroupObjectInstanceCollection = puzzleGroupObjectInstanceCollection;
    }

    public PuzzleLevel() {
    }

    public PuzzleLevel( Long approveDate, Long ordering, String title, String code, Integer fromAge, Integer toAge, Float maxScore, PuzzleGroup puzzleGroup, PuzzleLevelDifficulty puzzleDifficulty, PuzzleLevelStatus puzzleLevelStatus,PuzzleLevelPrivacy puzzleLevelPrivacy,BinaryContent picture,BinaryContent icon,Long version, Long created, Long updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.approveDate = approveDate;
        this.ordering = ordering;
        this.title = title;
        this.code = code;
        this.fromAge = fromAge;
        this.toAge = toAge;
        this.maxScore = maxScore;
        this.puzzleGroup = puzzleGroup;
        this.puzzleDifficulty = puzzleDifficulty;
        this.puzzleLevelStatus = puzzleLevelStatus;
        this.puzzleLevelPrivacy = puzzleLevelPrivacy;
        this.picture = picture;
        this.icon = icon;
    }
}
