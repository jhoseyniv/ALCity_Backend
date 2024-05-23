package com.alcity.dto.alenum;

public class EnumDTO {
    private Integer id;
    private String label;
    private String value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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


    public EnumDTO() {
    }

    public EnumDTO(Integer id, String label, String value) {
        this.id = id;
        this.label = label;
        this.value = value;
    }

}

