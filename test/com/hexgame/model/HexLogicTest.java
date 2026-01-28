package com.hexgame.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HexLogicTest {

    @Test
    void testHexCoordEquality() {
        // Sprawdzamy czy HashMap będzie działać poprawnie
        HexCoord h1 = new HexCoord(1, 2);
        HexCoord h2 = new HexCoord(1, 2);
        HexCoord h3 = new HexCoord(2, 2);

        assertEquals(h1, h2, "Obiekty o tych samych q,r muszą być równe");
        assertNotEquals(h1, h3);
        assertEquals(h1.hashCode(), h2.hashCode(), "HashCode musi być taki sam");
    }

    @Test
    void testHexSCoordinate() {
        // Sprawdzamy niezmiennik q + r + s = 0
        HexCoord h = new HexCoord(2, -5);
        assertEquals(3, h.s, "Współrzędna S musi być obliczona automatycznie (-q-r)");
        assertEquals(0, h.q + h.r + h.s);
    }

    // Tu można dodać testy logiki kamery, jeśli wyciągniesz logikę clamping do metody publicznej
}