package com.alcity.dto.appmember;

import com.alcity.entity.appmember.WalletItem;

import java.io.Serializable;

public class WalletItemChangeRateDTO implements Serializable {

    private Long id;
    private Long fromDate;
    private Float rate;
    private WalletItem fromCurrencyId;
    private WalletItem toCurrencyId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFromDate() {
        return fromDate;
    }

    public void setFromDate(Long fromDate) {
        this.fromDate = fromDate;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }


    public WalletItem getFromCurrencyId() {
        return fromCurrencyId;
    }

    public void setFromCurrencyId(WalletItem fromCurrencyId) {
        this.fromCurrencyId = fromCurrencyId;
    }

    public WalletItem getToCurrencyId() {
        return toCurrencyId;
    }

    public void setToCurrencyId(WalletItem toCurrencyId) {
        this.toCurrencyId = toCurrencyId;
    }
    public WalletItemChangeRateDTO() {
    }

    public WalletItemChangeRateDTO(Long id, Long fromDate, Float rate, WalletItem fromCurrencyId, WalletItem toCurrencyId) {
        this.id = id;
        this.fromDate = fromDate;
        this.rate = rate;
        this.fromCurrencyId = fromCurrencyId;
        this.toCurrencyId = toCurrencyId;
    }
}
