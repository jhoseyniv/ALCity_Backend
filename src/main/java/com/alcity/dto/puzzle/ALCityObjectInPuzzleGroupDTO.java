package com.alcity.dto.puzzle;

public class ALCityObjectInPuzzleGroupDTO {

    private Long id;
    private Long version;
    private String created;
    private String updated;
    private String createdBy;

    private String updatedBy;

    private String title;
    private String code;
    private ALCityObjectDTO puzzleObjectDTO;

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

    public ALCityObjectDTO getPuzzleObjectDTO() {
        return puzzleObjectDTO;
    }

    public void setPuzzleObjectDTO(ALCityObjectDTO puzzleObjectDTO) {
        this.puzzleObjectDTO = puzzleObjectDTO;
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

    public ALCityObjectInPuzzleGroupDTO() {
    }

    public ALCityObjectInPuzzleGroupDTO(Long id, Long version, String created, String updated, String createdBy, String updatedBy,
                                        String title, String code, ALCityObjectDTO puzzleObjectDTO) {
        this.id = id;
        this.version = version;
        this.created = created;
        this.updated = updated;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.title = title;
        this.code = code;
        this.puzzleObjectDTO = puzzleObjectDTO;
    }
}
