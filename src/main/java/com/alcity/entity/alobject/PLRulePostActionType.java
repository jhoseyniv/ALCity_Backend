package com.alcity.entity.alobject;

import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.base.BaseItemSet;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.io.Serializable;

@Entity
public class PLRulePostActionType  extends BaseItemSet implements Serializable {

    @Column(name="subAction" )
    private String subAction;

    public String getSubAction() {
        return subAction;
    }

    public void setSubAction(String subAction) {
        this.subAction = subAction;
    }

    public PLRulePostActionType() {
    }

    public PLRulePostActionType(String label, String value, String subAction ,
                                Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(label, value, version, created, updated, createdBy, updatedBy);
        this.subAction = subAction;
    }
}
