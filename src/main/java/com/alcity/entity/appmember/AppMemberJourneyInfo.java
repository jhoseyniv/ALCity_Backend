package com.alcity.entity.appmember;

import com.alcity.dto.journey.RoadMapDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor

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
