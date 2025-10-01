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
    Long memberId;
    String date;

    public AppMemberXPDTO(Integer dayOfWeek, String dayOfWeekName, Float xp, Long memberId, String date) {
        this.dayOfWeek = dayOfWeek;
        this.dayOfWeekName = dayOfWeekName;
        this.xp = xp;
        this.memberId = memberId;
        this.date = date;
    }
}
