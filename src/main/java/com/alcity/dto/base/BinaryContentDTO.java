package com.alcity.dto.base;


import java.time.ZonedDateTime;

public class BinaryContentDTO extends BaseTableDTO {

    private String fileName;
    private Integer size;
    private byte[] content;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public BinaryContentDTO() {
    }

    public BinaryContentDTO(String fileName, Integer size, byte[] content, Long id, Long version, String created, String updated) {
        super(id, version, created, updated);
        this.fileName = fileName;
        this.size = size;
        this.content = content;
    }
}
