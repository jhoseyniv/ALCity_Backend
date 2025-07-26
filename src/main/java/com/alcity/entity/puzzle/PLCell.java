package com.alcity.entity.puzzle;

import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.base.BaseTable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Collection;

@Entity
public class PLCell extends BaseTable implements Serializable {

    @Column(name="roow")
    private Integer row;

    @Column(name="col")
    private Integer col;

    @Column(name="zOrder")
    private Integer zOrder;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "puzzle_Ground_id", nullable = false)
    @JsonIgnore
    private PLGround plGround;

    @OneToMany(mappedBy = "plCell", fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<ALCityInstanceInPL> instances;

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getCol() {
        return col;
    }

    public void setCol(Integer col) {
        this.col = col;
    }

    public Integer getzOrder() {
        return zOrder;
    }

    public void setzOrder(Integer zOrder) {
        this.zOrder = zOrder;
    }

    public PLGround getPlGround() {
        return plGround;
    }

    public void setPlGround(PLGround plGround) {
        this.plGround = plGround;
    }

    public Collection<ALCityInstanceInPL> getInstances() {
        return instances;
    }

    public void setInstances(Collection<ALCityInstanceInPL> instances) {
        this.instances = instances;
    }

    public PLCell() {
    }

    public PLCell(Long version, String created, String updated, AppMember createdBy, AppMember updatedBy, Integer row, Integer col, Integer zOrder, PLGround plGround) {
        super(version, created, updated, createdBy, updatedBy);
        this.row = row;
        this.col = col;
        this.zOrder = zOrder;
        this.plGround = plGround;
    }
}


