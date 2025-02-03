package com.alcity.dto.journey;

import com.alcity.entity.puzzle.PuzzleGroup;

public class JourneyStepRecord {
    private Long id;
    private String title;
    private Integer ordering;
    private Integer xpos;
    private Integer ypos;

    private Long journeyId;

    private Long puzzleGroupId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getOrdering() {
        return ordering;
    }

    public void setOrdering(Integer ordering) {
        this.ordering = ordering;
    }

    public Integer getXpos() {
        return xpos;
    }

    public void setXpos(Integer xpos) {
        this.xpos = xpos;
    }

    public Integer getYpos() {
        return ypos;
    }

    public void setYpos(Integer ypos) {
        this.ypos = ypos;
    }

    public Long getJourneyId() {
        return journeyId;
    }

    public void setJourneyId(Long journeyId) {
        this.journeyId = journeyId;
    }


    public Long getPuzzleGroupId() {
        return puzzleGroupId;
    }

    public void setPuzzleGroupId(Long puzzleGroupId) {
        this.puzzleGroupId = puzzleGroupId;
    }

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
