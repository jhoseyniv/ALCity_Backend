package com.alcity.entity.users;

import com.alcity.entity.base.BaseTable;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class CurrencyChangeRate extends BaseTable implements Serializable {

    @Column(name="fromDate")
    private Long fromDate;

    @Column(name="rate")
    private Float rate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "from_currency_id", nullable = false)
    @JsonIgnore
    private WalletItem fromCurrency;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "to_currency_id", nullable = false)
    @JsonIgnore
    private WalletItem toCurrency;

    public CurrencyChangeRate() {
    }

    public CurrencyChangeRate(Long fromDate, Float rate, WalletItem fromCurrency, WalletItem toCurrency,Long version, Long created, Long updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.fromDate = fromDate;
        this.rate = rate;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
    }
}