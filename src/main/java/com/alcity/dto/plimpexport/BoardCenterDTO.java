package com.alcity.dto.plimpexport;

import java.io.Serializable;

public class BoardCenterDTO implements Serializable {
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

    public BoardCenterDTO() {
    }
    public BoardCenterDTO(Integer X, Integer Y, Integer Z) {
        this.X = X;
        this.Y = Y;
        this.Z = Z;
    }
}
