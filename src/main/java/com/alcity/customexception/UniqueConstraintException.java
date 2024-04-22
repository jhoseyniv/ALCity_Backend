package com.alcity.customexception;

public class UniqueConstraintException extends  RuntimeException{

    private String recordData;
    private Long recordId;
    private String  databaseEntity;

    public String getRecordData() {
        return recordData;
    }

    public void setRecordData(String recordData) {
        this.recordData = recordData;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public String getDatabaseEntity() {
        return databaseEntity;
    }

    public void setDatabaseEntity(String databaseEntity) {
        this.databaseEntity = databaseEntity;
    }

    public UniqueConstraintException(String recordData, Long recordId, String databaseEntity) {
        this.recordData = recordData;
        this.recordId = recordId;
        this.databaseEntity = databaseEntity;
    }
}
