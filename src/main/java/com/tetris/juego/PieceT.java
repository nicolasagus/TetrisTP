package com.tetris.juego;

public class PieceT extends BasePiece{
    private static final String[][] todasLasRotaciones = {
        {
            "    ", 
            "    ", 
            "TTT ", 
            " T  "
        },
        {
            "    ", 
            " T  ", 
            "TT  ", 
            " T  "
        },
        {
            "    ", 
            "    ", 
            " T  ", 
            "TTT "
        },
        {
            "    ", 
            "T   ", 
            "TT  ", 
            "T   "
        }
    };

    public PieceT() {
        super();
        Math.random();
        setMaxRotaciones(4);
        setRotacion((int)(Math.random() * this.getMaxRotaciones()));
    }

    public PieceT(int rotacion, int variacion) {
        super();
        setMaxRotaciones(4);
        setRotacion(rotacion);
    }

    @Override
    public String[] getMatriz(){
        return todasLasRotaciones[getRotacion()];
    }

    @Override
    public boolean mirandoArriba() { return getRotacion() == 0; }

    @Override
    public boolean mirandoDerecha() { return getRotacion() == 1; }

    @Override
    public boolean mirandoAbajo() { return getRotacion() == 2; }

    @Override
    public boolean mirandoIzquierda() { return getRotacion() == 3; }
}