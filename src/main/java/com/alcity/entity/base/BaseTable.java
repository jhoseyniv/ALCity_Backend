package com.alcity.entity.base;



import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS )
public abstract class BaseTable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    public Long getId() {
        return id;
    }

    @Column(name="label")
    private String label;

    @Column(name="value")
    private String value;


    @NotNull(message = "{bName.notempty}")
    private Long version;

    @NotNull(message = "{bLength.notempty}")
    private Long creationDate;


    @NotNull(message = "{bHeight.notempty}")
    private Long lastModifiedDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn( name ="creatorUser", nullable = false)
    @JsonIgnore
    private ALCitySystemUser creatorUser;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn( name ="lastModifiedUser",  nullable = false)
    @JsonIgnore
    private ALCitySystemUser lastModifiedUser;


    public BaseTable() {
    }

    public BaseTable(String label, String value, Long version, Long creationDate, Long lastModifiedDate, ALCitySystemUser creatorUser, ALCitySystemUser lastModifiedUser) {
        this.label = label;
        this.value = value;
        this.version = version;
        this.creationDate = creationDate;
        this.lastModifiedDate = lastModifiedDate;
        this.creatorUser = creatorUser;
        this.lastModifiedUser = lastModifiedUser;
    }
}
