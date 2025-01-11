package com.alcity.dto.puzzle;

import com.alcity.dto.journey.JourneyStepDTO;
import com.alcity.entity.journey.JourneyStep;

public class PuzzleLevelStepMappingDTO {
    private PLDTO pldto;
    private JourneyStepDTO journeyStepDTO;

    public PLDTO getPldto() {
        return pldto;
    }

    public void setPldto(PLDTO pldto) {
        this.pldto = pldto;
    }

    public JourneyStepDTO getJourneyStepDTO() {
        return journeyStepDTO;
    }

    public void setJourneyStepDTO(JourneyStepDTO journeyStepDTO) {
        this.journeyStepDTO = journeyStepDTO;
    }

    public PuzzleLevelStepMappingDTO() {
    }

    public PuzzleLevelStepMappingDTO(PLDTO pldto, JourneyStepDTO journeyStepDTO) {
        this.pldto = pldto;
        this.journeyStepDTO = journeyStepDTO;
    }
}
