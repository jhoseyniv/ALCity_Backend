package com.alcity.dto.base;

import java.time.ZonedDateTime;

public class UserGenderDTO extends BaseItemSetDTO {

    public UserGenderDTO() {
    }

    public UserGenderDTO(Long id, String label, String value, Long version, String created, String updated) {
        super(id, label, value, version, created, updated);
    }
}
