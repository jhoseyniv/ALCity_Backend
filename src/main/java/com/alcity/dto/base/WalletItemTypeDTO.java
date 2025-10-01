package com.alcity.dto.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class WalletItemTypeDTO {
    private Long id;
    private String label;
    private String value;
    private Boolean currency;

    private Long version;
    private String created;
    private String updated;
    private String createdBY;
    private String updatedBy;

    public WalletItemTypeDTO(Long id, String value,String label,Boolean currency,
                             Long version, String created, String updated, String createdBY, String updatedBy) {
        this.id =id;
        this.value=value;
        this.label=label;
        this.currency = currency;
        this.version = version;
        this.created = created;
        this.updated = updated;
        this.createdBY = createdBY;
        this.updatedBy = updatedBy;
    }
}
