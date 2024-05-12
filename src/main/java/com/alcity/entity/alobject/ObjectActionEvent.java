package com.alcity.entity.alobject;

import com.alcity.entity.alenum.ObjectAction;
import com.alcity.entity.base.BaseItemSet;
import com.alcity.entity.users.ApplicationMember;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class ObjectActionEvent extends BaseItemSet implements Serializable {
    @Enumerated(EnumType.ORDINAL)
    private ObjectAction objectAction;

    public ObjectActionEvent() {
    }

    public ObjectActionEvent(String label, String value, ObjectAction objectAction , Long version, String created, String updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(label, value, version, created, updated, createdBy, updatedBy);
        this.objectAction = objectAction;
    }
}
