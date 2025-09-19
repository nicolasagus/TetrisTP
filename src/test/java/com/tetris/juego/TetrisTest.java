package com.tetris.juego;

import static org.junit.Assert.*;

import org.junit.Test;

public class TetrisTest {
    @Test
    public void testTickBajaCada2TicksConTickEnStart() {
        Tetris tetris = new Tetris(5);
        tetris.start(); 

        PieceSquare square = new PieceSquare();
        tetris.board.addPieza(square);

        int[] locInicial = tetris.board.getActivePieceLocation();
        int yInicial = locInicial[1];

        tetris.tick();
        int[] locDespuesTick1 = tetris.board.getActivePieceLocation();
        assertTrue(locDespuesTick1[1] > yInicial);

        int yDespuesTick1 = locDespuesTick1[1];

        // Segundo tick -> debería quedarse en la misma posición
        tetris.tick();
        int[] locDespuesTick2 = tetris.board.getActivePieceLocation();
        assertEquals(yDespuesTick1, locDespuesTick2[1]);
    }
}