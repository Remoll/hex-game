package com.hexgame;

import javax.swing.JPanel;
import java.awt.*;
import java.util.HashMap;

public class GamePanel extends JPanel {

    private int radius = 40;

    private HashMap<HexField, HexField> generateHexMap(int width, int height) {
        HashMap<HexField, HexField> newHexMap = new HashMap<>();

        // Promień zasięgu od środka (dla 7x7 jest to 3 w każdą stronę)
        int halfW = width / 2;
        int halfH = height / 2;

        for (int q = -halfW; q <= halfW; q++) {
            // Obliczamy "pływający" offset dla osi R,
            // aby wizualnie rzędy były wyrównane do prostokąta
            int rOffset = (int) Math.floor(q / 2.0);

            for (int vRow = -halfH; vRow <= halfH; vRow++) {
                int r = vRow - rOffset;

                HexField field = new HexField(q, r);
                newHexMap.put(field, field);
            }
        }
        return newHexMap;
    }

    HashMap<HexField, HexField> gameMap = generateHexMap(15,9);

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
        gameMap.forEach((coord, field) -> {
            int offsetX = getWidth() / 2;
            int offsetY = getHeight() / 2;
            double x = radius * 1.5 * field.q;
            double y = radius * Math.sqrt(3) * (field.r + field.q / 2.0);

            // 2. Tworzymy kształt heksagonu
            Polygon hex = createHexagon((int)x + offsetX, (int)y + offsetY, radius);

            // 3. Rysujemy wypełnienie (środek)
            g2d.setColor(new Color(50, 150, 50)); // Zielony
            g2d.fillPolygon(hex);

            // 4. Rysujemy border (obramowanie)
            g2d.setColor(Color.WHITE);
            g2d.setStroke(new BasicStroke(2)); // Grubość linii
            g2d.drawPolygon(hex);

            String hexCords = "q=" + field.q + " r=" + field.r;
            g2d.drawString(hexCords, (int)x + offsetX - 20, (int)y + offsetY);
        });
    }
}