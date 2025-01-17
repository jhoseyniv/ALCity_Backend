package com.alcity.dtotransient;

import com.alcity.dto.journey.RoadMapDTO;

import java.util.Collection;

public class AppMemberJourneyInfo {
    private Long journeyId;
    private String journeyTitle;

    private Long passedIconId;
    private Long currentIconId;
    private Long lockedIconId;
    private Long appMemberId;
    private String appMemberUserName;

    private Collection<RoadMapDTO> roadMaps;
    private Collection<AppMemberStepInfo> steps;


    public Long getJourneyId() {
        return journeyId;
    }

    public void setJourneyId(Long journeyId) {
        this.journeyId = journeyId;
    }

    public Long getAppMemberId() {
        return appMemberId;
    }

    public void setAppMemberId(Long appMemberId) {
        this.appMemberId = appMemberId;
    }


    public String getJourneyTitle() {
        return journeyTitle;
    }

    public void setJourneyTitle(String journeyTitle) {
        this.journeyTitle = journeyTitle;
    }

    public String getAppMemberUserName() {
        return appMemberUserName;
    }

    public void setAppMemberUserName(String appMemberUserName) {
        this.appMemberUserName = appMemberUserName;
    }

    public Collection<RoadMapDTO> getRoadMaps() {
        return roadMaps;
    }

    public void setRoadMaps(Collection<RoadMapDTO> roadMaps) {
        this.roadMaps = roadMaps;
    }

    public Collection<AppMemberStepInfo> getSteps() {
        return steps;
    }

    public void setSteps(Collection<AppMemberStepInfo> steps) {
        this.steps = steps;
    }

    public Long getPassedIconId() {
        return passedIconId;
    }

    public void setPassedIconId(Long passedIconId) {
        this.passedIconId = passedIconId;
    }

    public Long getCurrentIconId() {
        return currentIconId;
    }

    public void setCurrentIconId(Long currentIconId) {
        this.currentIconId = currentIconId;
    }

    public Long getLockedIconId() {
        return lockedIconId;
    }

    public void setLockedIconId(Long lockedIconId) {
        this.lockedIconId = lockedIconId;
    }

    public AppMemberJourneyInfo() {
    }


    public AppMemberJourneyInfo(Long journeyId, String journeyTitle,Long passedIconId,Long currentIconId,Long lockedIconId, Long appMemberId, String appMemberUserName,Collection<AppMemberStepInfo> steps , Collection<RoadMapDTO> roadMaps) {
        this.journeyId = journeyId;
        this.journeyTitle = journeyTitle;
        this.currentIconId =currentIconId;
        this.passedIconId = passedIconId;
        this.lockedIconId = lockedIconId;
        this.appMemberId = appMemberId;
        this.appMemberUserName = appMemberUserName;
        this.roadMaps = roadMaps;
        this.steps = steps;
    }
}
