package com.alcity.dto.puzzle;

public class PLObjectiveDTO {


    private Long id;
    private Long version;
    private String created;
    private String updated;
    private String createdBy;

    private String updatedBy;
    private Long createdById;

    private Long updatedById;
    private String title;
    private String description;
    private Float skillAmount;

    private String skillTitle;
    private Long skillId;

    private Long puzzleLevelId;
    private Float rewardAmount;
    private Long walletItemId;
    private String walletItemTitle;
    private StringBuffer condition;

    public Long getPuzzleLevelId() {
        return puzzleLevelId;
    }

    public void setPuzzleLevelId(Long puzzleLevelId) {
        this.puzzleLevelId = puzzleLevelId;
    }

    public String getSkillTitle() {
        return skillTitle;
    }

    public void setSkillTitle(String skillTitle) {
        this.skillTitle = skillTitle;
    }

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public Long getWalletItemId() {
        return walletItemId;
    }

    public void setWalletItemId(Long walletItemId) {
        this.walletItemId = walletItemId;
    }

    public String getWalletItemTitle() {
        return walletItemTitle;
    }

    public void setWalletItemTitle(String walletItemTitle) {
        this.walletItemTitle = walletItemTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getSkillAmount() {
        return skillAmount;
    }

    public void setSkillAmount(Float skillAmount) {
        this.skillAmount = skillAmount;
    }

    public Float getRewardAmount() {
        return rewardAmount;
    }

    public void setRewardAmount(Float rewardAmount) {
        this.rewardAmount = rewardAmount;
    }

    public StringBuffer getCondition() {
        return condition;
    }

    public void setCondition(StringBuffer condition) {
        this.condition = condition;
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

    public PLObjectiveDTO() {
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

    public PLObjectiveDTO(Long id, Long version, String created, String updated, String createdBy, String updatedBy, Long createdById, Long updatedById,
                          String title, String description, Float skillAmount, String skillTitle, Long skillId, Float rewardAmount,
                          Long walletItemId, String walletItemTitle, StringBuffer condition) {
        this.id = id;
        this.version = version;
        this.created = created;
        this.updated = updated;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.createdById = createdById;
        this.updatedById = updatedById;
        this.title = title;
        this.description = description;
        this.skillAmount = skillAmount;
        this.skillTitle = skillTitle;
        this.skillId = skillId;
        this.rewardAmount = rewardAmount;
        this.walletItemId = walletItemId;
        this.walletItemTitle = walletItemTitle;
        this.condition = condition;
    }
}
