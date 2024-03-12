package com.alcity.dto;

public class CameraSetupDTO {

    private Integer xPosition;
    private Integer yPosition;
    private Integer zPosition;

    private Integer xRotation;
    private Integer yRotation;
    private Integer zRotation;

    public CameraSetupDTO() {
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

    public CameraSetupDTO(Integer xPosition, Integer yPosition, Integer zPosition, Integer xRotation, Integer yRotation, Integer zRotation) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.zPosition = zPosition;
        this.xRotation = xRotation;
        this.yRotation = yRotation;
        this.zRotation = zRotation;
    }
    public String getCameraSetupInfo(){
        return "position:{" +
                "x:" + getxPosition() +",y:"+getyPosition()+",z:" + getzPosition() +
                "},"+
                "rotation:{"+
                "x:" + getxRotation() +",y:"+getyRotation()+",z:" + getzRotation() +
                "}";
    }
}
