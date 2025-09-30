package com.alcity.entity.learning;

import com.alcity.entity.base.BaseTable;
import com.alcity.entity.appmember.AppMember;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class LearningTopic extends BaseTable {

    @NotNull(message = "{bHeight.notempty}")
    @Column(name="title",unique = true)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    private LearningTopic parentTopic;

    @OneToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<LearningTopic> learningTopicSet;

    public LearningTopic(String title, LearningTopic parentTopic , Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.title = title;
        this.parentTopic = parentTopic;
    }
}
