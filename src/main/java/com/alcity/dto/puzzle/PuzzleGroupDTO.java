package com.alcity.dto.puzzle;

import com.alcity.dto.base.BaseItemSetDTO;
import com.alcity.dto.base.BaseTableDTO;
import com.alcity.entity.journey.JourneyStep;
import com.alcity.entity.puzzle.PuzzleLevel;

import java.time.ZonedDateTime;
import java.util.Set;

public class PuzzleGroupDTO extends BaseTableDTO {

    private String title;
    private Set<JourneyStep> journeyStepSet;
    private Set<PuzzleLevel> puzzleLevelSet;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<JourneyStep> getJourneyStepSet() {
        return journeyStepSet;
    }

    public void setJourneyStepSet(Set<JourneyStep> journeyStepSet) {
        this.journeyStepSet = journeyStepSet;
    }

    public Set<PuzzleLevel> getPuzzleLevelSet() {
        return puzzleLevelSet;
    }

    public void setPuzzleLevelSet(Set<PuzzleLevel> puzzleLevelSet) {
        this.puzzleLevelSet = puzzleLevelSet;
    }

    public PuzzleGroupDTO() {
    }

    public PuzzleGroupDTO(Long id, Long version, ZonedDateTime created, ZonedDateTime updated, String title, Set<JourneyStep> journeyStepSet, Set<PuzzleLevel> puzzleLevelSet) {
        super(id, version, created, updated);
        this.title = title;
        this.journeyStepSet = journeyStepSet;
        this.puzzleLevelSet = puzzleLevelSet;
    }
}
