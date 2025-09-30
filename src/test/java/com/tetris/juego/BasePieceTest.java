package com.tetris.juego;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class BasePieceTest {
    @Test
    public void test_Pieza_Square() {
        BasePiece Z = new PieceSquare(0, 0);

        assertTrue(Z.mirandoIzquierda());
        assertTrue(Z.mirandoDerecha());
        assertTrue(Z.mirandoArriba());
        assertTrue(Z.mirandoAbajo());
        Z.rotateRight();
        assertTrue(Z.mirandoIzquierda());
        assertTrue(Z.mirandoDerecha());
        assertTrue(Z.mirandoArriba());
        assertTrue(Z.mirandoAbajo());
        Z.rotateLeft();
        assertTrue(Z.mirandoIzquierda());
        assertTrue(Z.mirandoDerecha());
        assertTrue(Z.mirandoArriba());
        assertTrue(Z.mirandoAbajo());
    }

    @Test
    public void test_Pieza_Stick() {
        for (int rot = 0; rot < 2; rot++) {
            PieceStick S = new PieceStick(rot);

            switch (S.getRotacion()) {
                case 0:
                    assertTrue(S.mirandoIzquierda());
                    assertTrue(S.mirandoDerecha());
                    assertFalse(S.mirandoArriba());
                    assertFalse(S.mirandoAbajo());
                    break;
                case 1:
                    assertFalse(S.mirandoIzquierda());
                    assertFalse(S.mirandoDerecha());
                    assertTrue(S.mirandoArriba());
                    assertTrue(S.mirandoAbajo());
                    break;
            }
        }
    }

    @Test
    public void test_Pieza_T() {
        for (int rot = 0; rot < 4; rot++) { // T tiene 4 rotaciones
            PieceT t = new PieceT(rot, 0);

            assertEquals(rot == 0, t.mirandoArriba());
            assertEquals(rot == 1, t.mirandoDerecha());
            assertEquals(rot == 2, t.mirandoAbajo());
            assertEquals(rot == 3, t.mirandoIzquierda());
        }
    }

    @Test
    public void test_Pieza_Dog(){
        for (int variacion = 0; variacion < 2; variacion++) {
        BasePiece D = new PieceDog(0, variacion);

        for (int i = 0; i < D.getMaxRotaciones(); i++) {
            if (D.getRotacion() == 0) {
                assertTrue(D.mirandoDerecha());
                assertTrue(D.mirandoIzquierda());
                assertFalse(D.mirandoArriba());
                assertFalse(D.mirandoAbajo());
            } else if (D.getRotacion() == 1) {
                assertFalse(D.mirandoDerecha());
                assertFalse(D.mirandoIzquierda());
                assertTrue(D.mirandoArriba());
                assertTrue(D.mirandoAbajo());
            }

            D.rotateRight();
        }

        for (int i = 0; i < D.getMaxRotaciones(); i++) {
            if (D.getRotacion() == 0) {
                assertTrue(D.mirandoDerecha());
                assertTrue(D.mirandoIzquierda());
                assertFalse(D.mirandoArriba());
                assertFalse(D.mirandoAbajo());
            } else if (D.getRotacion() == 1) {
                assertFalse(D.mirandoDerecha());
                assertFalse(D.mirandoIzquierda());
                assertTrue(D.mirandoArriba());
                assertTrue(D.mirandoAbajo());
            }
            D.rotateLeft();
        }
        }
    }

    @Test
    public void test_Pieza_L() {
        for (int variacion = 0; variacion < 2; variacion++) {
            for (int rot = 0; rot < 4; rot++) { 
                PieceL L = new PieceL(rot, variacion);

                assertEquals(rot == 0, L.mirandoArriba());
                assertEquals(rot == 1, L.mirandoDerecha());
                assertEquals(rot == 2, L.mirandoAbajo());
                assertEquals(rot == 3, L.mirandoIzquierda());
            }
        }
    }

    @Test
    public void Test_max_Variaciones(){
        BasePiece D = new PieceDog();
        BasePiece L = new PieceL();
        BasePiece Z = new PieceSquare();
        BasePiece S = new PieceStick();
        BasePiece T = new PieceT();

        assertEquals(1, D.getMaxVariaciones());
        assertEquals(2, L.getMaxVariaciones());
        assertEquals(0, Z.getMaxVariaciones());
        assertEquals(0, S.getMaxVariaciones());
        assertEquals(0, T.getMaxVariaciones());
    }

    @Test
    public void Test_Max_Rotaciones(){
        BasePiece D = new PieceDog();
        BasePiece L = new PieceL();
        BasePiece Z = new PieceSquare();
        BasePiece S = new PieceStick();
        BasePiece T = new PieceT();

        assertEquals(1, D.getMaxRotaciones());
        assertEquals(3, L.getMaxRotaciones());
        assertEquals(0, Z.getMaxRotaciones());
        assertEquals(1, S.getMaxRotaciones());
        assertEquals(3, T.getMaxRotaciones());
    }

    @Test
    public void test_BasePiece_GetMatriz() {
        BasePiece pieza = new BasePiece() {
            @Override
            public String[] getMatriz() {
                return super.getMatriz();
            }

            @Override
            public boolean mirandoArriba() { return false; }

            @Override
            public boolean mirandoAbajo() { return false; }

            @Override
            public boolean mirandoIzquierda() { return false; }

            @Override
            public boolean mirandoDerecha() { return false; }
        };

        assertNull(pieza.getMatriz());
} 
}