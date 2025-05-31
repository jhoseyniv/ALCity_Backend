package com.alcity.dto.appmember;


public class WalletItemTransactionDTO {

    private Float amount;

    private Long walletItemId;

    private Long counterpartyId;

    private Long appMemberId;

    private String walletTransactionType;

    public Long getWalletItemId() {
        return walletItemId;
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



    private Boolean incTransaction;
    private String description;

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

    public Long getAppMemberId() {
        return appMemberId;
    }

    public void setAppMemberId(Long appMemberId) {
        this.appMemberId = appMemberId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public WalletItemTransactionDTO() {
    }

    public WalletItemTransactionDTO(String walletTransactionType, Long counterpartyId,Long appMemberId,
                                    Float amount, Long walletItemId,Boolean incTransaction, String description) {
        this.walletTransactionType = walletTransactionType;
        this.counterpartyId = counterpartyId;
        this.appMemberId = appMemberId;
        this.amount = amount;
        this.walletItemId = walletItemId;
        this.incTransaction = incTransaction;
        this.description = description;
    }
}
