package com.tetris.juego;

public class PieceStick extends BasePiece{
    private static final String[] matrizHorizontal = {
        "    ",
        "    ",
        "    ",
        "SSSS"
    };

    private static final String[] matrizVertical = {
        "S   ",
        "S   ",
        "S   ",
        "S   "
    };

    public PieceStick() {
        super();
        setMaxRotaciones(1);
        setRotacion((int) (Math.random() * this.getMaxRotaciones()));
    }

    public PieceStick(int rotacion) {
        super();
        setMaxRotaciones(1);
        setRotacion(rotacion);
    }

    @Override
    public String[] getMatriz() {
        if (getRotacion() == 0) {
            return matrizHorizontal;
        } else {
            return matrizVertical;
        }
        
    }

    @Override
    public boolean mirandoAbajo() { return this.getRotacion() == 1; }

    @Override
    public boolean mirandoArriba() { return this.getRotacion() == 1; }

    @Override
    public boolean mirandoIzquierda() { return this.getRotacion() == 0; }

    @Override
    public boolean mirandoDerecha() { return this.getRotacion() == 0; }
}
