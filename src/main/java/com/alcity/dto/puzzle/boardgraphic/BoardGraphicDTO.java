package com.alcity.dto.puzzle.boardgraphic;

import java.io.Serializable;
import java.util.Collection;

public class BoardGraphicDTO implements Serializable {
    private Setting settings;
    private Collection<Row> rows;

    public Setting getSettings() {
        return settings;
    }

    public void setSettings(Setting settings) {
        this.settings = settings;
    }

    public Collection<Row> getRows() {
        return rows;
    }

    public void setRows(Collection<Row> rows) {
        this.rows = rows;
    }

    public BoardGraphicDTO(Setting settings, Collection<Row> rows) {
        this.settings = settings;
        this.rows = rows;
    }

    public BoardGraphicDTO() {
    }
}
