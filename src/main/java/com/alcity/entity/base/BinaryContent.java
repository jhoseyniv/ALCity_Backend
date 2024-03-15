package com.alcity.entity.base;


import com.alcity.entity.alobject.ALCityAttribute;
import com.alcity.entity.alobject.ALCityAttributeValue;
import com.alcity.entity.users.ApplicationMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
    private byte[] content;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "binary_content_type_id", nullable = false)
    @JsonIgnore
    private BinaryContentType contentType;

    @OneToMany(mappedBy = "binaryContent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<ALCityAttributeValue> attributeValueSet;


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
