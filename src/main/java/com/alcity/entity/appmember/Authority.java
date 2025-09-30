package com.alcity.entity.appmember;

import com.alcity.entity.base.BaseTable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Entity
@Getter
@Setter
@NoArgsConstructor

@Table(name = "Authority")

public class Authority  extends BaseTable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String name;


    public Authority(Long version, String created, String updated, AppMember createdBy, AppMember updatedBy, String name) {
        super(version, created, updated, createdBy, updatedBy);
        this.name = name;
    }

    public Authority(String name) {
        this.name = name;
    }

}
