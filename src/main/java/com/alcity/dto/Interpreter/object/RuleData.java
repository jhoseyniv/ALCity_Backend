package com.alcity.dto.Interpreter.object;

import com.alcity.dto.plimport.object.PostActionTreeImport;

import java.io.Serializable;
import java.util.Collection;

public class RuleData implements Serializable {
    private String title;
    private Integer ordering;
    private String event;
    private StringBuffer condition;
    private Boolean ignoreRemaining;
    private Collection<PostActionTreeExport> actionTreeExports;

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

    public Collection<PostActionTreeExport> getActionTreeExports() {
        return actionTreeExports;
    }

    public void setActionTreeExports(Collection<PostActionTreeExport> actionTreeExports) {
        this.actionTreeExports = actionTreeExports;
    }

    public Boolean getIgnoreRemaining() {
        return ignoreRemaining;
    }

    public void setIgnoreRemaining(Boolean ignoreRemaining) {
        this.ignoreRemaining = ignoreRemaining;
    }



    public RuleData() {
    }

    public RuleData(String title, Integer ordering, String event,Boolean ignoreRemaining, StringBuffer condition, Collection<PostActionTreeExport> actionTreeExports) {
        this.title = title;
        this.ordering = ordering;
        this.event = event;
        this.ignoreRemaining = ignoreRemaining;
        this.condition = condition;
        this.actionTreeExports = actionTreeExports;
    }
}
