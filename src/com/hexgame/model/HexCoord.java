package com.hexgame.model;

import java.util.Objects;

public class HexCoord {
    public final int q, r, s;

    public HexCoord(int q, int r) {
        this.q = q;
        this.r = r;
        this.s = -q - r;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HexCoord hexCoord = (HexCoord) o;
        return q == hexCoord.q && r == hexCoord.r;
    }

    @Override
    public int hashCode() {
        return Objects.hash(q, r);
    }

    @Override
    public String toString() { return "Hex(" + q + "," + r + ")"; }
}