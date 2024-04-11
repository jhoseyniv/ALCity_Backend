package com.alcity.entity.puzzle;


import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.base.PuzzleCategory;
import com.alcity.entity.base.BaseTable;
import com.alcity.entity.journey.JourneyStep;
import com.alcity.entity.users.ApplicationMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

@Entity
public class PuzzleGroup extends BaseTable implements Serializable {

    @Column(name="title")
    private String title;


    @OneToMany(mappedBy = "puzzleGroup", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<JourneyStep> journeyStepSet;

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
    private Collection<PuzzleGroup_PuzzleObject> puzzleGroup_puzzleObjectCollection;

    public Collection<PuzzleGroup_PuzzleObject> getPuzzleGroup_puzzleObjectCollection() {
        return puzzleGroup_puzzleObjectCollection;
    }

    public void setPuzzleGroup_puzzleObjectCollection(Collection<PuzzleGroup_PuzzleObject> puzzleGroup_puzzleObjectCollection) {
        this.puzzleGroup_puzzleObjectCollection = puzzleGroup_puzzleObjectCollection;
    }

    @OneToMany(mappedBy = "puzzleGroup", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<PuzzleSkillLearningContent> puzzleSkillLearningContentSet;

    public Set<PuzzleSkillLearningContent> getPuzzleSkillLearningContentSet() {
        return puzzleSkillLearningContentSet;
    }

    public void setPuzzleSkillLearningContentSet(Set<PuzzleSkillLearningContent> puzzleSkillLearningContentSet) {
        this.puzzleSkillLearningContentSet = puzzleSkillLearningContentSet;
    }

    @OneToMany(mappedBy = "puzzleGroup", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<PuzzleLevel> puzzleLevelSet;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<JourneyStep> getJourneyStepSet() {
        return journeyStepSet;
    }

    public void setJourneyStepSet(Set<JourneyStep> journeyStepSet) {
        this.journeyStepSet = journeyStepSet;
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

    public Set<PuzzleLevel> getPuzzleLevelSet() {
        return puzzleLevelSet;
    }

    public void setPuzzleLevelSet(Set<PuzzleLevel> puzzleLevelSet) {
        this.puzzleLevelSet = puzzleLevelSet;
    }

    public PuzzleGroup() {
    }

    public PuzzleGroup( String title,PuzzleCategory puzzleCategory, BinaryContent icon, BinaryContent pic,Long version, Long created, Long updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.title = title;
        this.puzzleCategory = puzzleCategory;
        this.icon = icon;
        this.pic = pic;
    }
}
