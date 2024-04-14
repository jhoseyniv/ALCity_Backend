package com.alcity.dto.base;

public class ClientTypeDTO extends BaseItemSetDTO{
    public ClientTypeDTO() {
    }

    public ClientTypeDTO(Long id, String label, String value, Long version, String created, String updated) {
        super(id, label, value, version, created, updated);
    }
}
