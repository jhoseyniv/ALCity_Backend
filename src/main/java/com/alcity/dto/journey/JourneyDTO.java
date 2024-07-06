package com.alcity.dto.journey;

import com.alcity.dto.base.BinaryContentDTO;
import com.alcity.entity.journey.JourneyLearningSkill;

import java.util.Set;

public class JourneyDTO   {
    private Long id;
    private Long version;
    private String created;
    private String updated;
    private String createdBy;

    private String updatedBy;
    private Long createdById;

    private Long updatedById;

    private String title;
    private BinaryContentDTO graphic;
    private Set<JourneyStepDTO> journeyStepDTOSet;
    private Set<JourneyLearningSkill> journeyLearningSkillSet;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BinaryContentDTO getGraphic() {
        return graphic;
    }

    public void setGraphic(BinaryContentDTO graphic) {
        this.graphic = graphic;
    }

    public Set<JourneyStepDTO> getJourneyStepDTOSet() {
        return journeyStepDTOSet;
    }

    public void setJourneyStepDTOSet(Set<JourneyStepDTO> journeyStepDTOSet) {
        this.journeyStepDTOSet = journeyStepDTOSet;
    }

    public Set<JourneyLearningSkill> getJourneyLearningSkillSet() {
        return journeyLearningSkillSet;
    }

    public void setJourneyLearningSkillSet(Set<JourneyLearningSkill> journeyLearningSkillSet) {
        this.journeyLearningSkillSet = journeyLearningSkillSet;
    }

    public JourneyDTO() {
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

    public JourneyDTO(Long id, Long version, String created, String updated, String createdBy, String updatedBy,Long createdById,Long updatedById,
                      String title, BinaryContentDTO graphic) {
        this.id = id;
        this.version = version;
        this.created = created;
        this.updated = updated;
        this.createdBy = createdBy;
        this.createdById = createdById;
        this.updatedBy = updatedBy;
        this.updatedById = updatedById;
        this.title = title;
        this.graphic = graphic;
    }
}
