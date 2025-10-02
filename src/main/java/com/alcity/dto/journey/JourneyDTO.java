package com.alcity.dto.journey;

import com.alcity.dto.base.BinaryContentDTO;
import com.alcity.entity.journey.JourneyLearningSkill;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
@NoArgsConstructor

public class JourneyDTO   {
    private Long id;
    private Long version;
    private String created;
    private String updated;
    private String createdBy;

    private String updatedBy;

    private String title;

    private Long picId;
    private Long buttonPassedIconId;
    private Long buttonCurrenIconId;
    private Long buttonLockedIconId;

    private Integer ordering;

    private Integer minToPassStar;

    private Integer minToOpenStar;


    public JourneyDTO(Long id, Long version, String created, String updated, String createdBy, String updatedBy,
                      String title, Long picId, Long buttonPassedIconId, Long buttonCurrenIconId, Long buttonLockedIconId, Integer ordering, Integer minToPassStar, Integer minToOpenStar) {
        this.id = id;
        this.version = version;
        this.created = created;
        this.updated = updated;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.title = title;
        this.picId = picId;
        this.buttonPassedIconId = buttonPassedIconId;
        this.buttonCurrenIconId = buttonCurrenIconId;
        this.buttonLockedIconId = buttonLockedIconId;
        this.ordering = ordering;
        this.minToPassStar = minToPassStar;
        this.minToOpenStar = minToOpenStar;
    }
}
