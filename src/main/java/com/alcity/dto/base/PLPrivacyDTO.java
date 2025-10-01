package com.alcity.dto.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class PLPrivacyDTO {
    private Long id;
    private Long version;
    private String label;
    private String value;
    private String created;
    private String updated;
    private String createdBy;
    private String updatedBy;
    private Long createdById;
    private Long updatedById;
    public PLPrivacyDTO(Long id, Long version, String label, String value, String created, String updated, String createdBy,
                        String updatedBy, Long createdById, Long updatedById) {
        this.id = id;
        this.version = version;
        this.label = label;
        this.value = value;
        this.created = created;
        this.updated = updated;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.createdById = createdById;
        this.updatedById = updatedById;
    }
}
