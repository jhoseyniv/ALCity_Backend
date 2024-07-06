package com.alcity.entity.base;



import com.alcity.entity.appmember.AppMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS )
public abstract class BaseItemSet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    public Long getId() {
        return id;
    }

    @Column(name="label",unique = true)
    private String label;

    @Column(name="value",unique = true)
    private String value;


    @NotNull(message = "{bName.notempty}")
    private Long version;

    @NotNull(message = "{bLength.notempty}")
    private String created;


    @NotNull(message = "{bHeight.notempty}")
    private String updated;

    public String getLabel() {
        return label;
    }

    public String getValue() {
        return value;
    }

    public Long getVersion() {
        return version;
    }

    public String getCreated() {
        return created;
    }

    public String getUpdated() {
        return updated;
    }

    public AppMember getCreatedBy() {
        return createdBy;
    }

    public AppMember getUpdatedBy() {
        return updatedBy;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name ="createdBy",nullable = true)
    @JsonIgnore
    private AppMember createdBy;

    public void setId(Long id) {
        this.id = id;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn( name ="updatedBy",  nullable = true)
    @JsonIgnore
    private AppMember updatedBy;

    public void setCreatedBy(AppMember createdBy) {
        this.createdBy = createdBy;
    }

    public void setUpdatedBy(AppMember updatedBy) {
        this.updatedBy = updatedBy;
    }

    public BaseItemSet() {
    }

    public BaseItemSet(String label, String value, Long version, String created, String updated, AppMember createdBy , AppMember updatedBy) {
        this.label = label;
        this.value = value;
        this.version = version;
        this.created = created;
        this.updated = updated;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }
}
