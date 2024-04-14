package com.alcity.entity.alobject;

import com.alcity.entity.base.BaseTable;
import com.alcity.entity.users.ApplicationMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class PuzzleObject_ObjectAction extends BaseTable implements Serializable {
    @Column(name="ownerObjectid")
    private Long ownerObjectid;

    public Long getOwnerObjectid() {
        return ownerObjectid;
    }

    public void setOwnerObjectid(Long ownerObjectid) {
        this.ownerObjectid = ownerObjectid;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Puzzle_object_action_owner_type_id", nullable = false)
    @JsonIgnore
    private PuzzleObjectActionOwnerType puzzleObjectActionOwnerType;

    public PuzzleObjectActionOwnerType getPuzzleObjectActionOwnerType() {
        return puzzleObjectActionOwnerType;
    }

    public void setPuzzleObjectActionOwnerType(PuzzleObjectActionOwnerType puzzleObjectActionOwnerType) {
        this.puzzleObjectActionOwnerType = puzzleObjectActionOwnerType;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "object_action_id", nullable = false)
    @JsonIgnore
    private ObjectAction objectAction;

    public ObjectAction getObjectAction() {
        return objectAction;
    }

    public void setObjectAction(ObjectAction objectAction) {
        this.objectAction = objectAction;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "action_renderer_id", nullable = false)
    @JsonIgnore
    private ActionRenderer actionRenderer;

    public ActionRenderer getActionRenderer() {
        return actionRenderer;
    }

    public void setActionRenderer(ActionRenderer actionRenderer) {
        this.actionRenderer = actionRenderer;
    }

    public PuzzleObject_ObjectAction() {
    }

    public PuzzleObject_ObjectAction(PuzzleObjectActionOwnerType puzzleObjectActionOwnerType,Long ownerObjectid,  ObjectAction objectAction, ActionRenderer actionRenderer,Long version, Long created, Long updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.ownerObjectid = ownerObjectid;
        this.puzzleObjectActionOwnerType = puzzleObjectActionOwnerType;
        this.objectAction = objectAction;
        this.actionRenderer = actionRenderer;
    }
}
