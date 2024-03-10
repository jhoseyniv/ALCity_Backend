package com.alcity.entity.base;

import com.alcity.entity.users.ApplicationMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS )
public class RecordInformation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    public Long getId() {
        return id;
    }
    @NotNull(message = "{bName.notempty}")
    private Long version;

    @NotNull(message = "{bLength.notempty}")
    private Long creationDate;


    @NotNull(message = "{bHeight.notempty}")
    private Long lastModifiedDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name ="createdBy",nullable = true)
    @JsonIgnore
    private ApplicationMember createdBy;



    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn( name ="updatedBy",  nullable = true)
    @JsonIgnore
    private ApplicationMember updatedBy;

    public RecordInformation() {
    }

    public RecordInformation(Long version, Long creationDate, Long lastModifiedDate, ApplicationMember createdBy, ApplicationMember updatedBy) {
        this.version = version;
        this.creationDate = creationDate;
        this.lastModifiedDate = lastModifiedDate;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }
}
