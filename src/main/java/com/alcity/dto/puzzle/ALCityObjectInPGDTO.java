package com.alcity.dto.puzzle;

public class ALCityObjectInPGDTO {

    private Long id;
    private String title;
    private String code;

    private String puzzleGroup;

    private Long puzzleGroupId;
    private String alCityObject;

    private Long alCityObjectId;

    private Long version;
    private String created;
    private String updated;
    private String createdBy;
    private String updatedBy;

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


    public String getPuzzleGroup() {
        return puzzleGroup;
    }

    public void setPuzzleGroup(String puzzleGroup) {
        this.puzzleGroup = puzzleGroup;
    }

    public Long getPuzzleGroupId() {
        return puzzleGroupId;
    }

    public void setPuzzleGroupId(Long puzzleGroupId) {
        this.puzzleGroupId = puzzleGroupId;
    }

    public ALCityObjectInPGDTO() {
    }

    public String getAlCityObject() {
        return alCityObject;
    }

    public void setAlCityObject(String alCityObject) {
        this.alCityObject = alCityObject;
    }

    public Long getAlCityObjectId() {
        return alCityObjectId;
    }

    public void setAlCityObjectId(Long alCityObjectId) {
        this.alCityObjectId = alCityObjectId;
    }

    public ALCityObjectInPGDTO(Long id, String title, String code, String puzzleGroup, Long puzzleGroupId, String alCityObject, Long alCityObjectId,
                               Long version, String created, String updated, String createdBy, String updatedBy) {
        this.id = id;
        this.title = title;
        this.code = code;
        this.puzzleGroup = puzzleGroup;
        this.puzzleGroupId = puzzleGroupId;
        this.alCityObject = alCityObject;
        this.alCityObjectId = alCityObjectId;
        this.version = version;
        this.created = created;
        this.updated = updated;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }
}
