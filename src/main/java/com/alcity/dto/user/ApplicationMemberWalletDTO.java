package com.alcity.dto.user;

import com.alcity.dto.user.WalletItemTransactionDTO;

import java.util.Collection;

public class ApplicationMemberWalletDTO {
    private Long walletItemId;
    private String walletItemTitle;
    private  Float amount;

    private Collection<WalletItemTransactionDTO> walletItemTransactionDTOSet;


    public Long getWalletItemId() {
        return walletItemId;
    }

    public void setWalletItemId(Long walletItemId) {
        this.walletItemId = walletItemId;
    }

    public String getWalletItemTitle() {
        return walletItemTitle;
    }

    public void setWalletItemTitle(String walletItemTitle) {
        this.walletItemTitle = walletItemTitle;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Collection<WalletItemTransactionDTO> getWalletItemTransactionDTOSet() {
        return walletItemTransactionDTOSet;
    }

    public void setWalletItemTransactionDTOSet(Collection<WalletItemTransactionDTO> walletItemTransactionDTOSet) {
        this.walletItemTransactionDTOSet = walletItemTransactionDTOSet;
    }

    public ApplicationMemberWalletDTO() {
    }

    public ApplicationMemberWalletDTO(Long walletItemId, String walletItemTitle, Float amount, Collection<WalletItemTransactionDTO> walletItemTransactionDTOSet) {
        this.walletItemId = walletItemId;
        this.walletItemTitle = walletItemTitle;
        this.amount = amount;
        this.walletItemTransactionDTOSet = walletItemTransactionDTOSet;
    }
}
