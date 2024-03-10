package com.alcity.entity.base;

import com.alcity.entity.users.ApplicationMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class UserGender extends  BaseTable  implements Serializable {

    public UserGender() {
    }
    @OneToMany(mappedBy = "gender", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<ApplicationMember> users;

    public UserGender(String label, String value, Long version, Long created, Long updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(label, value, version, created, updated, createdBy,updatedBy);
    }
}
