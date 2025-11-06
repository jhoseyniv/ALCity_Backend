package com.alcity.entity.base;

import com.alcity.entity.appmember.AppMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS )
public abstract class BaseTable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    public Long getId() {
        return id;
    }

    @NotNull(message = "{version.notempty}")
    private Long version;

    @NotNull(message = "{created.notempty}")
    private String created;


    @NotNull(message = "{created.notempty}")
    private String updated;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name ="createdBy",nullable = true)
    @JsonIgnore
    private AppMember createdBy;



    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn( name ="updatedBy",  nullable = true)
    @JsonIgnore
    private AppMember updatedBy;

     public BaseTable(Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        this.version = version;
        this.created = created;
        this.updated = updated;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }
}
