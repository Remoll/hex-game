package com.hexgame;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel {

    private int radius = 40; // Rozmiar heksagonu

    private HexField[] gameMap = {
            new HexField(0,0),
            new HexField(0,1),
            new HexField(0,-1),
            new HexField(0,2),
            new HexField(0,-2),
            new HexField(0,3),
            new HexField(0,-3),
            new HexField(0,4),
            new HexField(0,-4),

            new HexField(1,0),
            new HexField(1,1),
            new HexField(1,-1),
            new HexField(1,2),
            new HexField(1,-2),
            new HexField(1,3),
            new HexField(1,-3),
            new HexField(1,4),
            new HexField(1,-4),

            new HexField(-1,0),
            new HexField(-1,1),
            new HexField(-1,-1),
            new HexField(-1,2),
            new HexField(-1,-2),
            new HexField(-1,3),
            new HexField(-1,-3),
            new HexField(-1,4),
            new HexField(-1,-4),

            new HexField(2,0),
            new HexField(2,1),
            new HexField(2,-1),
            new HexField(2,2),
            new HexField(2,-2),
            new HexField(2,3),
            new HexField(2,-3),
            new HexField(2,4),
            new HexField(2,-4),

            new HexField(-2,0),
            new HexField(-2,1),
            new HexField(-2,-1),
            new HexField(-2,2),
            new HexField(-2,-2),
            new HexField(-2,3),
            new HexField(-2,-3),
            new HexField(-2,4),
            new HexField(-2,-4),

            new HexField(3,0),
            new HexField(3,1),
            new HexField(3,-1),
            new HexField(3,2),
            new HexField(3,-2),
            new HexField(3,3),
            new HexField(3,-3),
            new HexField(3,4),
            new HexField(3,-4),

            new HexField(-3,0),
            new HexField(-3,1),
            new HexField(-3,-1),
            new HexField(-3,2),
            new HexField(-3,-2),
            new HexField(-3,3),
            new HexField(-3,-3),
            new HexField(-3,4),
            new HexField(-3,-4),



            new HexField(6,-3),
            new HexField(7,-3),


    };

    public GamePanel() {
        // Ustawiamy kolor tła panelu
        setBackground(Color.DARK_GRAY);
    }

    // Pomocnicza metoda tworząca Polygon (6 wierzchołków)
    private Polygon createHexagon(int centerX, int centerY, int radius) {
        Polygon p = new Polygon();
        for (int i = 0; i < 6; i++) {
            // Dla Flat-topped kąty to: 0, 60, 120, 180, 240, 300 stopni
            double angleDeg = 60 * i;
            double angleRad = Math.toRadians(angleDeg);
            int x = (int) (centerX + radius * Math.cos(angleRad));
            int y = (int) (centerY + radius * Math.sin(angleRad));
            p.addPoint(x, y);
        }
        return p;
    }

    // Ta metoda jest wywoływana przez system zawsze, gdy okno wymaga odświeżenia
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Czyścimy tło

        // Konwertujemy Graphics na Graphics2D (daje więcej możliwości)
        Graphics2D g2d = (Graphics2D) g;

        // Włączamy antyaliasing, żeby krawędzie heksów były gładkie (nie poszarpane)
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // TUTAJ będziesz wpisywać logikę rysowania heksów

        for (HexField hexField : gameMap) {
            int offsetX = getWidth() / 2;
            int offsetY = getHeight() / 2;
            double x = radius * 1.5 * hexField.q;
            double y = radius * Math.sqrt(3) * (hexField.r + hexField.q / 2.0);

            // 2. Tworzymy kształt heksagonu
            Polygon hex = createHexagon((int)x + offsetX, (int)y + offsetY, radius);

            // 3. Rysujemy wypełnienie (środek)
            g2d.setColor(new Color(50, 150, 50)); // Zielony
            g2d.fillPolygon(hex);

            // 4. Rysujemy border (obramowanie)
            g2d.setColor(Color.WHITE);
            g2d.setStroke(new BasicStroke(2)); // Grubość linii
            g2d.drawPolygon(hex);
        }
    }
}