package com.alcity.dto.appmember;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AppMemberJourneyDTO {
    private Long journeyId;
    private String title;
    private Boolean open;
    private Long  picId;
    private Integer ordering;
    private Integer  minToPassStar;
    private Integer  minToOpenStar;
    private Integer  currentStar;
    private Long appMemberId;

    public AppMemberJourneyDTO(Long journeyId, String title,Integer ordering, Boolean open, Long picId, Integer minToPassStar, Integer minToOpenStar, Integer currentStar, Long appMemberId) {
        this.journeyId = journeyId;
        this.title = title;
        this.open = open;
        this.picId = picId;
        this.currentStar = currentStar;
        this.ordering = ordering;
        this.minToPassStar = minToPassStar;
        this.minToOpenStar = minToOpenStar;
        this.currentStar = currentStar;
        this.appMemberId = appMemberId;
    }
}
