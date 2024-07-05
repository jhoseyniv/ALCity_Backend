package com.alcity.entity.base;

import com.alcity.entity.alobject.Renderer;
import com.alcity.entity.users.AppMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

@Entity
public class ClientType extends BaseItemSet implements Serializable {

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    @JoinTable(name = "ApplicationMember_ClientType_MAPPING", joinColumns = @JoinColumn(name = "client_type_id"),
            inverseJoinColumns = @JoinColumn(name = "application_member_id"))
    private Set<AppMember> applicationMemberSet;

    @OneToMany(mappedBy = "clientType", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private Collection<Renderer> actionRendererCollection;


    public ClientType() {
    }

    public ClientType(String label, String value, Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(label, value, version, created, updated, createdBy,updatedBy);
    }
}
