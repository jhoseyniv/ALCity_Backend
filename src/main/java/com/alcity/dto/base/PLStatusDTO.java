package com.alcity.dto.base;

public class PLStatusDTO extends BaseItemSetDTO{
    public PLStatusDTO() {
    }

    public PLStatusDTO(Long id, String label, String value, Long version, String created, String updated) {
        super(id, label, value, version, created, updated);
    }
}
