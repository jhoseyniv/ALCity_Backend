package com.alcity.dto.alobject;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class RendererDTO {

    private Long id;
    private String objectAction;
    private String handler;
    private String clientType;

    private Long version;
    private String created;
    private String updated;
    private String createdBy;

    private String updatedBy;

    public RendererDTO(String objectAction, String handler, String clientType, Long id, Long version, String created, String updated, String createdBy, String updatedBy) {
        this.objectAction = objectAction;
        this.handler = handler;
        this.clientType = clientType;
        this.id = id;
        this.version = version;
        this.created = created;
        this.updated = updated;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }
}
