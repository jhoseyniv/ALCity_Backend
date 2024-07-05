package com.alcity.entity.puzzle;


import com.alcity.entity.base.BaseTable;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.alobject.ObjectCategory;
import com.alcity.entity.users.AppMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
public class ALCityObject extends BaseTable implements Serializable {

    @Column(name="title",unique = true)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    @JsonIgnore
    private ObjectCategory objectCategory;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "picture_id", nullable = false)
    @JsonIgnore
    private BinaryContent picture;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "icon_id", nullable = false)
    @JsonIgnore
    private BinaryContent icon;


    @OneToMany(mappedBy = "alCityObject", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Collection<ALCityObjectInPG> alCityObjectInPGCollection;

    public Collection<ALCityObjectInPG> getAlCityObjectInPGCollection() {
        return alCityObjectInPGCollection;
    }

    public void setAlCityObjectInPGCollection(Collection<ALCityObjectInPG> alCityObjectInPGCollection) {
        this.alCityObjectInPGCollection = alCityObjectInPGCollection;
    }

    public ObjectCategory getObjectCategory() {
        return objectCategory;
    }

    public void setObjectCategory(ObjectCategory objectCategory) {
        this.objectCategory = objectCategory;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BinaryContent getPicture() {
        return picture;
    }

    public void setPicture(BinaryContent picture) {
        this.picture = picture;
    }

    public BinaryContent getIcon() {
        return icon;
    }

    public void setIcon(BinaryContent icon) {
        this.icon = icon;
    }

    public ALCityObject() {
    }

    public ALCityObject(String title, ObjectCategory objectCategory, Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.title = title;
        this.objectCategory = objectCategory;
    }
}
