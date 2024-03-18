package com.alcity.entity.alobject;

import com.alcity.entity.alobject.ObjectAction;
import com.alcity.entity.base.BaseItemSet;
import com.alcity.entity.journey.Journey;
import com.alcity.entity.users.ApplicationMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class ObjectActionEvent extends BaseItemSet implements Serializable {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "object_action_id", nullable = false)
    @JsonIgnore
    private ObjectAction objectAction;

    public ObjectActionEvent() {
    }

    public ObjectActionEvent(String label, String value, ObjectAction objectAction , Long version, Long created, Long updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(label, value, version, created, updated, createdBy, updatedBy);
        this.objectAction = objectAction;
    }
}
