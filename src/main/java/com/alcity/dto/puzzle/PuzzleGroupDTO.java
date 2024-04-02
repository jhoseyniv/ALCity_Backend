package com.alcity.dto.puzzle;

import com.alcity.dto.base.BaseTableDTO;
import com.alcity.dto.base.BinaryContentDTO;
import com.alcity.dto.journey.JourneyStepDTO;

import java.util.Set;

public class PuzzleGroupDTO extends BaseTableDTO {

    private String title;
    private BinaryContentDTO icon;
    private BinaryContentDTO pic;

    private Set<JourneyStepDTO> journeyStepDTOSet;
    private Set<PuzzleLevelDTO> puzzleLevelDTOSet;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BinaryContentDTO getIcon() {
        return icon;
    }

    public void setIcon(BinaryContentDTO icon) {
        this.icon = icon;
    }

    public BinaryContentDTO getPic() {
        return pic;
    }

    public void setPic(BinaryContentDTO pic) {
        this.pic = pic;
    }

    public Set<JourneyStepDTO> getJourneyStepDTOSet() {
        return journeyStepDTOSet;
    }

    public void setJourneyStepDTOSet(Set<JourneyStepDTO> journeyStepDTOSet) {
        this.journeyStepDTOSet = journeyStepDTOSet;
    }

    public Set<PuzzleLevelDTO> getPuzzleLevelDTOSet() {
        return puzzleLevelDTOSet;
    }

    public void setPuzzleLevelDTOSet(Set<PuzzleLevelDTO> puzzleLevelDTOSet) {
        this.puzzleLevelDTOSet = puzzleLevelDTOSet;
    }

    public PuzzleGroupDTO() {
    }

    public PuzzleGroupDTO(Long id, Long version, String created, String updated, String title, BinaryContentDTO icon, BinaryContentDTO pic, Set<JourneyStepDTO> journeyStepDTOSet, Set<PuzzleLevelDTO> puzzleLevelDTOSet) {
        super(id, version, created, updated);
        this.title = title;
        this.icon = icon;
        this.pic = pic;
        this.journeyStepDTOSet = journeyStepDTOSet;
        this.puzzleLevelDTOSet = puzzleLevelDTOSet;
    }
}
