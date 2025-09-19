package com.tetris.juego;

import static org.junit.Assert.*;

import org.junit.Test;

public class TetrisTest {
    @Test
    public void testSquareDesdeArribaHastaElFondo() {
        Tetris tetris = new Tetris(5);
        tetris.start();

        PieceSquare square = new PieceSquare();
        tetris.board.addPieza(square);

        int[] locInicial = tetris.board.getActivePieceLocation();
        assertTrue(locInicial[1] <= 1);

        while (tetris.board.pieceActiveOnBoard()) {
            tetris.tick();
        }

        String fila19 = tetris.board.getMatriz(19);
        String fila18 = tetris.board.getMatriz(18);

        assertTrue(fila19.contains("X"));
        assertTrue(fila18.contains("X"));

        assertFalse(tetris.board.pieceActiveOnBoard());
    }
}