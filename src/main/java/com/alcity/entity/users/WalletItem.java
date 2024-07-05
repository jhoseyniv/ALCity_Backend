package com.alcity.entity.users;

import com.alcity.entity.base.BaseItemSet;
import com.alcity.entity.base.WalletItemType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
public class WalletItem extends BaseItemSet implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "wallet_item_type_id", nullable = false)
    @JsonIgnore
    private WalletItemType walletItemType;

    public WalletItemType getWalletItemType() {
        return walletItemType;
    }

    public void setWalletItemType(WalletItemType walletItemType) {
        this.walletItemType = walletItemType;
    }

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] icon;

    public byte[] getIcon() {
        return icon;
    }

    public void setIcon(byte[] icon) {
        this.icon = icon;
    }

    @OneToMany(mappedBy = "walletItem", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<AppMember_WalletItem> applicationMember_walletItems;

    public Set<AppMember_WalletItem> getApplicationMember_walletItems() {
        return applicationMember_walletItems;
    }

    public void setApplicationMember_walletItems(Set<AppMember_WalletItem> applicationMember_walletItems) {
        this.applicationMember_walletItems = applicationMember_walletItems;
    }

    public WalletItem() {
    }

    public WalletItem(WalletItemType walletItemType, byte[] icon, String label, String value, Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(label, value, version, created, updated, createdBy, updatedBy);
        this.walletItemType = walletItemType;
        this.icon = icon;
    }
}
