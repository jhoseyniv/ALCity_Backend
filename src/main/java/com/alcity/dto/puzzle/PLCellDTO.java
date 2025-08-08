package com.alcity.dto.puzzle;

import java.io.Serializable;

public class PLCellDTO  implements Serializable {
    private Long id;
    private Integer row;
    private Integer col;
    private Integer zorder;
    private Long plGroundId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getCol() {
        return col;
    }

    public void setCol(Integer col) {
        this.col = col;
    }

    public Integer getZorder() {
        return zorder;
    }

    public void setZorder(Integer zorder) {
        this.zorder = zorder;
    }

    public Long getPlGroundId() {
        return plGroundId;
    }

    public void setPlGroundId(Long plGroundId) {
        this.plGroundId = plGroundId;
    }

    public PLCellDTO() {
    }

    public PLCellDTO(Long id, Integer row, Integer col, Integer zorder,Long plGroundId) {
        this.id = id;
        this.row = row;
        this.col = col;
        this.zorder = zorder;
        this.plGroundId = plGroundId;
    }
}
