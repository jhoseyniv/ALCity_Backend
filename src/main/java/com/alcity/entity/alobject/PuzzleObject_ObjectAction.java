package com.alcity.entity.alobject;

import com.alcity.entity.base.BaseTable;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class PuzzleObject_ObjectAction extends BaseTable implements Serializable {
    @Column(name="ownerObjectid")
    private Long ownerObjectid;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Puzzle_object_action_owner_type_id", nullable = false)
    @JsonIgnore
    private PuzzleObjectActionOwnerType puzzleObjectActionOwnerType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "object_action_id", nullable = false)
    @JsonIgnore
    private ObjectAction objectAction;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "action_renderer_id", nullable = false)
    @JsonIgnore
    private ActionRenderer actionRenderer;

}
