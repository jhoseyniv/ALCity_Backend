package com.alcity.dto.alobject;

import com.alcity.dto.base.BaseTableDTO;
import com.alcity.dto.base.ClientTypeDTO;
import com.alcity.entity.base.ClientType;

public class ActionRendererDTO extends BaseTableDTO {
    private String handler;
    private ClientTypeDTO clientTypeDTO;
    private ObjectActionDTO objectActionDTO;

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public ClientTypeDTO getClientTypeDTO() {
        return clientTypeDTO;
    }

    public void setClientTypeDTO(ClientTypeDTO clientTypeDTO) {
        this.clientTypeDTO = clientTypeDTO;
    }

    public ObjectActionDTO getObjectActionDTO() {
        return objectActionDTO;
    }

    public void setObjectActionDTO(ObjectActionDTO objectActionDTO) {
        this.objectActionDTO = objectActionDTO;
    }

    public ActionRendererDTO() {
    }

    public ActionRendererDTO(Long id, Long version, String created, String updated, String handler, ClientTypeDTO clientTypeDTO, ObjectActionDTO objectActionDTO) {
        super(id, version, created, updated);
        this.handler = handler;
        this.clientTypeDTO = clientTypeDTO;
        this.objectActionDTO = objectActionDTO;
    }
}
