package com.alcity.entity.appmember;

import com.alcity.entity.base.BaseTable;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class WalletItemChangeRate extends BaseTable implements Serializable {

    @Column(name="fromDate")
    private String fromDate;

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


    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public WalletItem getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(WalletItem fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public WalletItem getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(WalletItem toCurrency) {
        this.toCurrency = toCurrency;
    }

    public WalletItemChangeRate() {
    }

    public WalletItemChangeRate(String fromDate, Float rate, WalletItem fromCurrency, WalletItem toCurrency, Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.fromDate = fromDate;
        this.rate = rate;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
    }
}
