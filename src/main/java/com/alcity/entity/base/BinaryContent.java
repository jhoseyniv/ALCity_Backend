package com.alcity.entity.base;


import com.alcity.entity.alobject.AttributeValue;
import com.alcity.entity.learning.LearningContent;
import com.alcity.entity.users.ApplicationMember;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class BinaryContent extends BaseTable implements Serializable {

    @Column(name="fileName")
    private String fileName;


    @Column(name="size")
    private Integer size;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] content;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "binary_content_type_id", nullable = false)
    @JsonIgnore
    private BinaryContentType contentType;

    @OneToMany(mappedBy = "binaryContent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<LearningContent> learningContentSet;

    public Set<LearningContent> getLearningContentSet() {
        return learningContentSet;
    }

    public void setLearningContentSet(Set<LearningContent> learningContentSet) {
        this.learningContentSet = learningContentSet;
    }

    @OneToMany(mappedBy = "binaryContent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<AttributeValue> attributeValueSet;

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

    public Set<AttributeValue> getAttributeValueSet() {
        return attributeValueSet;
    }

    public void setAttributeValueSet(Set<AttributeValue> attributeValueSet) {
        this.attributeValueSet = attributeValueSet;
    }

    public BinaryContent() {
    }

    public BinaryContent(String fileName, byte[] content, BinaryContentType contentType,Long version, Long created, Long updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.fileName = fileName;
        this.size = content.length;
        this.content = content;
        this.contentType = contentType;
    }
}
