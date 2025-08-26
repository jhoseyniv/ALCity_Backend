package com.alcity.dto.base;

import java.io.Serializable;

public class ClientTypeDTO  implements Serializable {
    private Long id;
    private String label;
    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ClientTypeDTO() {
    }

    public ClientTypeDTO(Long id, String label, String value) {
        this.id = id;
        this.label = label;
        this.value = value;
    }
}
