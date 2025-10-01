package com.alcity.dto.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor

public class ClientTypeDTO  implements Serializable {
    private Long id;
    private String label;
    private String value;

    public ClientTypeDTO(Long id, String label, String value) {
        this.id = id;
        this.label = label;
        this.value = value;
    }
}
