package com.alcity.entity.ruleengine;

import com.alcity.entity.base.BaseItemSet;
import com.alcity.entity.users.ApplicationMember;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class UserEvent extends BaseItemSet implements Serializable {
    public UserEvent() {
    }

    public UserEvent(String label, String value, Long version, Long created, Long updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(label, value, version, created, updated, createdBy, updatedBy);
    }
}
