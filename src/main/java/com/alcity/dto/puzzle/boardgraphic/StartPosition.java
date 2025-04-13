package com.alcity.dto.puzzle.boardgraphic;

import java.io.Serializable;

public class StartPosition implements Serializable {
    private Float x;
    private Float y;
    private Float z;

    public Float getX() {
        return x;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public Float getZ() {
        return z;
    }

    public void setZ(Float z) {
        this.z = z;
    }

    public StartPosition() {
    }

    public StartPosition(Float x, Float y, Float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
