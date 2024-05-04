package com.alcity.entity.base;

import com.alcity.entity.alenum.PLStatus;
import com.alcity.entity.alenum.WalletItemCategory;
import com.alcity.entity.users.ApplicationMember;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class WalletItemType extends BaseItemSet implements Serializable {

    @NotNull(message = "{bName.notempty}")
    private Boolean isCurrency;

    @Enumerated(EnumType.ORDINAL)
    private WalletItemCategory walletItemCategory;

    public WalletItemType() {
    }

    public WalletItemType( WalletItemCategory walletItemCategory ,String label, String value,Boolean isCurrency,Long version, String created, String updated,  ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(label, value, version, created, updated, createdBy,updatedBy);
        this.isCurrency = isCurrency ;
        this.walletItemCategory = walletItemCategory;
    }
}
