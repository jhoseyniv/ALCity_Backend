package com.alcity.entity.base;

import com.alcity.entity.users.ApplicationMember;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class CameraSetup  extends BaseTable implements Serializable {

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

    public CameraSetup(Long version, Long created, Long updated, ApplicationMember createdBy, ApplicationMember updatedBy, Integer xPosition, Integer yPosition, Integer zPosition, Integer xRotation, Integer yRotation, Integer zRotation) {
        super(version, created, updated, createdBy, updatedBy);
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.zPosition = zPosition;
        this.xRotation = xRotation;
        this.yRotation = yRotation;
        this.zRotation = zRotation;
    }
}
