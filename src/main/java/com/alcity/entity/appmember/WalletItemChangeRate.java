package com.alcity.entity.appmember;

import com.alcity.entity.base.BaseTable;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class WalletItemChangeRate extends BaseTable implements Serializable {

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

    public WalletItemChangeRate() {
    }

    public WalletItemChangeRate(Long fromDate, Float rate, WalletItem fromCurrency, WalletItem toCurrency, Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.fromDate = fromDate;
        this.rate = rate;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
    }
}
