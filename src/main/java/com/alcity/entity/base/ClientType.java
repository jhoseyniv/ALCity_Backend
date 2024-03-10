package com.alcity.entity.base;

import com.alcity.entity.users.ApplicationMember;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class ClientType extends BaseTable implements Serializable {

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
    @JoinTable(name = "ApplicationMember_ClientType_MAPPING", joinColumns = @JoinColumn(name = "client_type_id"),
            inverseJoinColumns = @JoinColumn(name = "application_member_id"))
    private Set<ApplicationMember> applicationMemberSet;


     public ClientType() {
    }

    public ClientType(String label, String value, Long version, Long creationDate, Long lastModifiedDate, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(label, value, version, creationDate, lastModifiedDate, createdBy,updatedBy);
    }
}
