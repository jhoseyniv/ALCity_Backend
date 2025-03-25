package com.alcity.dto.puzzle.boardgraphic;

import java.io.Serializable;
import java.util.Collection;

public class Row implements Serializable {
    private Collection<Tile> tiles ;

    public Collection<Tile> getTiles() {
        return tiles;
    }

    public void setTiles(Collection<Tile> tiles) {
        this.tiles = tiles;
    }

    public Row() {
    }

    public Row(Collection<Tile> tiles) {
        this.tiles = tiles;
    }
}
