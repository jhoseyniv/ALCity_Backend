package com.alcity.dto.appmember;

public class WalletItemDTO {
    private Long id;
    private String label;
    private String value;
    private Long  iconId;
    private byte[] thumbnail;
    private String walletItemType;
    private Boolean isCurrency;

    private Long version;
    private String created;
    private String updated;
    private String createdBY;
    private String updatedBy;

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

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getCreatedBY() {
        return createdBY;
    }

    public void setCreatedBY(String createdBY) {
        this.createdBY = createdBY;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
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

    public WalletItemDTO(Long id, String label, String value, Long iconId, String walletItemType,Boolean isCurrency,byte[] thumbnail,
                         Long version, String created, String updated, String createdBY, String updatedBy) {
        this.id = id;
        this.label = label;
        this.value = value;
        this.iconId = iconId;
        this.walletItemType = walletItemType;
        this.isCurrency = isCurrency;
        this.thumbnail =thumbnail;
        this.version = version;
        this.created = created;
        this.updated = updated;
        this.createdBY = createdBY;
        this.updatedBy = updatedBy;
    }
}
