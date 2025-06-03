package com.alcity.entity.alobject;

import com.alcity.entity.alenum.ObjectActionType;
import com.alcity.entity.alenum.POActionOwnerType;
import com.alcity.entity.base.BaseTable;
import com.alcity.entity.appmember.AppMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class ObjectAction extends BaseTable implements Serializable {
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
    private ObjectActionType objectAction;

    public ObjectActionType getObjectAction() {
        return objectAction;
    }

    public void setObjectAction(ObjectActionType objectAction) {
        this.objectAction = objectAction;
    }

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "action_renderer_id", nullable = false)
    @JsonIgnore
    private Renderer actionRenderer;

    public Renderer getActionRenderer() {
        return actionRenderer;
    }

    public void setActionRenderer(Renderer actionRenderer) {
        this.actionRenderer = actionRenderer;
    }

    public ObjectAction() {
    }

    public ObjectAction(POActionOwnerType poActionOwnerType, Long ownerObjectid, ObjectActionType objectAction, Renderer actionRenderer, Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.ownerObjectid = ownerObjectid;
        this.poActionOwnerType = poActionOwnerType;
        this.objectAction = objectAction;
        this.actionRenderer = actionRenderer;
    }
}
