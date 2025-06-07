package com.alcity.dto.plimport.object;

import java.io.Serializable;

public class RecordDataImport implements Serializable {
    private Long id;
    private String name;

    private String value;
    private String type;

    private Boolean expression;
    private String expressionValue;

    public Boolean getExpression() {
        return expression;
    }

    public void setExpression(Boolean expression) {
        this.expression = expression;
    }

    public String getExpressionValue() {
        return expressionValue;
    }

    public void setExpressionValue(String expressionValue) {
        this.expressionValue = expressionValue;
    }

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


    public RecordDataImport() {
    }

    public RecordDataImport(Long id, String name, String value, String type, Boolean expression, String expressionValue) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.type = type;
        this.expression = expression;
        this.expressionValue = expressionValue;
    }
}
