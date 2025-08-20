package com.alcity.dto.plimpexport;

public class PostionIntDTO {

    private Integer X;
    private Integer Y;
    private Integer Z;

    public Integer getX() {
        return X;
    }

    public void setX(Integer x) {
        X = x;
    }

    public Integer getY() {
        return Y;
    }

    public void setY(Integer y) {
        Y = y;
    }

    public Integer getZ() {
        return Z;
    }

    public void setZ(Integer z) {
        Z = z;
    }

    public PostionIntDTO() {
    }

    public PostionIntDTO(Integer x, Integer y, Integer z) {
        X = x;
        Y = y;
        Z = z;
    }
}
