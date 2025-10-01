package com.alcity.dto.appmember;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class MemberTypeDTO {
    Long id;
    Long version;
    String label;
    String value;
    String created;
    String updated;
    String createdBy;
    String updatedBy;
    private Long createdById;

    private Long updatedById;

    public MemberTypeDTO(Long id, Long version, String label, String value, String created, String updated, String createdBy, String updatedBy, Long createdById, Long updatedById) {
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
