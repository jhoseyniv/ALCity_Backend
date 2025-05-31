package com.alcity.dto.appmember;

import com.alcity.entity.alenum.WalletTransactionType;
import com.alcity.entity.appmember.AppMember;

public class WalletItemTransactionDTO {

    private Long id;
    private String transactionDate;
    private Float amount;

    private Long walletItemId;

    private Long counterpartyId;
    private Long appMemberWalletItemId;

    private String walletTransactionType;

    public Long getWalletItemId() {
        return walletItemId;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Long getCounterpartyId() {
        return counterpartyId;
    }

    public void setCounterpartyId(Long counterpartyId) {
        this.counterpartyId = counterpartyId;
    }

    public String getWalletTransactionType() {
        return walletTransactionType;
    }

    public void setWalletTransactionType(String walletTransactionType) {
        this.walletTransactionType = walletTransactionType;
    }

    public void setWalletItemId(Long walletItemId) {
        this.walletItemId = walletItemId;
    }


    public Long getAppMemberWalletItemId() {
        return appMemberWalletItemId;
    }

    public void setAppMemberWalletItemId(Long appMemberWalletItemId) {
        this.appMemberWalletItemId = appMemberWalletItemId;
    }

    private Boolean incTransaction;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public WalletItemTransactionDTO() {
    }

    public WalletItemTransactionDTO(Long id,String transactionDate, Float amount, Long walletItemId, Long counterpartyId,
                                    Long appMemberWalletItemId, String walletTransactionType, Boolean incTransaction, String description) {
        this.id =id;
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.walletItemId = walletItemId;
        this.counterpartyId = counterpartyId;
        this.appMemberWalletItemId = appMemberWalletItemId;
        this.walletTransactionType = walletTransactionType;
        this.incTransaction = incTransaction;
        this.description = description;
    }
}
