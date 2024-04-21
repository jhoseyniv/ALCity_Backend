package com.alcity.customexception;

public class UniqueConstraintException extends  RuntimeException{

    private String record;
    private String  className;

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
