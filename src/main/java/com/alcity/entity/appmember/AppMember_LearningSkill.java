package com.alcity.entity.appmember;


import com.alcity.entity.base.BaseTable;
import com.alcity.entity.learning.LearningSkill;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Table(uniqueConstraints={
                 @UniqueConstraint(
                         columnNames = {"application_member_id", "learning_skill_id"}
                 )
            }
      )

@Entity
@Getter
@Setter
@NoArgsConstructor

public class AppMember_LearningSkill extends BaseTable implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "application_member_id", referencedColumnName = "id")
    @JsonIgnore
    private AppMember applicationMember;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "learning_skill_id", referencedColumnName = "id")
    @JsonIgnore
    private LearningSkill learningSkill;

    @Column(name="amount")
    private Float amount;

    @Column(name="level")
    private Long level;


    public AppMember_LearningSkill( AppMember applicationMember, LearningSkill learningSkill, Float amount, Long level ,
                                    Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.applicationMember = applicationMember;
        this.learningSkill = learningSkill;
        this.amount = amount;
        this.level = level;
    }
}
