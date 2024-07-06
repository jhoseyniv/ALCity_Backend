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

    @Enumerated(EnumType.ORDINAL)
    private BinaryContentType contentType;

    @OneToMany(mappedBy = "binaryContent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<LearningContent> learningContentSet;



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

    public BinaryContent(String fileName, byte[] content, BinaryContentType contentType, Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.fileName = fileName;
        this.size = content.length;
        this.content = content;
        this.contentType = contentType;
    }
}
