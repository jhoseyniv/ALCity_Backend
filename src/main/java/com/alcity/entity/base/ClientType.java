package com.alcity.entity.base;

import com.alcity.entity.alobject.ActionRenderer;
import com.alcity.entity.journey.JourneyStep;
import com.alcity.entity.users.ApplicationMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class ClientType extends BaseItemSet implements Serializable {

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
    @JoinTable(name = "ApplicationMember_ClientType_MAPPING", joinColumns = @JoinColumn(name = "client_type_id"),
            inverseJoinColumns = @JoinColumn(name = "application_member_id"))
    private Set<ApplicationMember> applicationMemberSet;

    @OneToMany(mappedBy = "clientType", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<ActionRenderer> actionRendererSet;


    public ClientType() {
    }

    public ClientType(String label, String value, Long version, Long created, Long updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(label, value, version, created, updated, createdBy,updatedBy);
    }
}
