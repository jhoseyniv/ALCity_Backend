package com.alcity.entity.base;


import com.alcity.entity.alenum.BinaryContentType;
import com.alcity.entity.alobject.AttributeValue;
import com.alcity.entity.learning.LearningContent;
import com.alcity.entity.appmember.AppMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

@Entity
public class BinaryContent extends BaseTable implements Serializable {

    @Column(name="fileName")
    private String fileName;


    @Column(name="size")
    private Integer size;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] content;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] ios3Dcontent;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] andriod3Dcontent;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] web3Dcontent;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] thumbnail;

    @Column(name="is3dContent")
    private Boolean is3dContent;

    @Column(name="tag1")
    private String tag1;

    @Column(name="tag2")
    private String tag2;

    @Column(name="tag3")
    private String tag3;

    @Enumerated(EnumType.ORDINAL)
    private BinaryContentType contentType;

    @OneToMany(mappedBy = "binaryContent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<LearningContent> learningContentSet;

    public byte[] getIos3Dcontent() {
        return ios3Dcontent;
    }

    public void setIos3Dcontent(byte[] ios3Dcontent) {
        this.ios3Dcontent = ios3Dcontent;
    }

    public byte[] getAndriod3Dcontent() {
        return andriod3Dcontent;
    }

    public void setAndriod3Dcontent(byte[] andriod3Dcontent) {
        this.andriod3Dcontent = andriod3Dcontent;
    }

    public byte[] getWeb3Dcontent() {
        return web3Dcontent;
    }

    public void setWeb3Dcontent(byte[] web3Dcontent) {
        this.web3Dcontent = web3Dcontent;
    }

    public Set<LearningContent> getLearningContentSet() {
        return learningContentSet;
    }

    public void setLearningContentSet(Set<LearningContent> learningContentSet) {
        this.learningContentSet = learningContentSet;
    }

    @OneToMany(mappedBy = "binaryContentId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Collection<AttributeValue> attributeValueCollection;

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

    public BinaryContentType getContentType() {
        return contentType;
    }

    public void setContentType(BinaryContentType contentType) {
        this.contentType = contentType;
    }

    public Collection<AttributeValue> getAttributeValueCollection() {
        return attributeValueCollection;
    }

    public void setAttributeValueCollection(Collection<AttributeValue> attributeValueCollection) {
        this.attributeValueCollection = attributeValueCollection;
    }

    public BinaryContent() {
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

    public BinaryContent(Long version, String created, String updated, AppMember createdBy, AppMember updatedBy,
                         String fileName, Integer size, byte[] content, byte[] thumbnail, Boolean is3dContent ,
                         byte[] ios3Dcontent , byte[] andriod3Dcontent , byte[] web3Dcontent,
                         String tag1, String tag2, String tag3, BinaryContentType contentType) {
        super(version, created, updated, createdBy, updatedBy);
        this.fileName = fileName;
        this.size = size;
        this.content = content;
        this.thumbnail = thumbnail;
        this.is3dContent = is3dContent;
        this.ios3Dcontent = ios3Dcontent;
        this.andriod3Dcontent = andriod3Dcontent;
        this.web3Dcontent = web3Dcontent;
        this.tag1 = tag1;
        this.tag2 = tag2;
        this.tag3 = tag3;
        this.contentType = contentType;
    }
}
