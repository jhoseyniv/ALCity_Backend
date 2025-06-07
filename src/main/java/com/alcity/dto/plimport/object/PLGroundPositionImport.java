package com.alcity.dto.plimport.object;

public class PLGroundPositionImport {
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

    public PLGroundPositionImport(Float x, Float y, Float z) {
        X = x;
        Y = y;
        Z = z;
    }
}
