package com.alcity.dto.journey;

public class JourneyStepDTO  {


    private Long id;
    private String title;
    private Integer ordering;
    private Integer xpos;
    private Integer ypos;


    private String puzzleGroupTitle;
    private Long puzzleGroupId;
    private Long puzzleGroupIconId;

    private Long journeyId;
    private String journeyTitle;
    private Long journeyIconId;


    private Long version;
    private String created;
    private String updated;
    private String createdBy;

    private String updatedBy;

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

    public String getPuzzleGroupTitle() {
        return puzzleGroupTitle;
    }

    public void setPuzzleGroupTitle(String puzzleGroupTitle) {
        this.puzzleGroupTitle = puzzleGroupTitle;
    }

    public Long getPuzzleGroupId() {
        return puzzleGroupId;
    }

    public void setPuzzleGroupId(Long puzzleGroupId) {
        this.puzzleGroupId = puzzleGroupId;
    }

    public Long getPuzzleGroupIconId() {
        return puzzleGroupIconId;
    }

    public void setPuzzleGroupIconId(Long puzzleGroupIconId) {
        this.puzzleGroupIconId = puzzleGroupIconId;
    }

    public JourneyStepDTO() {
    }

    public Long getJourneyId() {
        return journeyId;
    }

    public void setJourneyId(Long journeyId) {
        this.journeyId = journeyId;
    }

    public String getJourneyTitle() {
        return journeyTitle;
    }

    public void setJourneyTitle(String journeyTitle) {
        this.journeyTitle = journeyTitle;
    }

    public Long getJourneyIconId() {
        return journeyIconId;
    }

    public void setJourneyIconId(Long journeyIconId) {
        this.journeyIconId = journeyIconId;
    }

    public JourneyStepDTO(Long id, String title, Integer ordering, Integer xpos, Integer ypos,
                          String puzzleGroupTitle, Long puzzleGroupId, Long puzzleGroupIconId,
                          Long journeyId, String journeyTitle, Long journeyIconId,
                          Long version, String created, String updated, String createdBy, String updatedBy) {
        this.id = id;
        this.title = title;
        this.ordering = ordering;
        this.xpos = xpos;
        this.ypos = ypos;
        this.puzzleGroupTitle = puzzleGroupTitle;
        this.puzzleGroupId = puzzleGroupId;
        this.puzzleGroupIconId = puzzleGroupIconId;
        this.journeyId = journeyId;
        this.journeyTitle = journeyTitle;
        this.journeyIconId = journeyIconId;
        this.version = version;
        this.created = created;
        this.updated = updated;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }
}
