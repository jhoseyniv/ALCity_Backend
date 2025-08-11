package com.alcity.dto.plimpexport.ruleexport;

import java.io.Serializable;
import java.util.Collection;

public class RuleData implements Serializable {
    private static final long serialVersionUID = 2696512588478032947L;

    private String title;
    private Integer ordering;
    private String event;
    private StringBuffer condition;
    private Boolean ignoreRemaining;
    private Collection<PostActionTreeExport> actions;

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

    public void setCondition(StringBuffer condition) {
        this.condition = condition;
    }

    public Collection<PostActionTreeExport> getActions() {
        return actions;
    }

    public void setActions(Collection<PostActionTreeExport> actions) {
        this.actions = actions;
    }

    public Boolean getIgnoreRemaining() {
        return ignoreRemaining;
    }

    public void setIgnoreRemaining(Boolean ignoreRemaining) {
        this.ignoreRemaining = ignoreRemaining;
    }



    public RuleData() {
    }

    public RuleData(String title, Integer ordering, String event,Boolean ignoreRemaining, StringBuffer condition, Collection<PostActionTreeExport> actions) {
        this.title = title;
        this.ordering = ordering;
        this.event = event;
        this.ignoreRemaining = ignoreRemaining;
        this.condition = condition;
        this.actions = actions;
    }
}
