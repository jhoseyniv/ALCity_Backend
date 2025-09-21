package com.alcity.dto.appmember;

import java.io.Serializable;

public class WalletItemDTO implements Serializable {
    private Long id;
    private String label;
    private String value;
    private Long  iconId;
    private byte[] thumbnail;
    private String walletItemType;
    private Boolean isCurrency;
    private Boolean isBaseCurrency;


    public byte[] getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(byte[] thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getWalletItemType() {
        return walletItemType;
    }

    public void setWalletItemType(String walletItemType) {
        this.walletItemType = walletItemType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getIconId() {
        return iconId;
    }

    public void setIconId(Long iconId) {
        this.iconId = iconId;
    }

    public Boolean getCurrency() {
        return isCurrency;
    }

    public void setCurrency(Boolean currency) {
        isCurrency = currency;
    }

    public WalletItemDTO() {

    }

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
