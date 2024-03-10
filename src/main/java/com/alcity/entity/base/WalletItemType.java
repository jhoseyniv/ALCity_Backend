package com.alcity.entity.base;

import com.alcity.entity.users.ApplicationMember;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class WalletItemType extends  BaseTable implements Serializable {

    public WalletItemType() {
    }

    public WalletItemType(String label, String value, Long version, Long creationDate, Long lastModifiedDate,  ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(label, value, version, creationDate, lastModifiedDate, createdBy,updatedBy);
    }
}
