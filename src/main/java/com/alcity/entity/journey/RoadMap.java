package com.alcity.entity.journey;

import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.base.BaseTable;
import com.alcity.entity.base.BinaryContent;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name="RoadMap")
public class RoadMap  extends BaseTable implements Serializable {

    @Column(name="xpos")
    private Integer xpos;

    @Column(name="ypos")
    private Integer ypos;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "binary_content_id", nullable = true)
    @JsonIgnore
    private BinaryContent graphic;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "journey_id", nullable = false)
    @JsonIgnore
    private Journey journey;


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

    public BinaryContent getGraphic() {
        return graphic;
    }

    public void setGraphic(BinaryContent graphic) {
        this.graphic = graphic;
    }

    public RoadMap() {
    }

    public RoadMap(Integer xpos, Integer ypos, BinaryContent graphic , Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.xpos = xpos;
        this.ypos = ypos;
        this.graphic = graphic;
    }
}
