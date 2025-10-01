package com.alcity.dto.appmember;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor

public class WalletItemDTO implements Serializable {
    private Long id;
    private String label;
    private String value;
    private Long  iconId;
    private byte[] thumbnail;
    private String walletItemType;
    private Boolean isCurrency;
    private Boolean isBaseCurrency;

    public WalletItemDTO(Long id, String label, String value, Long iconId, String walletItemType,Boolean isCurrency,Boolean isBaseCurrency,byte[] thumbnail ) {
        this.id = id;
        this.label = label;
        this.value = value;
        this.iconId = iconId;
        this.walletItemType = walletItemType;
        this.isCurrency = isCurrency;
        this.isBaseCurrency = isBaseCurrency;
        this.thumbnail =thumbnail;
    }
}
