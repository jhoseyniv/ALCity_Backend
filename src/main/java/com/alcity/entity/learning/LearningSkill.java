package com.alcity.entity.learning;

import com.alcity.entity.base.BaseTable;
import com.alcity.entity.users.ApplicationMember;

import javax.persistence.Entity;
import java.io.Serializable;


@Entity
public class LearningSkill extends BaseTable implements Serializable {
    public LearningSkill() {
    }

    public LearningSkill(String label, String value, Long version, Long created, Long updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(label, value, version, created, updated, createdBy, updatedBy);
    }
}
