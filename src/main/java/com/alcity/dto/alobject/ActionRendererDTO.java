package com.alcity.dto.alobject;

import com.alcity.dto.base.BaseTableDTO;
import com.alcity.dto.base.ClientTypeDTO;
import com.alcity.entity.alobject.ObjectAction;
import com.alcity.entity.base.ClientType;

public class ActionRendererDTO extends BaseTableDTO {
    private String handler;
    private ClientTypeDTO clientTypeDTO;
    private ObjectAction objectAction;

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

    public ObjectAction getObjectAction() {
        return objectAction;
    }

    public void setObjectAction(ObjectAction objectAction) {
        this.objectAction = objectAction;
    }

    public ActionRendererDTO() {
    }

    public ActionRendererDTO(Long id, Long version, String created, String updated, String handler, ClientTypeDTO clientTypeDTO, ObjectAction objectAction) {
        super(id, version, created, updated);
        this.handler = handler;
        this.clientTypeDTO = clientTypeDTO;
        this.objectAction = objectAction;
    }
}
