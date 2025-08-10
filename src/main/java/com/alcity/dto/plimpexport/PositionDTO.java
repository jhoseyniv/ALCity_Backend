package com.alcity.dto.plimpexport;

import java.io.Serializable;

public class PositionDTO implements Serializable {

    private static final long serialVersionUID = 5475773041002282988L;
    private Float X;
    private Float Y;
    private Float Z;

    public Float getX() {
        return X;
    }

    public void setX(Float x) {
        X = x;
    }

    public Float getY() {
        return Y;
    }

    public void setY(Float y) {
        Y = y;
    }

    public Float getZ() {
        return Z;
    }

    public void setZ(Float z) {
        Z = z;
    }

    public PositionDTO() {
    }

    public PositionDTO(Float x, Float y, Float z) {
        X = x;
        Y = y;
        Z = z;
    }
}
