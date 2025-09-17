package com.alcity.dto.base;

import java.io.Serializable;

public class PLBinaryContentDTO implements Serializable {

    private Long id;
    private String fileName;
    private Integer size;
    private String contentType;
    private Boolean is3dContent;
    private String deviceType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Boolean getIs3dContent() {
        return is3dContent;
    }

    public void setIs3dContent(Boolean is3dContent) {
        this.is3dContent = is3dContent;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public PLBinaryContentDTO() {
    }

    public PLBinaryContentDTO(Long id, String fileName, Integer size, String contentType, Boolean is3dContent, String deviceType) {
        this.id = id;
        this.fileName = fileName;
        this.size = size;
        this.contentType = contentType;
        this.is3dContent = is3dContent;
        this.deviceType = deviceType;
    }
}
