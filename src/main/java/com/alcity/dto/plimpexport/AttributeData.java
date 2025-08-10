package com.alcity.dto.plimpexport;

import java.io.Serializable;

public class AttributeData implements Serializable {
    private Long id;
    private String name;

    private Long valueId;
    private String value;
    private String type;

    private Boolean expression;
    private String expressionValue;


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

    public AttributeData() {
    }

    public AttributeData(Long id, String name, Long valueId, String value, String type, Boolean expression, String expressionValue) {
        this.id = id;
        this.name = name;
        this.valueId = valueId;
        this.value = value;
        this.type = type;
        this.expression = expression;
        this.expressionValue = expressionValue;
    }
}
