package com.alcity.entity.base;

import com.alcity.entity.appmember.AppMember;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class PLPrivacy extends BaseItemSet implements Serializable {


    public PLPrivacy() {
    }

    public PLPrivacy(String label, String value, Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(label, value, version, created, updated, createdBy,updatedBy);
    }
}
