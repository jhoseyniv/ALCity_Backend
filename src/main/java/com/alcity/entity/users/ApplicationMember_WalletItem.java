package com.alcity.entity.users;


import com.alcity.entity.base.BaseTable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class ApplicationMember_WalletItem extends BaseTable implements Serializable {


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "application_member_id", referencedColumnName = "id")
    @JsonIgnore
    private ApplicationMember applicationMember;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "wallet_item_id", referencedColumnName = "id")
    @JsonIgnore
    private WalletItem walletItem;

    @Column(name="amount")
    private Float amount;

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public ApplicationMember getApplicationMember() {
        return applicationMember;
    }

    public void setApplicationMember(ApplicationMember applicationMember) {
        this.applicationMember = applicationMember;
    }

    public WalletItem getWalletItem() {
        return walletItem;
    }

    public void setWalletItem(WalletItem walletItem) {
        this.walletItem = walletItem;
    }

    @OneToMany(mappedBy = "applicationMember_WalletItem", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<WalletTransaction>  walletTransactionSet;

    public ApplicationMember_WalletItem() {
    }

    public ApplicationMember_WalletItem(ApplicationMember applicationMember, WalletItem walletItem, Float amount,Long version, Long creationDate, Long lastModifiedDate, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(version, creationDate, lastModifiedDate, createdBy, updatedBy);
        this.applicationMember = applicationMember;
        this.walletItem = walletItem;
        this.amount = amount;
    }
}
