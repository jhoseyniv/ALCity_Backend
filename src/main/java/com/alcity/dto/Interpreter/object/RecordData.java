package com.alcity.dto.Interpreter.object;

public class RecordData {
    private Long id;
    private String name;
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

    public RecordData() {
    }

    public RecordData(Long id,String name, String value, String type) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.type = type;
    }
}
