package com.alcity.entity.appmember;

import com.alcity.entity.base.BaseTable;
import com.alcity.entity.puzzle.PLObjective;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Collection;

@Entity
public class WalletTransaction extends BaseTable implements Serializable {

    @NotNull(message = "{transactionDate.notempty}")
    private String transactionDate;

    @NotNull(message = "{amount.notempty}")
    private Float amount;

    @NotNull(message = "{incTransaction.notempty}")
    private Boolean incTransaction;

    @NotNull(message = "{description.notempty}")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "applicationMember_WalletItem", nullable = false)
    @JsonIgnore
    private AppMember_WalletItem applicationMember_WalletItem;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "plObjective_id", nullable = true)
    @JsonIgnore
    private PLObjective plObjective;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "walletItem_id", nullable = false)
    @JsonIgnore
    private WalletItem walletItem;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "source_appMember_id", nullable = false)
    @JsonIgnore
    private AppMember sourceUser;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "destination_appMember_id", nullable = false)
    @JsonIgnore
    private AppMember destinationUser;

    public AppMember getSourceUser() {
        return sourceUser;
    }

    public void setSourceUser(AppMember sourceUser) {
        this.sourceUser = sourceUser;
    }

    public AppMember getDestinationUser() {
        return destinationUser;
    }

    public void setDestinationUser(AppMember destinationUser) {
        this.destinationUser = destinationUser;
    }

    public AppMember_WalletItem getApplicationMember_WalletItem() {
        return applicationMember_WalletItem;
    }

    public void setApplicationMember_WalletItem(AppMember_WalletItem applicationMember_WalletItem) {
        this.applicationMember_WalletItem = applicationMember_WalletItem;
    }

    public PLObjective getPlObjective() {
        return plObjective;
    }

    public void setPlObjective(PLObjective plObjective) {
        this.plObjective = plObjective;
    }

    public WalletItem getWalletItem() {
        return walletItem;
    }

    public void setWalletItem(WalletItem walletItem) {
        this.walletItem = walletItem;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Boolean getIncTransaction() {
        return incTransaction;
    }

    public void setIncTransaction(Boolean incTransaction) {
        this.incTransaction = incTransaction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public WalletTransaction() {
    }

    public WalletTransaction(String transactionDate, Float amount, Boolean incTransaction, String description,
                             WalletItem walletItem,AppMember sourceUser,AppMember destinationUser,
                             Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.incTransaction = incTransaction;
        this.description = description;
        this.walletItem = walletItem;
        this.sourceUser = sourceUser;
        this.destinationUser = destinationUser;
    }
}
