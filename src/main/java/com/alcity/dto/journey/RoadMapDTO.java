package com.alcity.dto.journey;

public class RoadMapDTO {

    private Long id;
    private Integer xpos;
    private Integer ypos;
    private Long graphicId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public RoadMapDTO() {
    }

    public RoadMapDTO(Long id, Integer xpos, Integer ypos, Long graphicId) {
        this.id =id;
        this.xpos = xpos;
        this.ypos = ypos;
        this.graphicId = graphicId;
    }
}
