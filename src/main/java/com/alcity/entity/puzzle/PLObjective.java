package com.alcity.entity.puzzle;

import com.alcity.entity.appmember.ObjectiveTransaction;
import com.alcity.entity.base.BaseTable;
import com.alcity.entity.learning.LearningSkill;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.appmember.WalletItem;
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

public class PLObjective extends BaseTable implements Serializable {

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="skillAmount")
    private Float skillAmount;

    @Column(name="rewardAmount")
    private Float rewardAmount;

    @Column(name="condition" )
    private StringBuffer condition;


    @OneToMany(mappedBy = "plObjective", fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<ObjectiveTransaction> objectiveTransactions;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "learning_skill_id", nullable = true)
    @JsonIgnore
    private LearningSkill learningSkill;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "wallet_item_id", nullable = true)
    @JsonIgnore
    private WalletItem walletItem;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "puzzle_Level_id", nullable = true)
    @JsonIgnore
    private PuzzleLevel puzzleLevel;

    public PLObjective(String title, String description, Float skillAmount, Float rewardAmount, StringBuffer condition, LearningSkill learningSkill, WalletItem walletItem, PuzzleLevel puzzleLevel,
                       Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.title = title;
        this.description = description;
        this.skillAmount = skillAmount;
        this.rewardAmount = rewardAmount;
        this.condition = condition;
        this.learningSkill = learningSkill;
        this.walletItem = walletItem;
        this.puzzleLevel = puzzleLevel;
    }
}
