package com.hexgame;

public class HexField {
    public final int q, r, s;

    public HexField(int q, int r) {
        this.q = q;
        this.r = r;
        this.s = -q - r;
    }
}