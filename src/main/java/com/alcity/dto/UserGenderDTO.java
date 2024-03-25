package com.alcity.dto;

import java.time.ZonedDateTime;

public class UserGenderDTO {

    private Long id;
    private String value;
    private String label;
    private Long version;
    private ZonedDateTime created;
    private ZonedDateTime updated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public ZonedDateTime getCreated() {
        return created;
    }

    public void setCreated(ZonedDateTime created) {
        this.created = created;
    }

    public ZonedDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(ZonedDateTime updated) {
        this.updated = updated;
    }

    public UserGenderDTO() {
    }

    public UserGenderDTO(Long id, String value, String label, Long version, ZonedDateTime created, ZonedDateTime updated) {
        this.id = id;
        this.value = value;
        this.label = label;
        this.version = version;
        this.created = created;
        this.updated = updated;
    }
}
