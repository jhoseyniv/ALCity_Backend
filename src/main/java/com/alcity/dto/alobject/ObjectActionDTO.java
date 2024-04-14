package com.alcity.dto.alobject;

import com.alcity.dto.base.BaseItemSetDTO;

public class ObjectActionDTO extends BaseItemSetDTO {
    public ObjectActionDTO() {
    }

    public ObjectActionDTO(Long id, String label, String value, Long version, String created, String updated) {
        super(id, label, value, version, created, updated);
    }
}
