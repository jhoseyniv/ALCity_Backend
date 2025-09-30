package com.alcity.entity.appmember;

import com.alcity.entity.base.BaseItemSet;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.base.WalletItemType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class WalletItem extends BaseItemSet implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "wallet_item_type_id", nullable = false)
    @JsonIgnore
    private WalletItemType walletItemType;

    @Column(columnDefinition="tinyint(1) default 0")
    private boolean baseCurrency;

    public WalletItemType getWalletItemType() {
        return walletItemType;
    }

    public void setWalletItemType(WalletItemType walletItemType) {
        this.walletItemType = walletItemType;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "icon_id", nullable = true)
    @JsonIgnore
    private BinaryContent icon;

    @OneToMany(mappedBy = "walletItem", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<AppMember_WalletItem> applicationMember_walletItems;



    public WalletItem(WalletItemType walletItemType, BinaryContent icon, String label, String value, Boolean baseCurrency,
                      Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(label, value, version, created, updated, createdBy, updatedBy);
        this.walletItemType = walletItemType;
        this.baseCurrency = baseCurrency;
        this.icon = icon;
    }
}
