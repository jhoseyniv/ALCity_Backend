package com.alcity.entity.appuser;

import com.alcity.entity.base.BaseTable;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="User_Gender")
public class UserGender extends BaseTable implements Serializable {


    public UserGender(Long version, Long creationDate, Long creatorUser, Long lastModifiedDate, Long lastModifiedUser, String label,String value) {
        super(version, creationDate, creatorUser, lastModifiedDate, lastModifiedUser);
        this.label = label;
        this.value = value;
    }

    public UserGender() {
    }

    @Column(name="label")
    private String label;

    @Column(name="value")
    private String value;


}
