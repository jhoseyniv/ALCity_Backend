package com.alcity.dto.appmember;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor

public class WalletItemTransactionDTO implements Serializable {

    private Float amount;

    private Long walletItemId;

    private Long counterpartyId;

    private Long appMemberId;

    private String walletTransactionType;

    private Boolean incTransaction;
    private String description;

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
