package com.hexgame.view;

import com.hexgame.model.HexCoord;
import com.hexgame.model.WorldMap;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GamePanel extends JPanel {
    private final WorldMap worldMap;
    private final Camera camera;
    private final int hexRadius;

    private int mouseX = -1;
    private int mouseY = -1;

    public GamePanel(WorldMap worldMap, int hexRadius) {
        this.worldMap = worldMap;
        this.camera = new Camera();
        this.hexRadius = hexRadius;
        setBackground(Color.DARK_GRAY);

        // Obsługa myszy
        MouseAdapter ma = new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        };
        addMouseListener(ma);
        addMouseMotionListener(ma);

        // Pętla gry (Game Loop) - 60 FPS
        Timer timer = new Timer(16, e -> {
            camera.update(mouseX, mouseY, getWidth(), getHeight(), worldMap);
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Pobieramy offset z kamery
        int camX = camera.getX();
        int camY = camera.getY();

        // Środek ekranu jako punkt startowy + przesunięcie kamery
        int centerX = getWidth() / 2 + camX;
        int centerY = getHeight() / 2 + camY;

        // Rysujemy tylko to co jest na mapie
        worldMap.getFields().forEach((coord, field) -> {
            double x = hexRadius * 1.5 * coord.q;
            double y = hexRadius * Math.sqrt(3) * (coord.r + coord.q / 2.0);

            int drawX = (int)x + centerX;
            int drawY = (int)y + centerY;

            if (drawX < -hexRadius || drawX > getWidth() + hexRadius || drawY < -hexRadius || drawY > getHeight() + hexRadius) {
                return;
            }

            drawHex(g2d, drawX, drawY, coord);
        });

        // Debug info
        g2d.setColor(Color.YELLOW);
        g2d.drawString("Camera: " + camX + ", " + camY, 20, 20);
    }

    private void drawHex(Graphics2D g2d, int x, int y, HexCoord coord) {
        Polygon hex = createHexagon(x, y);
        g2d.setColor(new Color(50, 150, 50));
        g2d.fillPolygon(hex);
        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawPolygon(hex);
        g2d.drawString(coord.q + "," + coord.r, x - 15, y);
    }

    private Polygon createHexagon(int centerX, int centerY) {
        Polygon p = new Polygon();
        for (int i = 0; i < 6; i++) {
            double angleRad = Math.toRadians(60 * i);
            int x = (int) (centerX + hexRadius * Math.cos(angleRad));
            int y = (int) (centerY + hexRadius * Math.sin(angleRad));
            p.addPoint(x, y);
        }
        return p;
    }
}