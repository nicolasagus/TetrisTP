package com.tetris.juego;

import com.tetris.Interfaces.IGiro;
import com.tetris.Interfaces.IObtenerMatrizPieza;
import com.tetris.Interfaces.IRotator;

public abstract class BasePiece implements IRotator, IObtenerMatrizPieza, IGiro{
    protected int rotacionActual;
    protected int rotacionesMaximas;
    protected int variacionActual;
    protected int variacionesMaximas;

    public BasePiece() {
        super();
    }

    //ACA HAY ENCAPSULAMIENTO
    protected void setRotacion(int estado){
        this.rotacionActual = estado;
    }

    public int getRotacion(){
        return rotacionActual;
    }

    @Override
    public String[] getMatriz(){
        return null;
    }

    protected void setMaxRotaciones(int maxRotaciones){
        this.rotacionesMaximas = maxRotaciones - 1;
    }

    public int getMaxRotaciones(){
        return rotacionesMaximas;
    }

    protected void setVariacion(int variacion){
        this.variacionActual = variacion;
    }

    public int getVariacion(){
        return variacionActual;
    }

    protected void setMaxVariaciones(int maxVariaciones){
        this.variacionesMaximas = maxVariaciones;
    }

    public int getMaxVariaciones(){
        return variacionesMaximas;
    }
    // ACA TERMINA

    @Override
    public void rotateLeft(){
        rotacionActual = (rotacionActual - 1 + (rotacionesMaximas + 1)) % (rotacionesMaximas + 1);
    }

    @Override
    public void rotateRight(){
        rotacionActual = (rotacionActual + 1) % (rotacionesMaximas + 1);
    }
