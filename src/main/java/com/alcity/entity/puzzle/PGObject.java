package com.alcity.entity.puzzle;

import com.alcity.entity.base.BaseTable;
import com.alcity.entity.appmember.AppMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Collection;

@Table(name = "alcity_object_inpg" ,uniqueConstraints={
        @UniqueConstraint(columnNames = {"title", "code"})
})
@Entity
@Getter
@Setter
@NoArgsConstructor
public class PGObject extends BaseTable implements Serializable {

    @Column(name="title")
    private String title;

    @Column(name="code")
    private String code;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "puzzle_group_id", nullable = false)
    @JsonIgnore
    private PuzzleGroup puzzleGroup;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "alcity_object_id", nullable = false)
    @JsonIgnore
    private BaseObject alCityObject;

    @OneToMany(mappedBy = "alCityObjectInPG", fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<Instance> alCityInstanceInPLCollection;

    public PGObject(String title, String code, PuzzleGroup puzzleGroup, BaseObject alCityObject, Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.title = title;
        this.code = code;
        this.puzzleGroup = puzzleGroup;
        this.alCityObject = alCityObject;
    }
}
