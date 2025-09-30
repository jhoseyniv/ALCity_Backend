package com.alcity.entity.journey;

import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.base.BaseTable;
import com.alcity.entity.base.BinaryContent;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor

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

    public RoadMap(Integer xpos, Integer ypos, BinaryContent graphic,Journey journey,Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.xpos = xpos;
        this.ypos = ypos;
        this.graphic = graphic;
        this.journey = journey;
    }
}
