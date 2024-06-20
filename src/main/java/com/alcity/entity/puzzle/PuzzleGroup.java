package com.alcity.entity.puzzle;


import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.base.PuzzleCategory;
import com.alcity.entity.base.BaseTable;
import com.alcity.entity.journey.JourneyStep;
import com.alcity.entity.users.ApplicationMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

@Entity
public class PuzzleGroup extends BaseTable implements Serializable {

    @Column(name="title" ,unique = true)
    private String title;


    @OneToMany(mappedBy = "puzzleGroup", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Collection<JourneyStep> journeyStepCollection;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "puzzle_category_id", nullable = false)
    @JsonIgnore
    private PuzzleCategory puzzleCategory;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "icon_content_id", nullable = false)
    @JsonIgnore
    private BinaryContent icon;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pic_content_id", nullable = false)
    @JsonIgnore
    private BinaryContent pic;


    @OneToMany(mappedBy = "puzzleGroup", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Collection<ALCityObjectInPG> alCityObjectInPGS;

    public Collection<ALCityObjectInPG> getAlCityObjectInPGS() {
        return alCityObjectInPGS;
    }

    public void setAlCityObjectInPGS(Collection<ALCityObjectInPG> alCityObjectInPGS) {
        this.alCityObjectInPGS = alCityObjectInPGS;
    }

    @OneToMany(mappedBy = "puzzleGroup", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Collection<LearningSkillContent> learningSkillContents;

    public Collection<LearningSkillContent> getLearningSkillContents() {
        return learningSkillContents;
    }

    public void setLearningSkillContents(Collection<LearningSkillContent> learningSkillContents) {
        this.learningSkillContents = learningSkillContents;
    }

    @OneToMany(mappedBy = "puzzleGroup", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Collection<PuzzleLevel> puzzleLevels;

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

    public BinaryContent getIcon() {
        return icon;
    }

    public void setIcon(BinaryContent icon) {
        this.icon = icon;
    }

    public BinaryContent getPic() {
        return pic;
    }

    public void setPic(BinaryContent pic) {
        this.pic = pic;
    }

    public Collection<PuzzleLevel> getPuzzleLevels() {
        return puzzleLevels;
    }

    public PuzzleCategory getPuzzleCategory() {
        return puzzleCategory;
    }

    public void setPuzzleCategory(PuzzleCategory puzzleCategory) {
        this.puzzleCategory = puzzleCategory;
    }

    public void setPuzzleLevels(Collection<PuzzleLevel> puzzleLevels) {
        this.puzzleLevels = puzzleLevels;
    }

    public PuzzleGroup() {
    }

    public PuzzleGroup( String title,PuzzleCategory puzzleCategory, BinaryContent icon, BinaryContent pic,Long version, String created, String updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.title = title;
        this.puzzleCategory = puzzleCategory;
        this.icon = icon;
        this.pic = pic;
    }
}
