package com.alcity.dto.appmember;

public class RoadMapDTO {
    private Integer xpos;
    private Integer ypos;
    private Long graphicId;

    public Integer getXpos() {
        return xpos;
    }

    public void setXpos(Integer xpos) {
        this.xpos = xpos;
    }

    public Integer getYpos() {
        return ypos;
    }

    public void setYpos(Integer ypos) {
        this.ypos = ypos;
    }

    public Long getGraphicId() {
        return graphicId;
    }

    public void setGraphicId(Long graphicId) {
        this.graphicId = graphicId;
    }

    public RoadMapDTO(Integer xpos, Integer ypos, Long graphicId) {
        this.xpos = xpos;
        this.ypos = ypos;
        this.graphicId = graphicId;
    }
}
