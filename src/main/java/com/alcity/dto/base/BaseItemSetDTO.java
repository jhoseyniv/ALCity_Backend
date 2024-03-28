package com.alcity.dto.base;

import java.time.ZonedDateTime;

public class BaseItemSetDTO {
    private Long id;
    private String label;
    private String value;
    private Long version;
    private String created;
    private String updated;

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

    public BaseItemSetDTO() {
    }

    public BaseItemSetDTO(Long id, String label, String value, Long version, String created, String updated) {
        this.id = id;
        this.label = label;
        this.value = value;
        this.version = version;
        this.created = created;
        this.updated = updated;
    }
}

