package com.alcity.dto.journey;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor


public class RoadMapDTO {

    private Long id;
    private Integer xpos;
    private Integer ypos;
    private Long journeyId;
    private Long graphicId;



     public RoadMapDTO(Long id, Integer xpos, Integer ypos, Long journeyId, Long graphicId) {
        this.id = id;
        this.xpos = xpos;
        this.ypos = ypos;
        this.journeyId = journeyId;
        this.graphicId = graphicId;
    }
}
