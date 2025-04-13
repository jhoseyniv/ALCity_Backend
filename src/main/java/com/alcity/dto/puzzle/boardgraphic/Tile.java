package com.alcity.dto.puzzle.boardgraphic;

import java.io.Serializable;

public class Tile implements Serializable {
    private Integer id;
    private String tileObjId;
    private Float alpha;
    private Float zPosition;
    private Margin margin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public Float getAlpha() {
        return alpha;
    }

    public void setAlpha(Float alpha) {
        this.alpha = alpha;
    }

    public Float getzPosition() {
        return zPosition;
    }

    public void setzPosition(Float zPosition) {
        this.zPosition = zPosition;
    }

    public String getTileObjId() {
        return tileObjId;
    }

    public void setTileObjId(String tileObjId) {
        this.tileObjId = tileObjId;
    }

        public Margin getMargin() {
        return margin;
    }

    public void setMargin(Margin margin) {
        this.margin = margin;
    }

    public Tile() {
    }

    public Tile(Integer id, String tileObjId, Float alpha, Float zPosition) {
        this.id = id;
        this.tileObjId = tileObjId;
        this.alpha = alpha;
        this.zPosition = zPosition;
        this.margin = margin;
    }


}
