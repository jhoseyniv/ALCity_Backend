package com.alcity.entity.users;

import com.alcity.entity.base.BaseTable;
import com.alcity.entity.base.RecordInformation;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class WalletTransaction extends RecordInformation implements Serializable {

    @NotNull(message = "{transactionDate.notempty}")
    private Long transactionDate;

    @NotNull(message = "{amount.notempty}")
    private Float amount;

    @NotNull(message = "{incTransaction.notempty}")
    private Boolean incTransaction;

    @NotNull(message = "{description.notempty}")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "applicationMember_WalletItem", nullable = false)
    @JsonIgnore
    private ApplicationMember_WalletItem applicationMember_WalletItem;



}