package com.alcity.entity.base;

import com.alcity.entity.puzzle.PLTemplate;
import com.alcity.entity.puzzle.PuzzleGroup;
import com.alcity.entity.appmember.AppMember;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class PuzzleCategory extends BaseItemSet implements Serializable {

    @Column(name="templateEditor")
    private String templateEditor;

    @OneToMany(mappedBy = "puzzleCategory", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<PuzzleGroup> puzzleGroupCollection;


    @OneToMany(mappedBy = "puzzleCategory", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<PLTemplate> plTemplates;


    public PuzzleCategory(String label, String value,String templateEditor , Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(label, value, version, created, updated, createdBy,updatedBy);
        this.templateEditor = templateEditor;
    }
}
