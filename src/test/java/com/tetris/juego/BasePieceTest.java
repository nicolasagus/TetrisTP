package com.tetris.juego;

import static org.junit.Assert.*;
import org.junit.Test;

public class BasePieceTest {
    @Test
    public void test_Pieza_Square() {
        BasePiece z = new PieceSquare();

        assertNotNull(z.getMatriz());
        assertSame(z.getMatriz(), z.getMatriz());

        assertEquals(0, z.getMaxRotaciones());

        assertTrue(z.mirandoIzquierda());
        assertTrue(z.mirandoDerecha());
        assertTrue(z.mirandoArriba());
        assertTrue(z.mirandoAbajo());
    }

    @Test
    public void test_Pieza_Stick() {
        for (int rot = 0; rot < 2; rot++) {
            BasePiece S = new PieceStick(rot);

            assertEquals(2, S.getMaxRotaciones());

            assertTrue(S.getRotacion() >= 0 && S.getRotacion() < S.getMaxRotaciones());

            switch(S.getRotacion()) {
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
    public void test_Pieza_T(){
        BasePiece t = new PieceT(0,0);
        
        assertNotNull(t.getMatriz());
        assertSame(t.getMatriz(), t.getMatriz());

        assertEquals(3, t.getMaxRotaciones());

        assertTrue(t.getRotacion() >= 0 && t.getRotacion() < t.getMaxRotaciones());

        switch(t.getRotacion()) {
            case 0:
                assertTrue(t.mirandoArriba());
                assertFalse(t.mirandoDerecha());
                assertFalse(t.mirandoAbajo());
                assertFalse(t.mirandoIzquierda());
                break;
            case 1:
                assertFalse(t.mirandoArriba());
                assertTrue(t.mirandoDerecha());
                assertFalse(t.mirandoAbajo());
                assertFalse(t.mirandoIzquierda());
                break;
            case 2:
                assertFalse(t.mirandoArriba());
                assertFalse(t.mirandoDerecha());
                assertTrue(t.mirandoAbajo());
                assertFalse(t.mirandoIzquierda());
                break;
            case 3:
                assertFalse(t.mirandoArriba());
                assertFalse(t.mirandoDerecha());
                assertFalse(t.mirandoAbajo());
                assertTrue(t.mirandoIzquierda());
                break;
        }
    }

    @Test
    public void test_Pieza_Dog(){
        BasePiece D;
        for(int variacion = 0; variacion < 2; variacion++){
            D = new PieceDog(0, variacion);

            for(int i = 0; i < D.getMaxRotaciones(); i++){
                assertTrue(D.mirandoArriba() || D.mirandoAbajo() || D.mirandoIzquierda() || D.mirandoDerecha());
                D.rotarDerecha();
            }

            for(int i = 0; i < D.getMaxRotaciones(); i++){
                assertTrue(D.mirandoArriba() || D.mirandoAbajo() || D.mirandoIzquierda() || D.mirandoDerecha());
                D.rotarIzquierda();
            }
        }
    }

    @Test
    public void test_Pieza_L() {
        BasePiece L;
        for (int variacion = 0; variacion < 2; variacion++) {
            L = new PieceL(0, variacion);

            assertEquals(variacion, L.getVariacion());

            for (int i = 0; i < L.getMaxRotaciones(); i++) {
                assertTrue(L.mirandoArriba() || L.mirandoDerecha() || L.mirandoAbajo() || L.mirandoIzquierda());
                L.rotarDerecha();
            }

            for (int i = 0; i < L.getMaxRotaciones(); i++) {
                assertTrue(L.mirandoArriba() || L.mirandoDerecha() || L.mirandoAbajo() || L.mirandoIzquierda());
                L.rotarIzquierda();
            }
        }
    }

    @Test
    public void Test_max_Variaciones(){
        BasePiece d = new PieceDog();
        BasePiece l = new PieceL();
        BasePiece z = new PieceSquare();
        BasePiece s = new PieceStick();
        BasePiece t = new PieceT();

        assertEquals(1, d.getMaxVariaciones());
        assertEquals(2, l.getMaxVariaciones());
        assertEquals(0, z.getMaxVariaciones());
        assertEquals(0, s.getMaxVariaciones());
        assertEquals(0, t.getMaxVariaciones());
    }

    @Test
    public void test_colision(){
        BasePiece L = new PieceL();

        assertFalse(L.getHaColisionado());
        L.colision();
        assertTrue(L.getHaColisionado());
    }
}
