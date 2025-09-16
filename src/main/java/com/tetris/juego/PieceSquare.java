package com.tetris.juego;

public class PieceSquare extends BasePiece {
    private static final String[] matrizCuadrado = {
        "    ",
        "    ",
        "ZZ  ",
        "ZZ  "
    };

    public PieceSquare() {
        super();
        Math.random();
        setRotacion((int)(Math.random() * this.getMaxRotaciones()));
    }

    public PieceSquare(int rotacion, int variacion) {
        super();
    }

    @Override
    public String[] getMatriz(){
        return matrizCuadrado;
    }
    
    @Override
    public boolean mirandoIzquierda() {
        return true;
    }

    @Override
    public boolean mirandoDerecha() {
        return true;
    }

    @Override
    public boolean mirandoAbajo() {
        return true;
    }

    @Override
    public boolean mirandoArriba() {
        return true;
    }
}
