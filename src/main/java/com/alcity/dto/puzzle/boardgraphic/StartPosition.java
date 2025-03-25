package com.alcity.dto.puzzle.boardgraphic;

import java.io.Serializable;

public class StartPosition implements Serializable {
    private Integer x;
    private Integer y;
    private Integer z;

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getZ() {
        return z;
    }

    public void setZ(Integer z) {
        this.z = z;
    }

    public StartPosition() {
    }

    public StartPosition(Integer x, Integer y, Integer z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
