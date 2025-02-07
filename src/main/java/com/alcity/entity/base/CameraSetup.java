package com.alcity.entity.base;

import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.puzzle.PLGround;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
public class CameraSetup  extends BaseTable implements Serializable {


    @Column(name="title")
    private String title;

    @Column(name="xPosition")
    private Integer xPosition;

    @Column(name="yPosition")
    private Integer yPosition;

    @Column(name="zPosition")
    private Integer zPosition;

    @Column(name="xRotation")
    private Integer xRotation;

    @Column(name="yRotation")
    private Integer yRotation;

    @Column(name="zRotation")
    private Integer zRotation;

//    @OneToMany(mappedBy = "cameraSetup", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JsonIgnore
//    private Collection<PLGround> plGrounds;
//
//    public Collection<PLGround> getPlGrounds() {
//        return plGrounds;
//    }
//
//    public void setPlGrounds(Collection<PLGround> plGrounds) {
//        this.plGrounds = plGrounds;
//    }

    public CameraSetup() {
    }

    public Integer getxPosition() {
        return xPosition;
    }

    public void setxPosition(Integer xPosition) {
        this.xPosition = xPosition;
    }

    public Integer getyPosition() {
        return yPosition;
    }

    public void setyPosition(Integer yPosition) {
        this.yPosition = yPosition;
    }

    public Integer getzPosition() {
        return zPosition;
    }

    public void setzPosition(Integer zPosition) {
        this.zPosition = zPosition;
    }

    public Integer getxRotation() {
        return xRotation;
    }

    public void setxRotation(Integer xRotation) {
        this.xRotation = xRotation;
    }

    public Integer getyRotation() {
        return yRotation;
    }

    public void setyRotation(Integer yRotation) {
        this.yRotation = yRotation;
    }

    public Integer getzRotation() {
        return zRotation;
    }

    public void setzRotation(Integer zRotation) {
        this.zRotation = zRotation;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CameraSetup(String title,Integer xPosition, Integer yPosition, Integer zPosition, Integer xRotation, Integer yRotation, Integer zRotation,
                       Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.title = title;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.zPosition = zPosition;
        this.xRotation = xRotation;
        this.yRotation = yRotation;
        this.zRotation = zRotation;
    }
}
