package com.alcity.entity.alobject;

import com.alcity.entity.base.BaseTable;
import com.alcity.entity.base.ClientType;
import com.alcity.entity.journey.Journey;
import com.alcity.entity.journey.JourneyStep;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "object_action_id", nullable = false)
    @JsonIgnore
    private ObjectAction objectAction;

    @OneToMany(mappedBy = "actionRenderer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<PuzzleObject_ObjectAction> puzzleObject_ObjectAction;


}
