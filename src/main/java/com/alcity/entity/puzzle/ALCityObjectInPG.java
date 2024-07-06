package com.alcity.entity.puzzle;

import com.alcity.entity.base.BaseTable;
import com.alcity.entity.appmember.AppMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"title", "code"})
})
@Entity
public class ALCityObjectInPG extends BaseTable implements Serializable {

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
    private ALCityObject alCityObject;

    @OneToMany(mappedBy = "alCityObjectInPG", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Collection<ALCityInstanceInPL> alCityInstanceInPLCollection;

    public Collection<ALCityInstanceInPL> getAlCityInstanceInPLCollection() {
        return alCityInstanceInPLCollection;
    }

    public void setAlCityInstanceInPLCollection(Collection<ALCityInstanceInPL> alCityInstanceInPLCollection) {
        this.alCityInstanceInPLCollection = alCityInstanceInPLCollection;
    }

    public ALCityObjectInPG() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public PuzzleGroup getPuzzleGroup() {
        return puzzleGroup;
    }

    public void setPuzzleGroup(PuzzleGroup puzzleGroup) {
        this.puzzleGroup = puzzleGroup;
    }

    public ALCityObject getAlCityObject() {
        return alCityObject;
    }

    public void setAlCityObject(ALCityObject alCityObject) {
        this.alCityObject = alCityObject;
    }

    public ALCityObjectInPG(String title, String code, PuzzleGroup puzzleGroup, ALCityObject alCityObject, Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.title = title;
        this.code = code;
        this.puzzleGroup = puzzleGroup;
        this.alCityObject = alCityObject;
    }
}
