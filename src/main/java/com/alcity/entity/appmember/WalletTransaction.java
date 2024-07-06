package com.alcity.entity.appmember;

import com.alcity.entity.base.BaseTable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    @JoinColumn(name = "applicationMember_WalletItem", nullable = false)
    @JsonIgnore
    private AppMember_WalletItem applicationMember_WalletItem;

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

    public WalletTransaction(String transactionDate, Float amount, Boolean incTransaction, String description, AppMember_WalletItem applicationMember_WalletItem, Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.incTransaction = incTransaction;
        this.description = description;
        this.applicationMember_WalletItem = applicationMember_WalletItem;
    }
}
