package com.alcity.entity.alobject;

import com.alcity.entity.base.BaseTable;
import com.alcity.entity.base.ClientType;
import com.alcity.entity.users.ApplicationMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class ActionRenderer extends BaseTable implements Serializable {
    @Column(name="handler")
    private String handler;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_type_id", nullable = false)
    @JsonIgnore
    private ClientType clientType;

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }

    public ObjectAction getObjectAction() {
        return objectAction;
    }

    public void setObjectAction(ObjectAction objectAction) {
        this.objectAction = objectAction;
    }

    @Enumerated(EnumType.ORDINAL)
    private ObjectAction objectAction;

    @OneToMany(mappedBy = "actionRenderer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<PuzzleObject_ObjectAction> puzzleObject_ObjectAction;

    public ActionRenderer() {
    }

    public ActionRenderer(String handler, ClientType clientType, ObjectAction objectAction,Long version, String created, String updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.handler = handler;
        this.clientType = clientType;
        this.objectAction = objectAction;
    }
}
