package com.alcity.entity.puzzle;

import com.alcity.entity.base.BaseTable;
import com.alcity.entity.appmember.AppMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;



@Table(name = "alcity_instance_inpl")

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Instance extends BaseTable implements Serializable {

    @Column(name="name")
    private String name;

    @Column(name="row")
    private Integer row;

    @Column(name="col")
    private Integer col;

    @Column(name="zOrder")
    private Integer zorder;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "alcity_Object_In_PG_id", nullable = false)
    @JsonIgnore
    private PGObject alCityObjectInPG;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "pl_cell_id", nullable = true)
    @JsonIgnore
    private PLCell plCell;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "puzzle_Level_id", nullable = false)
    @JsonIgnore
    private PuzzleLevel puzzleLevel;

    public Instance(String name, Integer row, Integer col, Integer zorder, PLCell plCell, PGObject alCityObjectInPG, PuzzleLevel puzzleLevel, Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.name = name;
        this.plCell = plCell;
        this.row = row;
        this.col = col;
        this.zorder = zorder;
        this.alCityObjectInPG = alCityObjectInPG;
        this.puzzleLevel = puzzleLevel;
    }
}
