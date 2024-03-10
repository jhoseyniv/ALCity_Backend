package com.alcity.entity.base;


import com.alcity.entity.users.ApplicationMember;

import javax.persistence.Entity;

@Entity
public class BinaryContentType extends BaseTable {
    public BinaryContentType() {
    }

    public BinaryContentType(String label, String value, Long version, Long created, Long updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(label, value, version, created, updated, createdBy, updatedBy);
    }
}
