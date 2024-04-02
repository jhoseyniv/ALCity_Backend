package com.alcity.dto.journey;

import com.alcity.dto.base.BaseTableDTO;

public class JourneyStepDTO extends BaseTableDTO {

    private String title;
    private Integer ordering;
    private Integer xpos;
    private Integer ypos;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getOrdering() {
        return ordering;
    }

    public void setOrdering(Integer ordering) {
        this.ordering = ordering;
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

    public JourneyStepDTO() {
    }

    public JourneyStepDTO(String title, Integer ordering, Integer xpos, Integer ypos, Long id, Long version, String created, String updated) {
        super(id, version, created, updated);
        this.title = title;
        this.ordering = ordering;
        this.xpos = xpos;
        this.ypos = ypos;
    }
}
