package com.alcity.entity.puzzle;

import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.base.BaseTable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class PLCell extends BaseTable implements Serializable {

    @Column(name="roow")
    private Integer row;

    @Column(name="col")
    private Integer col;

    @Column(name="zOrder")
    private Integer zorder;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "puzzle_Ground_id", nullable = false)
    @JsonIgnore
    private PLGround plGround;

    @OneToMany(mappedBy = "plCell", fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<Instance> instances;

    public PLCell(Long version, String created, String updated, AppMember createdBy, AppMember updatedBy, Integer row, Integer col, Integer zorder, PLGround plGround) {
        super(version, created, updated, createdBy, updatedBy);
        this.row = row;
        this.col = col;
        this.zorder = zorder;
        this.plGround = plGround;
    }
}


