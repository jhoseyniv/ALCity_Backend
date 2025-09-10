package com.alcity.dto.appmember;

import jakarta.persistence.criteria.CriteriaBuilder;

public class AppMemberJourneyDTO {
    private Long journeyId;
    private String title;
    private Boolean isOpen;
    private Long  picId;
    private Integer ordering;
    private Integer  minToPassStar;
    private Integer  minToOpenStar;
    private Integer  currentStar;
    private Long appMemberId;

    public Long getJourneyId() {
        return journeyId;
    }

    public void setJourneyId(Long journeyId) {
        this.journeyId = journeyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getOpen() {
        return isOpen;
    }

    public void setOpen(Boolean open) {
        isOpen = open;
    }

    public Long getPicId() {
        return picId;
    }

    public void setPicId(Long picId) {
        this.picId = picId;
    }

    public Integer getMinToPassStar() {
        return minToPassStar;
    }

    public void setMinToPassStar(Integer minToPassStar) {
        this.minToPassStar = minToPassStar;
    }

    public Integer getMinToOpenStar() {
        return minToOpenStar;
    }

    public void setMinToOpenStar(Integer minToOpenStar) {
        this.minToOpenStar = minToOpenStar;
    }

    public Integer getCurrentStar() {
        return currentStar;
    }

    public void setCurrentStar(Integer currentStar) {
        this.currentStar = currentStar;
    }

    public Long getAppMemberId() {
        return appMemberId;
    }

    public void setAppMemberId(Long appMemberId) {
        this.appMemberId = appMemberId;
    }

    public Integer getOrdering() {
        return ordering;
    }

    public void setOrdering(Integer ordering) {
        this.ordering = ordering;
    }

    public AppMemberJourneyDTO() {
    }

    public AppMemberJourneyDTO(Long journeyId, String title,Integer ordering, Boolean isOpen, Long picId, Integer minToPassStar, Integer minToOpenStar, Integer currentStar, Long appMemberId) {
        this.journeyId = journeyId;
        this.title = title;
        this.isOpen = isOpen;
        this.picId = picId;
        this.currentStar = currentStar;
        this.ordering = ordering;
        this.minToPassStar = minToPassStar;
        this.minToOpenStar = minToOpenStar;
        this.currentStar = currentStar;
        this.appMemberId = appMemberId;
    }
}
