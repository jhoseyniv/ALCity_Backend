package com.alcity.entity.appmember;


import com.alcity.entity.base.BaseTable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Set;

@Entity
public class AppMember_WalletItem extends BaseTable implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "application_member_id", referencedColumnName = "id")
    @JsonIgnore
    private AppMember applicationMember;

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

    public AppMember getApplicationMember() {
        return applicationMember;
    }

    public void setApplicationMember(AppMember applicationMember) {
        this.applicationMember = applicationMember;
    }

    public WalletItem getWalletItem() {
        return walletItem;
    }

    public void setWalletItem(WalletItem walletItem) {
        this.walletItem = walletItem;
    }


    public AppMember_WalletItem() {
    }

    public AppMember_WalletItem(AppMember applicationMember, WalletItem walletItem, Float amount, Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.applicationMember = applicationMember;
        this.walletItem = walletItem;
        this.amount = amount;
    }
}
