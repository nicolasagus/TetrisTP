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
                break;
            }
            
    assertEquals(true, colision);
    }
    }
      @Test
    public void noDebeIngresarMasPiezasQueElMaximo() {
        Board board = new Board();
        BasePiece pieceL = new PieceL(0,0);
        board.addPiece(pieceL);
        // Intentar agregar otra pieza mientras hay una activa
        BasePiece otraPieza = new PieceL(0,0);
        board.addPiece(otraPieza);
        // Solo debe haber una pieza en el tablero
        assertEquals(1, board.getPieces().size());
    }


}