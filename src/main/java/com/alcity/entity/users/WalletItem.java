package com.alcity.entity.users;

import com.alcity.entity.base.BaseItemSet;
import com.alcity.entity.base.WalletItemType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class WalletItem extends BaseItemSet implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "wallet_item_type_id", nullable = false)
    @JsonIgnore
    private WalletItemType walletItemType;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] icon;

    @OneToMany(mappedBy = "walletItem", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<ApplicationMember_WalletItem> applicationMembers;

    public WalletItem() {
    }

    public WalletItem(WalletItemType walletItemType, byte[] icon, String label, String value, Long version, Long creationDate, Long lastModifiedDate, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(label, value, version, creationDate, lastModifiedDate, createdBy, updatedBy);
        this.walletItemType = walletItemType;
        this.icon = icon;
    }
}
