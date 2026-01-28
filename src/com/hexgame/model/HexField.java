package com.hexgame.model;

public class HexField {
    private final HexCoord coord;
    private int elevation = 0;

    public HexField(HexCoord coord) {
        this.coord = coord;
    }

    public HexCoord getCoord() { return coord; }
    public int getElevation() { return elevation; }
}