package com.alcity.dto.puzzle;

import com.alcity.dto.Interpreter.object.RuleActionData;

import java.util.Collection;

public class PLRuleDTO {
    private String title;
    private Integer ordering;

    private StringBuffer conditions;

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



    public StringBuffer getConditions() {
        return conditions;
    }

    public void setConditions(StringBuffer conditions) {
        this.conditions = conditions;
    }



    public PLRuleDTO() {
    }

    public PLRuleDTO(String title, Integer ordering, StringBuffer conditions) {
        this.title = title;
        this.ordering = ordering;
        this.conditions = conditions;
    }
}
