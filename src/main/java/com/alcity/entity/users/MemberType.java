package com.alcity.entity.users;


import com.alcity.entity.base.BaseTable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="MemberType")
public class MemberType extends BaseTable implements Serializable {
    public MemberType(Long version, Long creationDate, Long creatorUser, Long lastModifiedDate, Long lastModifiedUser, String label, String value) {
        super(version, creationDate, creatorUser, lastModifiedDate, lastModifiedUser);
        this.label = label;
        this.value = value;
    }

    public MemberType() {
    }

    @Column(name="label")
    private String label;

    @Column(name="value")
    private String value;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
