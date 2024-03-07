package com.alcity.entity.base;


import com.alcity.entity.base.ALCitySystemUser;
import com.alcity.entity.base.BaseTable;
import com.alcity.entity.users.ApplicationMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
public class MemberType  extends BaseTable implements Serializable {

    public MemberType() {
    }

    @OneToMany(mappedBy = "gender", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<ApplicationMember> users;

    public MemberType(String label, String value, Long version, Long creationDate, Long lastModifiedDate, ALCitySystemUser creatorUser, ALCitySystemUser lastModifiedUser) {
        super(label, value, version, creationDate, lastModifiedDate, creatorUser, lastModifiedUser);
    }
}
