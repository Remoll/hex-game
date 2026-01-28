package com.hexgame.view;

import com.hexgame.model.WorldMap;

public class Camera {
    private double x = 0;
    private double y = 0;

    public void update(int mouseX, int mouseY, int screenW, int screenH, WorldMap map) {
        if (mouseX < 0 || mouseY < 0) return;

        int speed = 25;
        int edgeThreshold = 100;
        if (mouseX < edgeThreshold) x += speed;
        if (mouseX > screenW - edgeThreshold) x -= speed;
        if (mouseY < edgeThreshold) y += speed;
        if (mouseY > screenH - edgeThreshold) y -= speed;

        clamp(map);
    }

    private void clamp(WorldMap map) {
        if (x > -map.getMinPixelX()) x = -map.getMinPixelX();
        if (y > -map.getMinPixelY()) y = -map.getMinPixelY();
        if (x < -map.getMaxPixelX()) x = -map.getMaxPixelX();
        if (y < -map.getMaxPixelY()) y = -map.getMaxPixelY();
    }

    public int getX() { return (int) x; }
    public int getY() { return (int) y; }
}