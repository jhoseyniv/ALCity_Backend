package com.alcity.dto.user;

import com.alcity.dto.base.WalletItemTypeDTO;
import com.alcity.entity.base.WalletItemType;

public class WalletItemDTO {
    private Long id;
    private String label;
    private String value;
    private Long version;
    private String created;
    private String updated;
    private String createdBY;
    private String updatedBy;
    private Long createdBYId;
    private Long updatedById;
    private WalletItemTypeDTO walletItemTypeDTO;


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

    public Long getCreatedBYId() {
        return createdBYId;
    }

    public void setCreatedBYId(Long createdBYId) {
        this.createdBYId = createdBYId;
    }

    public Long getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public WalletItemTypeDTO getWalletItemTypeDTO() {
        return walletItemTypeDTO;
    }

    public void setWalletItemTypeDTO(WalletItemTypeDTO walletItemTypeDTO) {
        this.walletItemTypeDTO = walletItemTypeDTO;
    }

    public WalletItemDTO() {

    }

    public WalletItemDTO(Long id, String label, String value, Long version, String created, String updated, String createdBY, String updatedBy, Long createdBYId, Long updatedById, WalletItemTypeDTO walletItemTypeDTO) {
        this.id = id;
        this.label = label;
        this.value = value;
        this.version = version;
        this.created = created;
        this.updated = updated;
        this.createdBY = createdBY;
        this.updatedBy = updatedBy;
        this.createdBYId = createdBYId;
        this.updatedById = updatedById;
        this.walletItemTypeDTO = walletItemTypeDTO;
    }
}