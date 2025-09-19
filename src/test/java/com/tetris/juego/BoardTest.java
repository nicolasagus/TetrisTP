package com.tetris.juego;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

public class BoardTest {
    @Test
    public void test_CaidaLibre(){
        Board board = new Board();
        BasePiece pieceL= new PieceL(0,0);
        
        board.addPieza(pieceL);
        assertEquals(board.getPiezas(0),pieceL);

        boolean colision= false;
        for (int i = 0; i <= 18; i++) {
            if(board.moveDownActivePiece()){
                colision= true;
                break;
            }
        }
        assertEquals(true, colision);
        assertFalse(board.pieceActiveOnBoard());
        assertEquals(1, board.getPiezas().size());
    }

    @Test
    public void test_colision(){
        Board board = new Board ();
        BasePiece PieceDog= new PieceDog(0,0);
        board.addPieza(PieceDog);
        assertEquals (board.getPiezas(0),PieceDog);
        boolean colision= false;
        for (int i = 0; i < 19; i++) {
            if(board.moveDownActivePiece()){
                colision= true;
                break;
            }
        }
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