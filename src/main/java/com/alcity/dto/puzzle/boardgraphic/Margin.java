package com.alcity.dto.puzzle.boardgraphic;

import java.io.Serializable;

public class Margin implements Serializable {
    private Float top;
    private Float bottom;
    private Float left;
    private Float right;

    public Float getTop() {
        return top;
    }

    public void setTop(Float top) {
        this.top = top;
    }

    public Float getBottom() {
        return bottom;
    }

    public void setBottom(Float bottom) {
        this.bottom = bottom;
    }

    public Float getLeft() {
        return left;
    }

    public void setLeft(Float left) {
        this.left = left;
    }

    public Float getRight() {
        return right;
    }

    public void setRight(Float right) {
        this.right = right;
    }

    public Margin() {
    }

    public Margin(Float top, Float bottom, Float left, Float right) {
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
    }
}
