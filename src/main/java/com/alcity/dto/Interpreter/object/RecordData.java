package com.alcity.dto.Interpreter.object;

import java.io.Serializable;

public class RecordData implements Serializable {
    private Long id;
    private String name;

    private Long valueId;
    private String value;
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getValueId() {
        return valueId;
    }

    public void setValueId(Long valueId) {
        this.valueId = valueId;
    }

    public RecordData() {
    }

    public RecordData(Long id,String name,Long valueId, String value, String type) {
        this.id = id;
        this.name = name;
        this.valueId = valueId;
        this.value = value;
        this.type = type;
    }
}
