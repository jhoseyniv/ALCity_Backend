package com.alcity.entity.learning;

import com.alcity.entity.alenum.SkillType;
import com.alcity.entity.alenum.UserGender;
import com.alcity.entity.base.BaseItemSet;
import com.alcity.entity.appmember.AppMember;

import com.alcity.entity.base.BaseTable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;


@Entity
public class LearningSkill extends BaseTable implements Serializable {

    @NotNull(message = "{bHeight.notempty}")
    @Column(name="title",unique = true)
    private String title;

    @Enumerated(EnumType.ORDINAL)
    private SkillType type;

    @Column(name="weight")
    private Float weight;

    @Column(name="levelUpSize")
    private Long levelUpSize;


    @ManyToOne(fetch = FetchType.LAZY)
    private LearningSkill parentSkill;

    @OneToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<LearningSkill> learningSkills;

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Long getLevelUpSize() {
        return levelUpSize;
    }

    public void setLevelUpSize(Long levelUpSize) {
        this.levelUpSize = levelUpSize;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public SkillType getType() {
        return type;
    }

    public void setType(SkillType type) {
        this.type = type;
    }

    public LearningSkill getParentSkill() {
        return parentSkill;
    }

    public void setParentSkill(LearningSkill parentSkill) {
        this.parentSkill = parentSkill;
    }

    public Collection<LearningSkill> getLearningSkills() {
        return learningSkills;
    }

    public void setLearningSkills(Collection<LearningSkill> learningSkills) {
        this.learningSkills = learningSkills;
    }

    public LearningSkill() {
    }

    public LearningSkill(Long version, String created, String updated, AppMember createdBy, AppMember updatedBy,
                         String title, SkillType type, LearningSkill parentSkill,Float weight, Long levelUpSize) {
        super(version, created, updated, createdBy, updatedBy);
        this.title = title;
        this.type = type;
        this.parentSkill = parentSkill;
        this.weight = weight;
        this.levelUpSize = levelUpSize;
    }
}
