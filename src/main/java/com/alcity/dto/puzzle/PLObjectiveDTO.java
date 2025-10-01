package com.alcity.dto.puzzle;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

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
    private Long skillId;
    private String skillLable;
    private String skillValue;
    private Long puzzleLevelId;
    private Float rewardAmount;
    private Long walletItemId;
    private String walletItemTitle;
    private StringBuffer condition;

    public PLObjectiveDTO(Long id, Long version, String created, String updated, String createdBy, String updatedBy, Long createdById, Long updatedById,
                          String title, String description, Float skillAmount,Long skillId, String skillLable, String skillValue, Float rewardAmount,
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
        this.skillId = skillId;
        this.skillAmount = skillAmount;
        this.skillLable = skillLable;
        this.skillValue = skillValue;
        this.rewardAmount = rewardAmount;
        this.walletItemId = walletItemId;
        this.walletItemTitle = walletItemTitle;
        this.condition = condition;
    }
}
