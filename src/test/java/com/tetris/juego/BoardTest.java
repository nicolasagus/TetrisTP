package com.tetris.juego;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BoardTest {
    @Test
    public void test_CaidaLibre(){
        Board board = new Board();
        BasePiece pieceL= new PieceL(0,0);

        board.addPiece(pieceL);
        assertEquals(board.getPieces(0),pieceL);

        boolean colision= false;
        for (int i = 0; i < 19; i++) {
            if(board.moveDownActivePiece()){
                colision= true;
                break;
            }
        }
        assertEquals(true, colision);
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