package com.alcity.entity.base;


import com.alcity.entity.alenum.BinaryContentType;
import com.alcity.entity.alobject.AttributeValue;
import com.alcity.entity.learning.LearningContent;
import com.alcity.entity.appmember.AppMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor

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


    @OneToMany(mappedBy = "binaryContentId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Collection<AttributeValue> attributeValueCollection;

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
