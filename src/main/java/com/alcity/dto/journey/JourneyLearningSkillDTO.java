package com.alcity.dto.journey;

import com.alcity.dto.base.BaseTableDTO;

public class JourneyLearningSkillDTO  extends BaseTableDTO {
    private Float requiredAmount;


    public Float getRequiredAmount() {
        return requiredAmount;
    }

    public void setRequiredAmount(Float requiredAmount) {
        this.requiredAmount = requiredAmount;
    }

    public JourneyLearningSkillDTO() {
    }

    public JourneyLearningSkillDTO(Long id, Long version, String created, String updated, Float requiredAmount) {
        super(id, version, created, updated);
        this.requiredAmount = requiredAmount;
    }
}
