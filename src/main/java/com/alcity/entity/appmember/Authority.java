package com.alcity.entity.appmember;

import com.alcity.entity.base.BaseTable;
import jakarta.persistence.*;

import java.io.Serializable;
@Entity
@Table(name = "Authority")
public class Authority  extends BaseTable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String name;

    public Authority() {

    }

    public Authority(Long version, String created, String updated, AppMember createdBy, AppMember updatedBy, String name) {
        super(version, created, updated, createdBy, updatedBy);
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public Authority(String name) {
        this.name = name;
    }

}
