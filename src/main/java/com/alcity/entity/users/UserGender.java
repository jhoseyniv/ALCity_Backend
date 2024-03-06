package com.alcity.entity.users;

import com.alcity.entity.base.BaseTable;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

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

    @OneToMany(mappedBy = "gender", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<ApplicationMember> users;

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

    public Set<ApplicationMember> getUsers() {
        return users;
    }

    public void setUsers(Set<ApplicationMember> users) {
        this.users = users;
    }
}
