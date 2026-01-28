package com.hexgame.model;

import java.util.HashMap;
import java.util.Map;

public class WorldMap {
    private final Map<HexCoord, HexField> fields = new HashMap<>();

    private int minPixelX, maxPixelX, minPixelY, maxPixelY;
    private final int hexRadius;

    public WorldMap(int widthInHexes, int heightInHexes, int hexRadius) {
        this.hexRadius = hexRadius;
        generateFields(widthInHexes, heightInHexes);
        calculatePixelBounds();
    }

    private void generateFields(int width, int height) {
        int halfW = width / 2;
        int halfH = height / 2;

        for (int q = -halfW; q <= halfW; q++) {
            int rOffset = (int) Math.floor(q / 2.0);
            for (int vRow = -halfH; vRow <= halfH; vRow++) {
                int r = vRow - rOffset;
                HexCoord coord = new HexCoord(q, r);
                fields.put(coord, new HexField(coord));
            }
        }
    }

    private void calculatePixelBounds() {
        minPixelX = Integer.MAX_VALUE;
        maxPixelX = Integer.MIN_VALUE;
        minPixelY = Integer.MAX_VALUE;
        maxPixelY = Integer.MIN_VALUE;

        for (HexCoord coord : fields.keySet()) {
            int x = (int)(hexRadius * 1.5 * coord.q);
            int y = (int)(hexRadius * Math.sqrt(3) * (coord.r + coord.q / 2.0));

            if(x < minPixelX) minPixelX = x;
            if(x > maxPixelX) maxPixelX = x;
            if(y < minPixelY) minPixelY = y;
            if(y > maxPixelY) maxPixelY = y;
        }

        minPixelX -= hexRadius;
        maxPixelX += hexRadius;
        minPixelY -= hexRadius;
        maxPixelY += hexRadius;
    }

    public Map<HexCoord, HexField> getFields() { return fields; }
    public int getMinPixelX() { return minPixelX; }
    public int getMaxPixelX() { return maxPixelX; }
    public int getMinPixelY() { return minPixelY; }
    public int getMaxPixelY() { return maxPixelY; }
}