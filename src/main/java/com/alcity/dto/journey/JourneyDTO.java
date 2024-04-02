package com.alcity.dto.journey;

import com.alcity.dto.base.BaseTableDTO;
import com.alcity.dto.base.BinaryContentDTO;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.journey.JourneyLearningSkill;
import com.alcity.entity.journey.JourneyStep;

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

    public JourneyDTO(String title, BinaryContentDTO graphic, Set<JourneyStepDTO> journeyStepDTOSet, Set<JourneyLearningSkill> journeyLearningSkillSet ,Long id, Long version, String created, String updated) {
        super(id, version, created, updated);
        this.title = title;
        this.graphic = graphic;
        this.journeyStepDTOSet = journeyStepDTOSet;
        this.journeyLearningSkillSet = journeyLearningSkillSet;
    }
}
