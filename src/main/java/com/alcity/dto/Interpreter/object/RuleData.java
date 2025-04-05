package com.alcity.dto.Interpreter.object;

import java.io.Serializable;
import java.util.Collection;

public class RuleData implements Serializable {
    private String title;
    private Integer ordering;
    private String event;
    private StringBuffer condition;
    private Boolean ignoreRemaining;
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

    public StringBuffer getCondition() {
        return condition;
    }

    public void setConditions(StringBuffer condition) {
        this.condition = condition;
    }

    public Collection<RuleActionData> getActions() {
        return actions;
    }

    public Boolean getIgnoreRemaining() {
        return ignoreRemaining;
    }

    public void setIgnoreRemaining(Boolean ignoreRemaining) {
        this.ignoreRemaining = ignoreRemaining;
    }

    public void setActions(Collection<RuleActionData> actions) {
        this.actions = actions;

    }

    public RuleData() {
    }

    public RuleData(String title, Integer ordering, String event,Boolean ignoreRemaining, StringBuffer condition, Collection<RuleActionData> actions) {
        this.title = title;
        this.ordering = ordering;
        this.event = event;
        this.ignoreRemaining = ignoreRemaining;
        this.condition = condition;
        this.actions = actions;
    }
}
