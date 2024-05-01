package com.alcity.dto.journey;

public class JourneyLearningSkillDTO   {

    private Long id;
    private Long version;
    private String created;
    private String updated;
    private String createdBy;

    private String updatedBy;

    private Float requiredAmount;

    public Float getRequiredAmount() {
        return requiredAmount;
    }

    public void setRequiredAmount(Float requiredAmount) {
        this.requiredAmount = requiredAmount;
    }

    public JourneyLearningSkillDTO() {
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

    public JourneyLearningSkillDTO(Long id, Long version, String created, String updated, String createdBy, String updatedBy, Float requiredAmount) {
        this.id = id;
        this.version = version;
        this.created = created;
        this.updated = updated;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.requiredAmount = requiredAmount;
    }
}
