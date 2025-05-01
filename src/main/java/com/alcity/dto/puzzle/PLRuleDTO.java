package com.alcity.dto.puzzle;

public class PLRuleDTO {
    private Long id;
    private String title;
    private Integer ordering;
    private Boolean ignoreRemaining;
    private StringBuffer condition;

    private Long puzzleLevelId;

    private String puzzleLeveTitle;

    private Long PLRuleEventId;

    private String  PLRuleEventName;
    private String  PLRuleSubEventName;

    private  Integer plRuleEventTypeId;
    private  String plRuleEventTypeTitle;

    public Boolean getIgnoreRemaining() {
        return ignoreRemaining;
    }

    public void setIgnoreRemaining(Boolean ignoreRemaining) {
        this.ignoreRemaining = ignoreRemaining;
    }

    public String getPuzzleLeveTitle() {
        return puzzleLeveTitle;
    }

    public void setPuzzleLeveTitle(String puzzleLeveTitle) {
        this.puzzleLeveTitle = puzzleLeveTitle;
    }

    public Long getPLRuleEventId() {
        return PLRuleEventId;
    }

    public void setPLRuleEventId(Long PLRuleEventId) {
        this.PLRuleEventId = PLRuleEventId;
    }

    public String getPLRuleEventName() {
        return PLRuleEventName;
    }

    public void setPLRuleEventName(String PLRuleEventName) {
        this.PLRuleEventName = PLRuleEventName;
    }

    public Integer getPlRuleEventTypeId() {
        return plRuleEventTypeId;
    }

    public void setPlRuleEventTypeId(Integer plRuleEventTypeId) {
        this.plRuleEventTypeId = plRuleEventTypeId;
    }

    public String getPlRuleEventTypeTitle() {
        return plRuleEventTypeTitle;
    }

    public void setPlRuleEventTypeTitle(String plRuleEventTypeTitle) {
        this.plRuleEventTypeTitle = plRuleEventTypeTitle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPuzzleLevelId() {
        return puzzleLevelId;
    }

    public void setPuzzleLevelId(Long puzzleLevelId) {
        this.puzzleLevelId = puzzleLevelId;
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



    public StringBuffer getCondition() {
        return condition;
    }

    public void setCondition(StringBuffer condition) {
        this.condition = condition;
    }

    public String getPLRuleSubEventName() {
        return PLRuleSubEventName;
    }

    public void setPLRuleSubEventName(String PLRuleSubEventName) {
        this.PLRuleSubEventName = PLRuleSubEventName;
    }

    public PLRuleDTO() {
    }

    public PLRuleDTO(Long id, String title, Integer ordering,Boolean ignoreRemaining, StringBuffer condition, Long puzzleLevelId, String puzzleLeveTitle, Long PLRuleEventId, String PLRuleEventName,String PLRuleSubEventName,
                     Integer plRuleEventTypeId, String plRuleEventTypeTitle) {
        this.id = id;
        this.title = title;
        this.ordering = ordering;
        this.ignoreRemaining = ignoreRemaining;
        this.condition = condition;
        this.puzzleLevelId = puzzleLevelId;
        this.puzzleLeveTitle = puzzleLeveTitle;
        this.PLRuleEventId = PLRuleEventId;
        this.PLRuleEventName = PLRuleEventName;
        this.PLRuleSubEventName = PLRuleSubEventName;
        this.plRuleEventTypeId = plRuleEventTypeId;
        this.plRuleEventTypeTitle = plRuleEventTypeTitle;
    }
}
