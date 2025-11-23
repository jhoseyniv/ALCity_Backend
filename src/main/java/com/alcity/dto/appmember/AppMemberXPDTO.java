package com.alcity.dto.appmember;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor

public class AppMemberXPDTO implements Serializable {
    Integer dayOfWeek;
    String dayOfWeekName;
    Float xp;
    Long id;
    String date;

    public AppMemberXPDTO(Long id, Integer dayOfWeek, String dayOfWeekName, Float xp,  String date) {
        this.dayOfWeek = dayOfWeek;
        this.dayOfWeekName = dayOfWeekName;
        this.xp = xp;
        this.id = id;
        this.date = date;
    }
}
