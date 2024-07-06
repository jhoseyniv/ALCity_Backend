package com.alcity.entity.base;

import com.alcity.entity.alenum.WalletItemCategory;
import com.alcity.entity.appmember.AppMember;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class WalletItemType extends BaseItemSet implements Serializable {

    @NotNull(message = "{bName.notempty}")
    private Boolean isCurrency;

    @Enumerated(EnumType.ORDINAL)
    private WalletItemCategory walletItemCategory;

    public Boolean getCurrency() {
        return isCurrency;
    }

    public void setCurrency(Boolean currency) {
        isCurrency = currency;
    }

    public WalletItemCategory getWalletItemCategory() {
        return walletItemCategory;
    }

    public void setWalletItemCategory(WalletItemCategory walletItemCategory) {
        this.walletItemCategory = walletItemCategory;
    }

    public WalletItemType() {
    }

    public WalletItemType(WalletItemCategory walletItemCategory , String label, String value, Boolean isCurrency, Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(label, value, version, created, updated, createdBy,updatedBy);
        this.isCurrency = isCurrency ;
        this.walletItemCategory = walletItemCategory;
    }
}
