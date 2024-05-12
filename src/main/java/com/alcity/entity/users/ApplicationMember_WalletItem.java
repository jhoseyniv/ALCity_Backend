package com.alcity.entity.users;


import com.alcity.entity.base.BaseTable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    private Set<WalletTransaction>  walletTransactionSet;

    public Set<WalletTransaction> getWalletTransactionSet() {
        return walletTransactionSet;
    }

    public void setWalletTransactionSet(Set<WalletTransaction> walletTransactionSet) {
        this.walletTransactionSet = walletTransactionSet;
    }

    public ApplicationMember_WalletItem() {
    }

    public ApplicationMember_WalletItem(ApplicationMember applicationMember, WalletItem walletItem, Float amount,Long version, String created, String updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.applicationMember = applicationMember;
        this.walletItem = walletItem;
        this.amount = amount;
    }
}
