package com.alcity.entity.appmember;

import com.alcity.entity.alenum.WalletTransactionType;
import com.alcity.entity.base.BaseTable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

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
    @JoinColumn(name = "applicationMember_id", nullable = false)
    @JsonIgnore
    private AppMember appMember;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "appMember_WalletItem_id", referencedColumnName = "id")
    @JsonIgnore
    private AppMember_WalletItem appMemberWalletItem;

    public AppMember_WalletItem getAppMemberWalletItem() {
        return appMemberWalletItem;
    }

    public void setAppMemberWalletItem(AppMember_WalletItem appMemberWalletItem) {
        this.appMemberWalletItem = appMemberWalletItem;
    }

    @Enumerated(EnumType.ORDINAL)
    private WalletTransactionType walletTransactionType;

    @Column(name="counterparty_id")
    private Long counterpartyId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "walletItem_id", nullable = false)
    @JsonIgnore
    private WalletItem walletItem;

    public Long getCounterpartyId() {
        return counterpartyId;
    }

    public void setCounterpartyId(Long counterpartyId) {
        this.counterpartyId = counterpartyId;
    }

    public AppMember getAppMember() {
        return appMember;
    }

    public void setAppMember(AppMember appMember) {
        this.appMember = appMember;
    }

    public WalletTransactionType getWalletTransactionType() {
        return walletTransactionType;
    }

    public void setWalletTransactionType(WalletTransactionType walletTransactionType) {
        this.walletTransactionType = walletTransactionType;
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

    public WalletTransaction(String transactionDate, Float amount, Boolean incTransaction, String description,AppMember appMember,
                             WalletItem walletItem,Long counterpartyId,WalletTransactionType transactionType,
                             Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.incTransaction = incTransaction;
        this.description = description;
        this.walletItem = walletItem;
        this.appMember = appMember;
        this.counterpartyId = counterpartyId;
        this.walletTransactionType = transactionType;
    }
}
