package com.alcity.dto.puzzle;

import com.alcity.dto.player.PermitedPlayerDTO;

import java.util.Collection;

public class PuzzleLevelLDTO {
    private Long id;
    private Long version;
    private String created;
    private String updated;
    private String createdBy;
    private String updatedBy;

    private Long createdById;
    private Long updatedById;

    private String approveDate;
    private Long ordering;
    private String title;
    private String code;
    private Integer fromAge;
    private Integer toAge;
    private Float maxScore;

    private Long puzzleGroupId;

    private String puzzleLevelStatus;

    private String puzzleLevelPrivacy;

    private String puzzleLevelDifficulty;

    public Long getPuzzleGroupId() {
        return puzzleGroupId;
    }

    public void setPuzzleGroupId(Long puzzleGroupId) {
        this.puzzleGroupId = puzzleGroupId;
    }

    public Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public Long getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
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

    private Collection<PLObjectiveDTO> puzzleLevelObjectiveDTOCollection;
    private Collection<PuzzleLevel_LearningTopicDTO> puzzleLevel_learningTopicDTOCollection;

    private Collection<PLGroundDTO> puzzleLevelGroundDTOCollection;
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

    public Collection<PLGroundDTO> getPuzzleLevelGroundDTOCollection() {
        return puzzleLevelGroundDTOCollection;
    }

    public void setPuzzleLevelGroundDTOCollection(Collection<PLGroundDTO> puzzleLevelGroundDTOCollection) {
        this.puzzleLevelGroundDTOCollection = puzzleLevelGroundDTOCollection;
    }

    public Collection<PLObjectiveDTO> getPuzzleLevelObjectiveDTOCollection() {
        return puzzleLevelObjectiveDTOCollection;
    }

    public void setPuzzleLevelObjectiveDTOCollection(Collection<PLObjectiveDTO> puzzleLevelObjectiveDTOCollection) {
        this.puzzleLevelObjectiveDTOCollection = puzzleLevelObjectiveDTOCollection;
    }

    public Collection<PuzzleLevel_LearningTopicDTO> getPuzzleLevel_learningTopicDTOCollection() {
        return puzzleLevel_learningTopicDTOCollection;
    }

    public void setPuzzleLevel_learningTopicDTOCollection(Collection<PuzzleLevel_LearningTopicDTO> puzzleLevel_learningTopicDTOCollection) {
        this.puzzleLevel_learningTopicDTOCollection = puzzleLevel_learningTopicDTOCollection;
    }

    private Collection<PLInstanceDTO> plInstanceDTOS;

    public Collection<PLInstanceDTO> getPlInstanceDTOS() {
        return plInstanceDTOS;
    }

    public void setPlInstanceDTOS(Collection<PLInstanceDTO> plInstanceDTOS) {
        this.plInstanceDTOS = plInstanceDTOS;
    }

    public PuzzleLevelLDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public PuzzleLevelLDTO(Long id, Long version, String created, String updated, String createdBy, String updatedBy, Long createdById, Long updatedById,
                           String approveDate, Long ordering, String title, String code, Integer fromAge, Integer toAge, Float maxScore, String puzzleLevelStatus, String puzzleLevelPrivacy, String puzzleLevelDifficulty) {
        this.id = id;
        this.version = version;
        this.created = created;
        this.updated = updated;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.createdById = createdById;
        this.updatedById = updatedById;
        this.approveDate = approveDate;
        this.ordering = ordering;
        this.title = title;
        this.code = code;
        this.fromAge = fromAge;
        this.toAge = toAge;
        this.maxScore = maxScore;
        this.puzzleLevelStatus = puzzleLevelStatus;
        this.puzzleLevelPrivacy = puzzleLevelPrivacy;
        this.puzzleLevelDifficulty = puzzleLevelDifficulty;
    }
}
