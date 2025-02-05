package com.alcity.dto.Interpreter.object;

import java.io.Serializable;
import java.util.Collection;

public class RuleData implements Serializable {
    private String title;
    private Integer ordering;
    private String event;
    private StringBuffer conditions;
    private Collection<RuleActionData> actions;

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

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public StringBuffer getConditions() {
        return conditions;
    }

    public void setConditions(StringBuffer conditions) {
        this.conditions = conditions;
    }

    public Collection<RuleActionData> getActions() {
        return actions;
    }

    public void setActions(Collection<RuleActionData> actions) {
        this.actions = actions;

    }

    public RuleData() {
    }

    public RuleData(String title, Integer ordering, String event, StringBuffer conditions, Collection<RuleActionData> actions) {
        this.title = title;
        this.ordering = ordering;
        this.event = event;
        this.conditions = conditions;
        this.actions = actions;
    }
}
