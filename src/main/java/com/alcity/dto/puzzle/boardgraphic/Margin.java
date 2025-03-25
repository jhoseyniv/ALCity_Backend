package com.alcity.dto.puzzle.boardgraphic;

import java.io.Serializable;

public class Margin implements Serializable {
    private Integer top;
    private Integer bottom;
    private Integer left;
    private Integer right;

    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }

    public Integer getBottom() {
        return bottom;
    }

    public void setBottom(Integer bottom) {
        this.bottom = bottom;
    }

    public Integer getLeft() {
        return left;
    }

    public void setLeft(Integer left) {
        this.left = left;
    }

    public Integer getRight() {
        return right;
    }

    public void setRight(Integer right) {
        this.right = right;
    }

    public Margin() {
    }

    public Margin(Integer top, Integer bottom, Integer left, Integer right) {
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
    }
}
