package com.alcity.dto.puzzle;

import com.alcity.dto.Interpreter.object.RuleActionData;

import java.util.Collection;

public class PLRuleDTO {
    private Long id;
    private String title;
    private Integer ordering;
    private StringBuffer condition;

    private Long puzzleLevelId;

    private String puzzleLeveTitle;

    private Long PLRuleEventId;

    private String  PLRuleEventName;

    private  Integer plRuleEventTypeId;
    private  String plRuleEventTypeTitle;

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



    public PLRuleDTO() {
    }

    public PLRuleDTO(Long id, String title, Integer ordering, StringBuffer condition, Long puzzleLevelId, String puzzleLeveTitle, Long PLRuleEventId, String PLRuleEventName,
                     Integer plRuleEventTypeId, String plRuleEventTypeTitle) {
        this.id = id;
        this.title = title;
        this.ordering = ordering;
        this.condition = condition;
        this.puzzleLevelId = puzzleLevelId;
        this.puzzleLeveTitle = puzzleLeveTitle;
        this.PLRuleEventId = PLRuleEventId;
        this.PLRuleEventName = PLRuleEventName;
        this.plRuleEventTypeId = plRuleEventTypeId;
        this.plRuleEventTypeTitle = plRuleEventTypeTitle;
    }
}
