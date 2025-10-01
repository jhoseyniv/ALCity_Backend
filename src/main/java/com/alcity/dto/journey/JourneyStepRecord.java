package com.alcity.dto.journey;

import com.alcity.entity.puzzle.PuzzleGroup;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class JourneyStepRecord {
    private Long id;
    private String title;
    private Integer ordering;
    private Integer xpos;
    private Integer ypos;

    private Long journeyId;

    private Long puzzleGroupId;


    public JourneyStepRecord(Long id, String title, Integer ordering,
                             Integer xpos, Integer ypos, Long journeyId, Long puzzleGroupId) {
        this.id = id;
        this.title = title;
        this.ordering = ordering;
        this.xpos = xpos;
        this.ypos = ypos;
        this.journeyId = journeyId;
        this.puzzleGroupId = puzzleGroupId;
    }
}
