package com.alcity.dto.base;


public class BinaryContentDTO {

    private Long id;
    private Long version;
//    private String created;
//    private String updated;
//    private String createdBy;
//
//    private String updatedBy;

    private String fileName;
    private Integer size;
    private byte[] content;
    private byte[] thumbnail;

    private String contentType;

    private String tag1;
    private String tag2;
    private String tag3;

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

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }


    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public byte[] getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(byte[] thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTag1() {
        return tag1;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }

    public String getTag2() {
        return tag2;
    }

    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }

    public String getTag3() {
        return tag3;
    }

    public void setTag3(String tag3) {
        this.tag3 = tag3;
    }

    public BinaryContentDTO() {
    }

    public BinaryContentDTO(Long id, Long version, String created, String updated, String createdBy, String updatedBy,
                            String fileName, Integer size, byte[] content, byte[] thumbnail, String contentType, String tag1, String tag2, String tag3) {
        this.id = id;
        this.version = version;
        this.fileName = fileName;
        this.size = size;
        this.content = content;
        this.thumbnail = thumbnail;
        this.contentType = contentType;
        this.tag1 = tag1;
        this.tag2 = tag2;
        this.tag3 = tag3;
    }
}
