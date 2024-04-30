package com.alcity.entity.base;

import com.alcity.entity.users.ApplicationMember;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class PLPrivacy extends BaseItemSet implements Serializable {


    public PLPrivacy() {
    }

    public PLPrivacy(String label, String value, Long version, String created, String updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(label, value, version, created, updated, createdBy,updatedBy);
    }
}
