package com.alcity.entity.alobject;

import com.alcity.entity.alenum.ObjectAction;
import com.alcity.entity.alenum.POActionOwnerType;
import com.alcity.entity.base.BaseTable;
import com.alcity.entity.users.ApplicationMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class PuzzleObjectAction extends BaseTable implements Serializable {
    @Column(name="ownerObjectid")
    private Long ownerObjectid;

    public Long getOwnerObjectid() {
        return ownerObjectid;
    }

    public void setOwnerObjectid(Long ownerObjectid) {
        this.ownerObjectid = ownerObjectid;
    }

    @Enumerated(EnumType.ORDINAL)
    private POActionOwnerType poActionOwnerType;

    public POActionOwnerType getPoActionOwnerType() {
        return poActionOwnerType;
    }

    public void setPoActionOwnerType(POActionOwnerType poActionOwnerType) {
        this.poActionOwnerType = poActionOwnerType;
    }

    @Enumerated(EnumType.ORDINAL)
    private ObjectAction objectAction;

    public ObjectAction getObjectAction() {
        return objectAction;
    }

    public void setObjectAction(ObjectAction objectAction) {
        this.objectAction = objectAction;
    }

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "action_renderer_id", nullable = false)
    @JsonIgnore
    private ActionRenderer actionRenderer;

    public ActionRenderer getActionRenderer() {
        return actionRenderer;
    }

    public void setActionRenderer(ActionRenderer actionRenderer) {
        this.actionRenderer = actionRenderer;
    }

    public PuzzleObjectAction() {
    }

    public PuzzleObjectAction(POActionOwnerType poActionOwnerType, Long ownerObjectid, ObjectAction objectAction, ActionRenderer actionRenderer, Long version, String created, String updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.ownerObjectid = ownerObjectid;
        this.poActionOwnerType = poActionOwnerType;
        this.objectAction = objectAction;
        this.actionRenderer = actionRenderer;
    }
}