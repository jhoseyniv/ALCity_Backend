package com.alcity.dto.puzzle;

import com.alcity.dto.journey.JourneyStepDTO;

import java.util.Collection;

public class PGDTO  {
    private Long id;
    private Long version;
    private String created;
    private String updated;
    private String createdBy;

    private String updatedBy;

    private String title;
    private Long iconId;
    private Long picId;

    private Collection<JourneyStepDTO> journeyStepDTOCollection;
    private Collection<PuzzleLevelLDTO> puzzleLevelDTOCollection;

    private Collection<ALCityObjectInPGDTO> puzzleGroup_puzzleObjectDTOCollection;

    private Collection<PuzzleSkillLearningContentDTO> puzzleSkillLearningContentDTOCollection;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getIconId() {
        return iconId;
    }

    public void setIconId(Long iconId) {
        this.iconId = iconId;
    }

    public Long getPicId() {
        return picId;
    }

    public void setPicId(Long picId) {
        this.picId = picId;
    }

    public Collection<JourneyStepDTO> getJourneyStepDTOCollection() {
        return journeyStepDTOCollection;
    }

    public void setJourneyStepDTOCollection(Collection<JourneyStepDTO> journeyStepDTOCollection) {
        this.journeyStepDTOCollection = journeyStepDTOCollection;
    }

    public Collection<PuzzleLevelLDTO> getPuzzleLevelDTOCollection() {
        return puzzleLevelDTOCollection;
    }

    public void setPuzzleLevelDTOCollection(Collection<PuzzleLevelLDTO> puzzleLevelDTOCollection) {
        this.puzzleLevelDTOCollection = puzzleLevelDTOCollection;
    }

    public Collection<PuzzleSkillLearningContentDTO> getPuzzleSkillLearningContentDTOCollection() {
        return puzzleSkillLearningContentDTOCollection;
    }

    public void setPuzzleSkillLearningContentDTOCollection(Collection<PuzzleSkillLearningContentDTO> puzzleSkillLearningContentDTOCollection) {
        this.puzzleSkillLearningContentDTOCollection = puzzleSkillLearningContentDTOCollection;
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

    public PGDTO() {
    }

    public Collection<ALCityObjectInPGDTO> getPuzzleGroup_puzzleObjectDTOCollection() {
        return puzzleGroup_puzzleObjectDTOCollection;
    }

    public void setPuzzleGroup_puzzleObjectDTOCollection(Collection<ALCityObjectInPGDTO> puzzleGroup_puzzleObjectDTOCollection) {
        this.puzzleGroup_puzzleObjectDTOCollection = puzzleGroup_puzzleObjectDTOCollection;
    }

    public PGDTO(Long id, Long version, String created, String updated, String createdBy, String updatedBy, String title, Long iconId, Long picId) {
        this.id = id;
        this.version = version;
        this.created = created;
        this.updated = updated;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.title = title;
        this.iconId = iconId;
        this.picId = picId;
    }
}
