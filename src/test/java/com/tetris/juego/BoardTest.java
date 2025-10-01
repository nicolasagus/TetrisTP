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
    @Test
    public void test_colision(){
        Board board = new Board ();
        BasePiece PieceDog= new PieceDog(0,0);
        board.addPiece(PieceDog);
        assertEquals (board.getPieces(0),PieceDog);
        boolean colision= false;
        for (int i = 0; i < 19; i++) {
            if(board.moveDownActivePiece()){
                colision= true;
                break;  }    
    assertEquals(true, colision);
    }
    }
      @Test
    public void noDebeIngresarMasPiezasQueElMaximo() {
        Board board = new Board();
        BasePiece pieceL = new PieceL(0,0);
        board.addPiece(pieceL);
        
        BasePiece otraPieza = new PieceL(0,0);
        board.addPiece(otraPieza);
        
        assertEquals(1, board.getPieces().size());
    }
public boolean moveDownActivePiece() {
    Board activePiece = moveDownActivePiece();
    int boardHeight = getHeight();
    BasePiece[][] grid = getGrid();
    int boardWidth = getWidth();

    if (activePiece==null) return false;

    int newRow = activePiece.getRow() + 1;

    // Reviso todas las coordenadas que ocupa la pieza
    for (Point cell : activePiece.getCells()) {
        int futureRow = cell.y + 1;
        int futureCol = cell.x;

        // Si toca el fondo
        if (futureRow >= boardHeight) {
            return false;
        }

        // Si choca con otra pieza ya fijada
        if (grid[futureRow][futureCol] != null) {
            return false;
        }
    }

    // Si no choca, actualizo posición
    activePiece.moveDown();
    return true;
}
private int getHeight() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getHeight'");
}
private BasePiece[][] getGrid() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getGrid'");
}

    @Test
public void testCompletarLinea() {
    Board board = new Board();
    
    // Llenar la fila 19
    for (int col = 0; col < board.getWidth(); col++) {
        BasePiece piece = new PieceSquare(col, 19);
        board.addPiece(piece);
        piece.colision(); // fijar la pieza
    }

    int filasAntes = board.getFilledRowsCount();
    board.checkAndClearFullLines();
    int filasDespues = board.getFilledRowsCount();

    assertEquals(filasAntes - 1, filasDespues);
}

 
@Test
public void testcompletarLinea() {
    Board board = new Board();
    // Llenar la fila 19
    for (int col = 0; col < board.getWidth(); col++) {
        BasePiece piece = new PieceSquare(col, 19);
        board.addPiece(piece);
        piece.colision(); // Fijar la pieza
    }

    int filasAntes = board.getFilledRowsCount();
    board.checkAndClearFullLines();
    int filasDespues = board.getFilledRowsCount();

    assertEquals(filasAntes - 1, filasDespues);

}
}