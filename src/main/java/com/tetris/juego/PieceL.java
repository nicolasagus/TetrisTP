package com.tetris.juego;

public class PieceL extends BasePiece{
    private static final String[][][] todasLasFormas = {
        { 
            // Rotación 0 - Arriba
            {
                "    ", 
                "L   ", 
                "L   ",     // Variación 0
                "LL  "
            }, 
            {
                "    ", 
                " L  ",         // Variación 1
                " L  ", 
                "LL  "
            }  
        },

        { 
        // Rotación 1 - Derecha
            {
                "    ", 
                "    ",             // Variación 0
                "LLL ", 
                "L   "
            }, 
            {
                "    ", 
                "    ",         // Variación 1
                "L   ", 
                "LLL "
            }  
        },

        { 
        // Rotación 2 - Abajo
            {
                "    ", 
                "LL  ", 
                " L  ",         // Variación 0
                " L  "
            }, 
            {
                "    ", 
                "LL  ",         // Variación 1
                "L   ", 
                "L   "
            }  
        },

        { 
        // Rotación 3 - Izquierda
            {
                "    ", 
                "    ",     // Variación 0
                "  L ", 
                "LLL "
            }, 
            {
                "    ", 
                "    ",         // Variación 1
                "LLL ", 
                "  L "
            }  
        }
    };

    public PieceL() {
        super();
        Math.random();
        setMaxRotaciones(4);
        setMaxVariaciones(2);
        setRotacion((int)(Math.random() * this.getMaxRotaciones()));
        setVariacion((int)(Math.random() * this.getMaxVariaciones()));
    }

    public PieceL(int rotacion, int variacion) {
        super();
        setMaxRotaciones(4);
        setMaxVariaciones(2);
        setRotacion(rotacion);
        setVariacion(variacion);
    }

    @Override
    public String[] getMatriz() {
        return todasLasFormas[getRotacion()][getVariacion()];
    }

    @Override
    public boolean mirandoArriba() { return getRotacion()==0; }

    @Override
    public boolean mirandoDerecha() { return getRotacion()==1; }

    @Override
    public boolean mirandoAbajo() { return getRotacion()==2; }

    @Override
    public boolean mirandoIzquierda(){ return getRotacion()==3; }
}