package com.alcity.dto.user;

public class WalletItemTransactionDTO {

    private Long transactionDate;
    private Float amount;
    private Boolean incTransaction;
    private String description;

    public WalletItemTransactionDTO() {
    }

    public WalletItemTransactionDTO(Long transactionDate, Float amount, Boolean incTransaction, String description) {
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.incTransaction = incTransaction;
        this.description = description;
    }

    public Long getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Long transactionDate) {
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
}
