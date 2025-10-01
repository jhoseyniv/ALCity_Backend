package com.alcity.dto.journey;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class JourneyLearningSkillDTO   {

    private Long id;
    private Long version;
    private String created;
    private String updated;
    private String createdBy;

    private String updatedBy;

    private Float requiredAmount;

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
