package com.tetris.juego;

public class PieceDog extends BasePiece{
    private static final String [][] matrizHorizontal = {
        {
            "    ",
            "    ",
            " DD ",
            "DD  "
        },
        {
            "    ",
            "    ",
            "DD  ",
            " DD "
        },
    };

    private static final String[][] matrizVertical = {
        {
            "    ",
            "D   ",
            "DD  ",
            " D  "
        },
        {
            "    ",
            " D  ",
            "DD  ",
            "D   "
        },
    };

    public PieceDog() {
        super();
        setMaxRotaciones(1);
        setMaxVariaciones(1);
        setRotacion((int)(Math.random() * this.getMaxRotaciones()));
        setVariacion((int)(Math.random() * this.getMaxVariaciones()));
    }

    public PieceDog(int rotacion, int variacion){
        super();
        setMaxRotaciones(1);
        setMaxVariaciones(1);
        setRotacion(rotacion);
        setVariacion(variacion);
    }

    @Override
    public String[] getMatriz(){
        if (getRotacion() == 0) {
            return matrizHorizontal[getVariacion()];
        } else {
            return matrizVertical[getVariacion()];
        }
    }

    @Override
    public boolean mirandoDerecha(){ return getRotacion() == 0; }

    @Override
    public boolean mirandoIzquierda() { return getRotacion() == 0;}

    @Override
    public boolean mirandoArriba() { return getRotacion() == 1; }

    @Override
    public boolean mirandoAbajo() { return getRotacion() == 1; }
}