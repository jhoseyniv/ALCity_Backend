package com.alcity.dto.puzzle;

import com.alcity.entity.alenum.PLRuleEventType;

public class PLRuleEventDTO {

    private Long id;
    private String name;
    private String plRuleEventType;
    private Integer plRuleEventTypeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlRuleEventType() {
        return plRuleEventType;
    }

    public void setPlRuleEventType(String plRuleEventType) {
        this.plRuleEventType = plRuleEventType;
    }

    public Integer getPlRuleEventTypeId() {
        return plRuleEventTypeId;
    }

    public void setPlRuleEventTypeId(Integer plRuleEventTypeId) {
        this.plRuleEventTypeId = plRuleEventTypeId;
    }

    public PLRuleEventDTO() {
    }

    public PLRuleEventDTO(Long id, String name, String plRuleEventType, Integer plRuleEventTypeId) {
        this.id = id;
        this.name = name;
        this.plRuleEventType = plRuleEventType;
        this.plRuleEventTypeId = plRuleEventTypeId;
    }
}
