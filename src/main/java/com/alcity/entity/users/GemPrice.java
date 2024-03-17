package com.alcity.entity.users;

import com.alcity.entity.base.BaseTable;
import com.alcity.entity.journey.Journey;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class GemPrice extends BaseTable implements Serializable {

    @Column(name="fromDate")
    private Long fromDate;

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

    public GemPrice(Long fromDate, Float amount, WalletItem gem, WalletItem currency , Long version, Long created, Long updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.fromDate = fromDate;
        this.amount = amount;
        this.gem = gem;
        this.currency = currency;
    }
}
