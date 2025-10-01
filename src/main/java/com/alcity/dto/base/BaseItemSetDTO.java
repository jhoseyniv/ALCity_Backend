package com.alcity.dto.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
@Getter
@Setter
@NoArgsConstructor

public class BaseItemSetDTO {
    private Long id;
    private String label;
    private String value;
    private Long version;
    private String created;
    private String updated;
    private String createdBY;
    private String updatedBy;

    public BaseItemSetDTO(Long id, String label, String value, Long version, String created, String updated) {
        this.id = id;
        this.label = label;
        this.value = value;
        this.version = version;
        this.created = created;
        this.updated = updated;
    }
}

