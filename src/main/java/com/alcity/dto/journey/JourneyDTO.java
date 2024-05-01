package com.alcity.dto.journey;

import com.alcity.dto.base.BinaryContentDTO;
import com.alcity.dto.user.ApplicationMemberDTO;
import com.alcity.entity.journey.JourneyLearningSkill;

import java.util.Set;

public class JourneyDTO  extends BaseTableDTO {
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

    public JourneyDTO(Long id, Long version, String created, String updated, ApplicationMemberDTO createdBy, ApplicationMemberDTO updatedBy, String title, BinaryContentDTO graphic) {
        super(id, version, created, updated, createdBy, updatedBy);
        this.title = title;
        this.graphic = graphic;
    }
}
