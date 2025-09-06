package com.alcity.test.ruleimport_new;

import com.alcity.dto.plimpexport.rulemport.PostActionTreeImport;

import java.io.Serializable;
import java.util.Collection;

public class PLRuleImport_New implements Serializable {
    private String title;
    private Integer ordering;
    private String event;
    private String subEvent;
    private StringBuffer condition;
    private Boolean ignoreRemaining;
    private Collection<PostActionTreeImport_New> actions;

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

    public Collection<PostActionTreeImport_New> getActions() {
        return actions;
    }

    public void setActions(Collection<PostActionTreeImport_New> actions) {
        this.actions = actions;
    }

    public PLRuleImport_New() {
    }

    public PLRuleImport_New(String title, Integer ordering, String event, Boolean ignoreRemaining, StringBuffer condition, Collection<PostActionTreeImport_New> actions) {
        this.title = title;
        this.ordering = ordering;
        this.event = event;
        this.ignoreRemaining = ignoreRemaining;
        this.condition = condition;
        this.actions = actions;
    }
}
