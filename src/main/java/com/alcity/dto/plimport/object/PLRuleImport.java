package com.alcity.dto.plimport.object;

import java.io.Serializable;
import java.util.Collection;

public class PLRuleImport implements Serializable {
    private String title;
    private Integer ordering;
    private String event;
    private String subEvent;
    private StringBuffer condition;
    private Boolean ignoreRemaining;
    private Collection<PostActionTreeImport> actionTreesImport;

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

    public String getSubEvent() {
        return subEvent;
    }

    public void setSubEvent(String subEvent) {
        this.subEvent = subEvent;
    }

    public void setCondition(StringBuffer condition) {
        this.condition = condition;
    }

    public StringBuffer getCondition() {
        return condition;
    }

    public void setConditions(StringBuffer condition) {
        this.condition = condition;
    }


    public Boolean getIgnoreRemaining() {
        return ignoreRemaining;
    }

    public void setIgnoreRemaining(Boolean ignoreRemaining) {
        this.ignoreRemaining = ignoreRemaining;
    }

    public Collection<PostActionTreeImport> getActionTreesImport() {
        return actionTreesImport;
    }

    public void setActionTreesImport(Collection<PostActionTreeImport> actionTreesImport) {
        this.actionTreesImport = actionTreesImport;
    }

    public PLRuleImport() {
    }

    public PLRuleImport(String title, Integer ordering, String event, Boolean ignoreRemaining, StringBuffer condition, Collection<PostActionTreeImport> actionTreesImport) {
        this.title = title;
        this.ordering = ordering;
        this.event = event;
        this.ignoreRemaining = ignoreRemaining;
        this.condition = condition;
        this.actionTreesImport = actionTreesImport;
    }
}
