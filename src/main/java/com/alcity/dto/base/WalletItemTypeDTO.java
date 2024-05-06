package com.alcity.dto.base;


public class WalletItemTypeDTO extends BaseItemSetDTO {
    private Boolean isCurrency;
    private String walletItemCategory;

    public Boolean getCurrency() {
        return isCurrency;
    }

    public void setCurrency(Boolean currency) {
        isCurrency = currency;
    }

    public String getWalletItemCategory() {
        return walletItemCategory;
    }

    public void setWalletItemCategory(String walletItemCategory) {
        this.walletItemCategory = walletItemCategory;
    }

    public WalletItemTypeDTO(Long id, String label, String value, Long version, String created, String updated, Boolean isCurrency, String walletItemCategory) {
        super(id, label, value, version, created, updated);
        this.isCurrency = isCurrency;
        this.walletItemCategory = walletItemCategory;
    }
}
