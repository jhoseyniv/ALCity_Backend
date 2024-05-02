package com.alcity.entity.base;

import com.alcity.entity.users.ApplicationMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS )
public class BaseTable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    public Long getId() {
        return id;
    }
    @NotNull(message = "{bName.notempty}")
    private Long version;

    @NotNull(message = "{bLength.notempty}")
    private String created;


    @NotNull(message = "{bHeight.notempty}")
    private String updated;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name ="createdBy",nullable = true)
    @JsonIgnore
    private ApplicationMember createdBy;



    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn( name ="updatedBy",  nullable = true)
    @JsonIgnore
    private ApplicationMember updatedBy;

    public Long getVersion() {
        return version;
    }

    public String getCreated() {
        return created;
    }

    public String getUpdated() {
        return updated;
    }

    public ApplicationMember getCreatedBy() {
        return createdBy;
    }

    public ApplicationMember getUpdatedBy() {
        return updatedBy;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setCreatedBy(ApplicationMember createdBy) {
        this.createdBy = createdBy;
    }

    public void setUpdatedBy(ApplicationMember updatedBy) {
        this.updatedBy = updatedBy;
    }

    public BaseTable() {
    }

    public BaseTable(Long version, String created, String updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        this.version = version;
        this.created = created;
        this.updated = updated;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }
}
