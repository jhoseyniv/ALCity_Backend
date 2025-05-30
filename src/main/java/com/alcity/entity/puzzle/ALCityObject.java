package com.alcity.entity.puzzle;


import com.alcity.entity.base.BaseTable;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.alobject.ObjectCategory;
import com.alcity.entity.appmember.AppMember;
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
    @JoinColumn(name = "pic_id", nullable = false)
    @JsonIgnore
    private BinaryContent pic;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "icon_id", nullable = false)
    @JsonIgnore
    private BinaryContent icon;

    @Column(name="is3dObject")
    private Boolean is3dObject;

    public Boolean getIs3dObject() {
        return is3dObject;
    }

    public void setIs3dObject(Boolean is3dObject) {
        this.is3dObject = is3dObject;
    }

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

    public BinaryContent getPic() {
        return pic;
    }

    public void setPic(BinaryContent pic) {
        this.pic = pic;
    }

    public BinaryContent getIcon() {
        return icon;
    }

    public void setIcon(BinaryContent icon) {
        this.icon = icon;
    }

    public ALCityObject() {
    }

    public ALCityObject(Long version, String created, String updated, AppMember createdBy, AppMember updatedBy,
                        String title, ObjectCategory objectCategory,Boolean is3dObject , BinaryContent pic, BinaryContent icon) {
        super(version, created, updated, createdBy, updatedBy);
        this.title = title;
        this.is3dObject = is3dObject;
        this.objectCategory = objectCategory;
        this.pic = pic;
        this.icon = icon;
    }
}
