package com.alcity.entity.alobject;

import com.alcity.entity.alenum.ObjectAction;
import com.alcity.entity.base.BaseTable;
import com.alcity.entity.base.ClientType;
import com.alcity.entity.appmember.AppMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Table(uniqueConstraints=
@UniqueConstraint(columnNames = {"handler", "objectAction"}))
@Entity
public class Renderer extends BaseTable implements Serializable {
    @Column(name="handler" )
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

    @OneToMany(mappedBy = "actionRenderer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private Collection<PuzzleObjectAction> puzzleObjectActions;

    public Collection<PuzzleObjectAction> getPuzzleObjectActions() {
        return puzzleObjectActions;
    }

    public void setPuzzleObjectActions(Collection<PuzzleObjectAction> puzzleObjectActions) {
        this.puzzleObjectActions = puzzleObjectActions;
    }

    public Renderer() {
    }

    public Renderer(String handler, ClientType clientType, ObjectAction objectAction, Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.handler = handler;
        this.clientType = clientType;
        this.objectAction = objectAction;
    }
}
