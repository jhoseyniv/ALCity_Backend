package com.alcity.dto.pgimport;

public class PGObjectVariableImportDTO {
    private  Long id;
    private String name;
    private String dataType;
    private String value;
    private Boolean expression;
    private String expressionValue;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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

    public PGObjectVariableImportDTO() {
    }

    public PGObjectVariableImportDTO(Long id, String name, String dataType, String value, Boolean expression, String expressionValue) {
        this.id = id;
        this.name = name;
        this.dataType = dataType;
        this.value = value;
        this.expression = expression;
        this.expressionValue = expressionValue;
    }
}
