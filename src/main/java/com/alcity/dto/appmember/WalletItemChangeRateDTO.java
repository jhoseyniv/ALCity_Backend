package com.alcity.dto.appmember;

import com.alcity.entity.appmember.WalletItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor

public class WalletItemChangeRateDTO implements Serializable {

    private Long id;
    private String fromDate;
    private Float rate;
    private WalletItem fromCurrencyId;
    private WalletItem toCurrencyId;

    public WalletItemChangeRateDTO(Long id, String fromDate, Float rate, WalletItem fromCurrencyId, WalletItem toCurrencyId) {
        this.id = id;
        this.fromDate = fromDate;
        this.rate = rate;
        this.fromCurrencyId = fromCurrencyId;
        this.toCurrencyId = toCurrencyId;
    }
}
