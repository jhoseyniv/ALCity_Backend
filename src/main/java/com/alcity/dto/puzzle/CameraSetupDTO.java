package com.alcity.dto.puzzle;

import java.io.Serializable;

public class CameraSetupDTO implements Serializable {
    private Long id;
   // private Long version;
//    private String created;
//    private String updated;
//    private String createdBy;
//
//    private String updatedBy;


    private String title;
    private Integer XPosition;
    private Integer YPosition;
    private Integer ZPosition;

    private Integer XRotation;
    private Integer YRotation;
    private Integer ZRotation;

    public CameraSetupDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Long getVersion() {
//        return version;
//    }
//
//    public void setVersion(Long version) {
//        this.version = version;
//    }

    public Integer getXPosition() {
        return XPosition;
    }

    public void setXPosition(Integer XPosition) {
        this.XPosition = XPosition;
    }

    public Integer getYPosition() {
        return YPosition;
    }

    public void setYPosition(Integer YPosition) {
        this.YPosition = YPosition;
    }

    public Integer getZPosition() {
        return ZPosition;
    }

    public void setZPosition(Integer ZPosition) {
        this.ZPosition = ZPosition;
    }

    public Integer getXRotation() {
        return XRotation;
    }

    public void setXRotation(Integer XRotation) {
        this.XRotation = XRotation;
    }

    public Integer getYRotation() {
        return YRotation;
    }

    public void setYRotation(Integer YRotation) {
        this.YRotation = YRotation;
    }

    public Integer getZRotation() {
        return ZRotation;
    }

    public void setZRotation(Integer ZRotation) {
        this.ZRotation = ZRotation;
    }


    public CameraSetupDTO(Long id, String title, Integer XPosition, Integer YPosition, Integer ZPosition,
                          Integer XRotation, Integer YRotation, Integer ZRotation) {
        this.id = id;
        this.title = title;
        this.XPosition = XPosition;
        this.YPosition = YPosition;
        this.ZPosition = ZPosition;
        this.XRotation = XRotation;
        this.YRotation = YRotation;
        this.ZRotation = ZRotation;
    }
//    public String getCameraSetupInfottttttt() {
//
//        return "position:{" +
//                "x:" + getxPosition() +
//                ",y:" + getyPosition() +
//                ",z:" + getzPosition() +
//                "}," + System.lineSeparator() +
//                "rotation:{" + System.lineSeparator() +
//                "x:" + getxRotation() +
//                ",y:" + getyRotation() +
//                ",z:" + getzRotation() +
//                "}";
//    }

}