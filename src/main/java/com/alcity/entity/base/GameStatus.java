package com.alcity.entity.base;


import com.alcity.entity.users.ApplicationMember;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class GameStatus extends BaseItemSet implements Serializable {

    public GameStatus() {
    }

    public GameStatus(String label, String value, Long version, Long created, Long updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(label, value, version, created, updated, createdBy, updatedBy);
    }
}