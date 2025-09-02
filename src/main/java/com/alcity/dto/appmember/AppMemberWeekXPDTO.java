package com.alcity.dto.appmember;

import com.alcity.entity.appmember.AppMember;

import java.io.Serializable;

public class AppMemberWeekXPDTO implements Serializable {
    Integer dayOfWeek;
    String dayOfWeekName;
    Float xp;
    Long memberId;
    String date;

    public Integer getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(Integer dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getDayOfWeekName() {
        return dayOfWeekName;
    }

    public void setDayOfWeekName(String dayOfWeekName) {
        this.dayOfWeekName = dayOfWeekName;
    }

    public Float getXp() {
        return xp;
    }

    public void setXp(Float xp) {
        this.xp = xp;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public AppMemberWeekXPDTO() {
    }

    public AppMemberWeekXPDTO(Integer dayOfWeek, String dayOfWeekName, Float xp, Long memberId, String date) {
        this.dayOfWeek = dayOfWeek;
        this.dayOfWeekName = dayOfWeekName;
        this.xp = xp;
        this.memberId = memberId;
        this.date = date;
    }
}
