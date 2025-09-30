package com.tetris.juego;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class BoardTest {
    @Test
    public void test_CaidaLibreConHaColisionado() {
        Board board = new Board();
        BasePiece pieceL = new PieceL(0, 0);

        board.addPieza(pieceL, 0);
        assertEquals(board.getPiezas(0), pieceL);

        assertEquals("x000000000", board.getMatriz(0));
        assertEquals("x000000000", board.getMatriz(1));
        assertEquals("xx00000000", board.getMatriz(2));

        for (int i = 0; i <= 18 && !board.piezaActivaFija(); i++) {
            board.moveDownActivePiece();
        }

        assertTrue(board.piezaActivaFija());
        assertFalse(board.pieceActiveOnBoard());
        assertEquals(1, board.getPiezas().size());

        assertEquals("X000000000", board.getMatriz(17));
        assertEquals("X000000000", board.getMatriz(18));
        assertEquals("XX00000000", board.getMatriz(19));
    }

    @Test
    public void test_colision2Sticks() {
        Board board = new Board();
        BasePiece stick1 = new PieceStick(1);
        board.addPieza(stick1, 0);
        assertEquals(board.getPiezas(0), stick1);

        while (!board.moveDownActivePiece()) {}
        assertTrue(board.piezaActivaFija());

        String fila19 = board.getMatriz(19);
        String fila18 = board.getMatriz(18);
        String fila17 = board.getMatriz(17);
        String fila16 = board.getMatriz(16);
        assertTrue(fila19.contains("X"));
        assertTrue(fila18.contains("X"));
        assertTrue(fila17.contains("X"));
        assertTrue(fila16.contains("X"));

        BasePiece stick2 = new PieceStick(1);
        board.addPieza(stick2, 0);
        assertEquals(board.getPiezas(1), stick2);

        while (!board.moveDownActivePiece()) {}
        assertTrue(board.piezaActivaFija());

        String fila15 = board.getMatriz(15);
        String fila14 = board.getMatriz(14);
        String fila13 = board.getMatriz(13);
        String fila12 = board.getMatriz(12);

        assertTrue(fila15.contains("X"));
        assertTrue(fila14.contains("X"));
        assertTrue(fila13.contains("X"));
        assertTrue(fila12.contains("X"));

        assertEquals("0000000000", board.getMatriz(11));
        
        assertEquals(2, board.getPiezas().size());
        assertFalse(board.pieceActiveOnBoard());
    }

    @Test
    public void test_CompletarLinea() {
        Board board = new Board();

        BasePiece stick = new PieceStick();
        assertEquals(0, board.getLineCount());
        board.addPieza(stick, 0);

        while (!board.moveDownActivePiece()) {}

        assertTrue(board.piezaActivaFija());
        assertEquals("XXXX000000", board.getMatriz(19));

        BasePiece stick2 = new PieceStick();
        board.addPieza(stick2, 4);

        while (!board.moveDownActivePiece()) {}

        assertTrue(board.piezaActivaFija());
        assertEquals("XXXXXXXX00", board.getMatriz(19));

        BasePiece square = new PieceSquare();
        board.addPieza(square, 8);

        while (!board.moveDownActivePiece()) {}

        assertTrue(board.piezaActivaFija());
        assertEquals("0000000000", board.getMatriz(18));
        assertEquals("00000000XX", board.getMatriz(19));

        assertEquals(1, board.getLineCount());
    }

    @Test
    public void noDebeIngresarMasPiezasQueElMaximo() {
        Board board = new Board();
        BasePiece pieceL = new PieceL(0,0);
        board.addPieza(pieceL);
        BasePiece otraPieza = new PieceL(0,0);
        board.addPieza(otraPieza);
        assertEquals(1, board.getPiezas().size());
    }
}