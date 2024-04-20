package com.alcity.dto.puzzle;

import com.alcity.dto.base.BaseTableDTO;
import com.alcity.dto.player.PermitedPlayerDTO;

import java.util.Collection;

public class PuzzleLevelDTO extends BaseTableDTO {

    private String approveDate;
    private Long ordering;
    private String title;
    private String code;
    private Integer fromAge;
    private Integer toAge;
    private Float maxScore;

    private String puzzleLevelStatus;

    private String puzzleLevelPrivacy;

    private String puzzleLevelDifficulty;

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

    private Collection<PuzzleLevelObjectiveDTO> puzzleLevelObjectiveDTOCollection;
    private Collection<PuzzleLevel_LearningTopicDTO> puzzleLevel_learningTopicDTOCollection;

    private Collection<PuzzleLevelGroundDTO> puzzleLevelGroundDTOCollection;
    private Collection<PermitedPlayerDTO> permitedPlayerDTOCollection;


    public String getPuzzleLevelStatus() {
        return puzzleLevelStatus;
    }

    public void setPuzzleLevelStatus(String puzzleLevelStatus) {
        this.puzzleLevelStatus = puzzleLevelStatus;
    }

    public String getPuzzleLevelPrivacy() {
        return puzzleLevelPrivacy;
    }

    public void setPuzzleLevelPrivacy(String puzzleLevelPrivacy) {
        this.puzzleLevelPrivacy = puzzleLevelPrivacy;
    }

    public String getPuzzleLevelDifficulty() {
        return puzzleLevelDifficulty;
    }

    public void setPuzzleLevelDifficulty(String puzzleLevelDifficulty) {
        this.puzzleLevelDifficulty = puzzleLevelDifficulty;
    }

    public Collection<PermitedPlayerDTO> getPermitedPlayerDTOCollection() {
        return permitedPlayerDTOCollection;
    }

    public void setPermitedPlayerDTOCollection(Collection<PermitedPlayerDTO> permitedPlayerDTOCollection) {
        this.permitedPlayerDTOCollection = permitedPlayerDTOCollection;
    }

    public Collection<PuzzleLevelGroundDTO> getPuzzleLevelGroundDTOCollection() {
        return puzzleLevelGroundDTOCollection;
    }

    public void setPuzzleLevelGroundDTOCollection(Collection<PuzzleLevelGroundDTO> puzzleLevelGroundDTOCollection) {
        this.puzzleLevelGroundDTOCollection = puzzleLevelGroundDTOCollection;
    }

    public Collection<PuzzleLevelObjectiveDTO> getPuzzleLevelObjectiveDTOCollection() {
        return puzzleLevelObjectiveDTOCollection;
    }

    public void setPuzzleLevelObjectiveDTOCollection(Collection<PuzzleLevelObjectiveDTO> puzzleLevelObjectiveDTOCollection) {
        this.puzzleLevelObjectiveDTOCollection = puzzleLevelObjectiveDTOCollection;
    }

    public Collection<PuzzleLevel_LearningTopicDTO> getPuzzleLevel_learningTopicDTOCollection() {
        return puzzleLevel_learningTopicDTOCollection;
    }

    public void setPuzzleLevel_learningTopicDTOCollection(Collection<PuzzleLevel_LearningTopicDTO> puzzleLevel_learningTopicDTOCollection) {
        this.puzzleLevel_learningTopicDTOCollection = puzzleLevel_learningTopicDTOCollection;
    }

    private Collection<PuzzleGroupObjectInstanceDTO> puzzleGroupObjectInstanceDTOCollection;

    public Collection<PuzzleGroupObjectInstanceDTO> getPuzzleGroupObjectInstanceDTOCollection() {
        return puzzleGroupObjectInstanceDTOCollection;
    }

    public void setPuzzleGroupObjectInstanceDTOCollection(Collection<PuzzleGroupObjectInstanceDTO> puzzleGroupObjectInstanceDTOCollection) {
        this.puzzleGroupObjectInstanceDTOCollection = puzzleGroupObjectInstanceDTOCollection;
    }

    public PuzzleLevelDTO() {
    }


    public PuzzleLevelDTO(Long id, Long version, String created, String updated, String approveDate, Long ordering, String title, String code, Integer fromAge, Integer toAge, Float maxScore) {
        super(id, version, created, updated);
        this.approveDate = approveDate;
        this.ordering = ordering;
        this.title = title;
        this.code = code;
        this.fromAge = fromAge;
        this.toAge = toAge;
        this.maxScore = maxScore;
    }
}
