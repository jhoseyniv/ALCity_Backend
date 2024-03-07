package com.alcity.entity.base;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class WalletItemType extends  BaseTable implements Serializable {

    public WalletItemType() {
    }

    public WalletItemType(String label, String value, Long version, Long creationDate, Long lastModifiedDate, ALCitySystemUser creatorUser, ALCitySystemUser lastModifiedUser) {
        super(label, value, version, creationDate, lastModifiedDate, creatorUser, lastModifiedUser);
    }
}
