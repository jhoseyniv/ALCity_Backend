package com.alcity.dto.puzzle;

import com.alcity.dto.base.LearningSkillDTO;
import com.alcity.dto.user.WalletItemDTO;

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
    private Float rewardAmount;
    private StringBuffer condition;

    private WalletItemDTO walletItemDTO;

    private LearningSkillDTO learningSkillDTO;

    public LearningSkillDTO getLearningSkillDTO() {
        return learningSkillDTO;
    }

    public void setLearningSkillDTO(LearningSkillDTO learningSkillDTO) {
        this.learningSkillDTO = learningSkillDTO;
    }

    public WalletItemDTO getWalletItemDTO() {
        return walletItemDTO;
    }

    public void setWalletItemDTO(WalletItemDTO walletItemDTO) {
        this.walletItemDTO = walletItemDTO;
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

    public PLObjectiveDTO(Long id, Long version, String created, String updated, String createdBy, String updatedBy,Long createdById,Long updatedById,
                          String title, String description, Float skillAmount, Float rewardAmount, StringBuffer condition) {
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
        this.rewardAmount = rewardAmount;
        this.condition = condition;
    }
}
