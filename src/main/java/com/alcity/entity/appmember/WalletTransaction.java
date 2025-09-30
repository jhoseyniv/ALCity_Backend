package com.alcity.entity.appmember;

import com.alcity.entity.alenum.WalletTransactionType;
import com.alcity.entity.base.BaseTable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor

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

    @Enumerated(EnumType.ORDINAL)
    private WalletTransactionType walletTransactionType;

    @Column(name="counterparty_id")
    private Long counterpartyId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "walletItem_id", nullable = false)
    @JsonIgnore
    private WalletItem walletItem;

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
