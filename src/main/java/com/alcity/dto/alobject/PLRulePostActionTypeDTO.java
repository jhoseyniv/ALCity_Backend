package com.alcity.dto.alobject;

import com.alcity.dto.base.BaseItemSetDTO;

public class PLRulePostActionTypeDTO extends BaseItemSetDTO {

    private String subAction;

    public String getSubAction() {
        return subAction;
    }

    public void setSubAction(String subAction) {
        this.subAction = subAction;
    }

    public PLRulePostActionTypeDTO() {
    }

    public PLRulePostActionTypeDTO(Long id, String label, String value,String subAction, Long version, String created, String updated) {
        super(id, label, value, version, created, updated);
        this.subAction = subAction;
    }
}
