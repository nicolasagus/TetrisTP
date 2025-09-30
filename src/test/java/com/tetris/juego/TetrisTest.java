package com.tetris.juego;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class TetrisTest {
    @Test
    public void testCaidaLibreCuadradoConTick() {
        Tetris tetris = new Tetris(5);
        tetris.setGameStarted(true);
        tetris.setAutoSpawn(false);

        PieceSquare square = new PieceSquare();
        tetris.board.addPieza(square, 0);

        assertEquals("xx00000000", tetris.board.getMatriz(0));
        assertEquals("xx00000000", tetris.board.getMatriz(1));

        int[] locInicial = tetris.board.getActivePieceLocation();
        assertTrue(locInicial[1] <= 1);

        while (tetris.board.pieceActiveOnBoard()) {
            tetris.tick();
        }

        assertEquals("XX00000000", tetris.board.getMatriz(18));
        assertEquals("XX00000000", tetris.board.getMatriz(19));

        assertFalse(tetris.board.pieceActiveOnBoard());
        assertEquals(1, tetris.board.getPiezas().size());
    }

    @Test
    public void test_TickJuegoIniciado() {
        Tetris tetris = new Tetris(5);

        assertEquals("juego no iniciado",tetris.state());
        assertEquals(0, tetris.clock.getTicks());
        tetris.start();
        assertEquals(1, tetris.clock.getTicks());

        assertTrue(tetris.getGameStarted());
    }

    @Test
    public void stateTest(){
        Tetris tetris = new Tetris(4);

        assertEquals("juego no iniciado",tetris.state());
        
        tetris.start();
        assertEquals("juego en curso",tetris.state());
    }

    @Test
    public void testTickGanaJuego() {
        Tetris tetris = new Tetris(1); 
        tetris.start();

        tetris.board.setLineCount(1);

        tetris.tick();
        assertTrue(tetris.getGameWinned());
    }

    @Test
    public void testTickPierdeJuego() {
        Tetris tetris = new Tetris(5); 
        tetris.start();

        for (int i = 0; i < 10; i++) {
            tetris.board.addPieza(new PieceSquare(), 0);
            while(!tetris.board.moveDownActivePiece()){}
        }

        tetris.tick();

        assertTrue(tetris.getGameLost());
    }

    @Test
    public void rotateRightTest(){
        Tetris tetris = new Tetris(4);

        tetris.start();

        for (int i = 0; i < 4; i++) {
            tetris.tick();
        }

        tetris.rotateRight();
    }

    @Test
    public void rotateLeftTest(){
        Tetris tetris = new Tetris(4);

        tetris.start();

        for (int i = 0; i < 4; i++) {
            tetris.tick();
        }

        tetris.rotateLeft();
    }

    @Test
    public void testGetTimeMilliseconds() throws InterruptedException {
        Clock clock = new Clock();
        Thread.sleep(100);
        long tiempo = clock.getTimeMiliSeconds();
        assertTrue(tiempo <= 0);
        assertTrue(tiempo >= -200);
    }
}