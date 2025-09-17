package com.alcity.dto.base;


import java.io.Serializable;

public class BinaryContentDTO implements Serializable {

    private Long id;
    private String fileName;
    private Integer size;
    private byte[] content;
    private byte[] ios3Dcontent;
    private byte[] android3Dcontent;
    private byte[] web3Dcontent;
    private byte[] thumbnail;

    private String contentType;

    private Boolean is3dContent;

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

    public Boolean getIs3dContent() {
        return is3dContent;
    }

    public void setIs3dContent(Boolean is3dContent) {
        this.is3dContent = is3dContent;
    }

    public BinaryContentDTO() {
    }

    public byte[] getIos3Dcontent() {
        return ios3Dcontent;
    }

    public void setIos3Dcontent(byte[] ios3Dcontent) {
        this.ios3Dcontent = ios3Dcontent;
    }

    public byte[] getAndroid3Dcontent() {
        return android3Dcontent;
    }

    public void setAndroid3Dcontent(byte[] android3Dcontent) {
        this.android3Dcontent = android3Dcontent;
    }

    public byte[] getWeb3Dcontent() {
        return web3Dcontent;
    }

    public void setWeb3Dcontent(byte[] web3Dcontent) {
        this.web3Dcontent = web3Dcontent;
    }

    public BinaryContentDTO(Long id,
                            String fileName, Integer size, byte[] content, byte[] thumbnail,
                            byte[] ios3Dcontent , byte[] android3Dcontent , byte[] web3Dcontent,
                            String contentType, Boolean is3dContent, String tag1, String tag2, String tag3) {
        this.id = id;
        this.fileName = fileName;
        this.size = size;
        this.content = content;
        this.ios3Dcontent = ios3Dcontent;
        this.android3Dcontent =android3Dcontent;
        this.web3Dcontent = web3Dcontent;
        this.thumbnail = thumbnail;
        this.contentType = contentType;
        this.is3dContent = is3dContent;
        this.tag1 = tag1;
        this.tag2 = tag2;
        this.tag3 = tag3;
    }
}
