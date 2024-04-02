package com.alcity.dto.base;

import java.time.ZonedDateTime;

public class BaseTableDTO {
    private Long id;
    private Long version;
    private String created;
    private String updated;

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

    public BaseTableDTO() {
    }

    public BaseTableDTO(Long id, Long version, String created, String updated) {
        this.id = id;
        this.version = version;
        this.created = created;
        this.updated = updated;
    }
}
