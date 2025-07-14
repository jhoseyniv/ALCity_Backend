package com.alcity.entity.puzzle;

import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.base.BaseTable;
import com.alcity.entity.base.PuzzleCategory;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class PLTemplate extends BaseTable implements Serializable {

    @Column(name="title" ,unique = true)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "puzzle_category_id", nullable = true)
    @JsonIgnore
    private PuzzleCategory puzzleCategory;

    @Column(name="content")
    private StringBuffer content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public PuzzleCategory getPuzzleCategory() {
        return puzzleCategory;
    }

    public void setPuzzleCategory(PuzzleCategory puzzleCategory) {
        this.puzzleCategory = puzzleCategory;
    }

    public StringBuffer getContent() {
        return content;
    }

    public void setContent(StringBuffer content) {
        this.content = content;
    }

    public PLTemplate() {
    }

    public PLTemplate(String title, PuzzleCategory puzzleCategory, StringBuffer content ,Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.title = title;
        this.puzzleCategory = puzzleCategory;
        this.content = content;
    }
}
