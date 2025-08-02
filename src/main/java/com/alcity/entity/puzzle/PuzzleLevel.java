package com.alcity.entity.puzzle;


import com.alcity.entity.alenum.PLStatus;
import com.alcity.entity.alenum.PLDifficulty;
import com.alcity.entity.base.*;
import com.alcity.entity.play.PermitedPlayer;
import com.alcity.entity.appmember.AppMember;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Collection;


@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"title", "code","puzzle_group_id"})
})

@Entity
public class PuzzleLevel extends BaseTable implements Serializable {

    @Column(name="approveDate")
    private String approveDate;

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

    @Column(name="firstStarScore")
    private Float firstStarScore;

    @Column(name="secondStarScore")
    private Float secondStarScore;
    @Column(name="thirdStartScore")
    private Float thirdStartScore;

    @Column(name="interpreterFile")
    private byte[] interpreterFile;

    public byte[] getInterpreterFile() {
        return interpreterFile;
    }

    public void setInterpreterFile(byte[] interpreterFile) {
        this.interpreterFile = interpreterFile;
    }

    public String getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(String approveDate) {
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

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "puzzle_group_id", nullable = false)
    @JsonIgnore
    private PuzzleGroup puzzleGroup;

     @Enumerated(EnumType.ORDINAL)
     private PLDifficulty puzzleDifficulty;

    @Enumerated(EnumType.ORDINAL)
    private PLStatus puzzleLevelStatus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "puzzle_level_privacy_id", nullable = false)
    @JsonIgnore
    private PLPrivacy puzzleLevelPrivacy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "picture_id")
    @JsonIgnore
    private BinaryContent picture;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "boardGraphic_id")
//    @JsonIgnore
//    private BinaryContent boardGraphic;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "icon_id")
    @JsonIgnore
    private BinaryContent icon;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "creator_id")
    @JsonIgnore
    private AppMember creator;

    @OneToMany(mappedBy = "puzzleLevel", fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<PLRule> rules;

    @OneToMany(mappedBy = "puzzleLevel", fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<PLObjective> plObjectives;

    @OneToMany(mappedBy = "puzzleLevel", fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<PLGround> plGrounds;

    @OneToMany(mappedBy = "puzzleLevel", fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<PermitedPlayer> permitedPlayerCollection;

    public Collection<PermitedPlayer> getPermitedPlayerCollection() {
        return permitedPlayerCollection;
    }

    public void setPermitedPlayerCollection(Collection<PermitedPlayer> permitedPlayerCollection) {
        this.permitedPlayerCollection = permitedPlayerCollection;
    }

    public Collection<PLObjective> getPlObjectives() {
        return plObjectives;
    }

    public void setPlObjectives(Collection<PLObjective> plObjectives) {
        this.plObjectives = plObjectives;
    }

    public Collection<PLGround> getPlGrounds() {
        return plGrounds;
    }

    public void setPlGrounds(Collection<PLGround> plGrounds) {
        this.plGrounds = plGrounds;
    }

    public PuzzleGroup getPuzzleGroup() {
        return puzzleGroup;
    }

    public void setPuzzleGroup(PuzzleGroup puzzleGroup) {
        this.puzzleGroup = puzzleGroup;
    }

    public PLDifficulty getPuzzleDifficulty() {
        return puzzleDifficulty;
    }

    public void setPuzzleDifficulty(PLDifficulty puzzleDifficulty) {
        this.puzzleDifficulty = puzzleDifficulty;
    }

    public PLStatus getPuzzleLevelStatus() {
        return puzzleLevelStatus;
    }

    public void setPuzzleLevelStatus(PLStatus puzzleLevelStatus) {
        this.puzzleLevelStatus = puzzleLevelStatus;
    }

    public PLPrivacy getPuzzleLevelPrivacy() {
        return puzzleLevelPrivacy;
    }

    public void setPuzzleLevelPrivacy(PLPrivacy puzzleLevelPrivacy) {
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

    public Collection<PLRule> getRules() {
        return rules;
    }

    public void setRules(Collection<PLRule> rules) {
        this.rules = rules;
    }

    public AppMember getCreator() {
        return creator;
    }

    public void setCreator(AppMember creator) {
        this.creator = creator;
    }

    @OneToMany(mappedBy = "puzzleLevel", fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<LearningTopicInPL> learningTopicInPLCollection;

    public Collection<LearningTopicInPL> getLearningTopicInPLCollection() {
        return learningTopicInPLCollection;
    }

    public void setLearningTopicInPLCollection(Collection<LearningTopicInPL> learningTopicInPLCollection) {
        this.learningTopicInPLCollection = learningTopicInPLCollection;
    }

    @OneToMany(mappedBy = "puzzleLevel", fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<ALCityInstanceInPL> puzzleGroupObjectInstanceCollection;

    public Collection<ALCityInstanceInPL> getPuzzleGroupObjectInstanceCollection() {
        return puzzleGroupObjectInstanceCollection;
    }

    public void setPuzzleGroupObjectInstanceCollection(Collection<ALCityInstanceInPL> puzzleGroupObjectInstanceCollection) {
        this.puzzleGroupObjectInstanceCollection = puzzleGroupObjectInstanceCollection;
    }


    public Float getFirstStarScore() {
        return firstStarScore;
    }

    public void setFirstStarScore(Float firstStarScore) {
        this.firstStarScore = firstStarScore;
    }

    public Float getSecondStarScore() {
        return secondStarScore;
    }

    public void setSecondStarScore(Float secondStarScore) {
        this.secondStarScore = secondStarScore;
    }

    public Float getThirdStartScore() {
        return thirdStartScore;
    }

    public void setThirdStartScore(Float thirdStartScore) {
        this.thirdStartScore = thirdStartScore;
    }

    public PuzzleLevel() {
    }

    public PuzzleLevel(AppMember creator,String approveDate, Long ordering, String title, String code, Integer fromAge, Integer toAge, Float maxScore,Float firstStarScore,Float secondStarScore,Float thirdStartScore,
                        PuzzleGroup puzzleGroup, PLDifficulty puzzleDifficulty, PLStatus puzzleLevelStatus, PLPrivacy puzzleLevelPrivacy,BinaryContent picture ,BinaryContent icon,
                       Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.creator = creator;
        this.approveDate = approveDate;
        this.ordering = ordering;
        this.title = title;
        this.code = code;
        this.fromAge = fromAge;
        this.toAge = toAge;
        this.maxScore = maxScore;
        this.firstStarScore = firstStarScore;
        this.secondStarScore = secondStarScore;
        this.thirdStartScore = thirdStartScore;
        this.puzzleGroup = puzzleGroup;
        this.puzzleDifficulty = puzzleDifficulty;
        this.puzzleLevelStatus = puzzleLevelStatus;
        this.puzzleLevelPrivacy = puzzleLevelPrivacy;
        this.picture = picture;
        this.icon = icon;
       // this.boardGraphic = boardGraphic;
    }
}
