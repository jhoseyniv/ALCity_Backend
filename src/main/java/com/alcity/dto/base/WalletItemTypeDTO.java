package com.alcity.dto.base;


public class WalletItemTypeDTO {
    private Long id;
    private String label;
    private String value;
    private Boolean isCurrency;

    private Long version;
    private String created;
    private String updated;
    private String createdBY;
    private String updatedBy;

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

    public Boolean getCurrency() {
        return isCurrency;
    }

    public void setCurrency(Boolean currency) {
        isCurrency = currency;
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

    public WalletItemTypeDTO(Long id, String value,String label,Boolean isCurrency,
                             Long version, String created, String updated, String createdBY, String updatedBy) {
        this.id =id;
        this.value=value;
        this.label=label;
        this.isCurrency = isCurrency;
        this.version = version;
        this.created = created;
        this.updated = updated;
        this.createdBY = createdBY;
        this.updatedBy = updatedBy;
    }
}
