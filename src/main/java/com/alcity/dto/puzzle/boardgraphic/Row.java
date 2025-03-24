package com.alcity.dto.puzzle.boardgraphic;

public class Row {
    private Integer id;
    private Integer tileObjId;
    private Integer alpha;
    private Integer zPosition;
    private Margin margin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTileObjId() {
        return tileObjId;
    }

    public void setTileObjId(Integer tileObjId) {
        this.tileObjId = tileObjId;
    }

    public Integer getAlpha() {
        return alpha;
    }

    public void setAlpha(Integer alpha) {
        this.alpha = alpha;
    }

    public Integer getzPosition() {
        return zPosition;
    }

    public void setzPosition(Integer zPosition) {
        this.zPosition = zPosition;
    }

    public Margin getMargin() {
        return margin;
    }

    public void setMargin(Margin margin) {
        this.margin = margin;
    }

    public Row() {
    }

    public Row(Integer id, Integer tileObjId, Integer alpha, Integer zPosition, Margin margin) {
        this.id = id;
        this.tileObjId = tileObjId;
        this.alpha = alpha;
        this.zPosition = zPosition;
        this.margin = margin;
    }
}
