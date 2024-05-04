package com.alcity.entity.users;

import com.alcity.entity.base.BaseTable;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class GemPrice extends BaseTable implements Serializable {

    @Column(name="fromDate")
    private String fromDate;

    @Column(name="amount")
    private Float amount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "gem_id", nullable = false)
    @JsonIgnore
    private WalletItem gem;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "currency_id", nullable = false)
    @JsonIgnore
    private WalletItem currency;

    public GemPrice() {
    }

    public GemPrice(String fromDate, Float amount, WalletItem gem, WalletItem currency , Long version, String created, String updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.fromDate = fromDate;
        this.amount = amount;
        this.gem = gem;
        this.currency = currency;
    }
}
