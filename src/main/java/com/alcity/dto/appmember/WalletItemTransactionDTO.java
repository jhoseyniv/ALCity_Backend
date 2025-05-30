package com.alcity.dto.appmember;

import com.alcity.entity.appmember.AppMember;

public class WalletItemTransactionDTO {

    private Long id;
   // private String transactionDate;
    private Float amount;

    private Long walletItemId;

    private Long plObjectiveId;
    private Long appMemberWalletItemId;

    private Long sourceUserId;
    private Long destinationUserId;

    public Long getWalletItemId() {
        return walletItemId;
    }

    public Long getSourceUserId() {
        return sourceUserId;
    }

    public void setSourceUserId(Long sourceUserId) {
        this.sourceUserId = sourceUserId;
    }

    public Long getDestinationUserId() {
        return destinationUserId;
    }

    public void setDestinationUserId(Long destinationUserId) {
        this.destinationUserId = destinationUserId;
    }

    public void setWalletItemId(Long walletItemId) {
        this.walletItemId = walletItemId;
    }

    public Long getPlObjectiveId() {
        return plObjectiveId;
    }

    public void setPlObjectiveId(Long plObjectiveId) {
        this.plObjectiveId = plObjectiveId;
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

    public WalletItemTransactionDTO() {
    }


//    public String getTransactionDate() {
//        return transactionDate;
//    }
//
//    public void setTransactionDate(String transactionDate) {
//        this.transactionDate = transactionDate;
//    }

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

    public WalletItemTransactionDTO(Long id,  Float amount, Long walletItemId, Long plObjectiveId,
                                    Long appMemberWalletItemId, Boolean incTransaction, String description,Long sourceUserId,Long destinationUserId) {
        this.id = id;
       // this.transactionDate = transactionDate;
        this.amount = amount;
        this.walletItemId = walletItemId;
        this.plObjectiveId = plObjectiveId;
        this.appMemberWalletItemId = appMemberWalletItemId;
        this.incTransaction = incTransaction;
        this.description = description;
        this.destinationUserId = destinationUserId;
        this.sourceUserId = sourceUserId;
    }
}
