package com.hexgame;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        // Tworzymy okno
        JFrame window = new JFrame("Moja Gra Taktyczna");

        // Tworzymy nasze płótno
        GamePanel gamePanel = new GamePanel();

        // Dodajemy płótno do okna
        window.add(gamePanel);

        // Podstawowe ustawienia okna
        window.setSize(1024, 768); // Szerokość i wysokość
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Zamknij program po kliknięciu X
        window.setLocationRelativeTo(null); // Wyśrodkuj okno na ekranie
        window.setVisible(true); // Pokaż okno
    }
}