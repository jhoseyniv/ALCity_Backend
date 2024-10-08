package com.alcity.entity.learning;

import com.alcity.entity.base.BaseItemSet;
import com.alcity.entity.appmember.AppMember;

import jakarta.persistence.*;
import java.io.Serializable;


@Entity
public class LearningSkill extends BaseItemSet implements Serializable {
    public LearningSkill() {
    }

    public LearningSkill(String label, String value, Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(label, value, version, created, updated, createdBy, updatedBy);
    }
}
