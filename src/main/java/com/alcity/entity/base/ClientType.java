package com.alcity.entity.base;

import com.alcity.entity.alobject.Renderer;
import com.alcity.entity.appmember.AppMember;
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
    private Collection<AppMember> members;

    @OneToMany(mappedBy = "clientType", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private Collection<Renderer> renderers;

    public Collection<AppMember> getMembers() {
        return members;
    }

    public void setMembers(Collection<AppMember> members) {
        this.members = members;
    }

    public Collection<Renderer> getRenderers() {
        return renderers;
    }

    public void setRenderers(Collection<Renderer> renderers) {
        this.renderers = renderers;
    }

    public ClientType() {
    }

    public ClientType(String label, String value, Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(label, value, version, created, updated, createdBy,updatedBy);
    }
}
