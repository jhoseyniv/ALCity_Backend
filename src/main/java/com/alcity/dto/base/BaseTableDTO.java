package com.alcity.dto.base;

import java.time.ZonedDateTime;

public class BaseTableDTO {
    private Long id;
    private Long version;
    private ZonedDateTime created;
    private ZonedDateTime updated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public BaseTableDTO() {
    }

    public BaseTableDTO(Long id, Long version, ZonedDateTime created, ZonedDateTime updated) {
        this.id = id;
        this.version = version;
        this.created = created;
        this.updated = updated;
    }
}
