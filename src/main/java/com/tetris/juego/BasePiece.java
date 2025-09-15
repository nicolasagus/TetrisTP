package com.tetris.juego;

import com.tetris.Interfaces.*;

public abstract class BasePiece implements IRotador, IObtenerMatrizPieza, IGiro{
    protected int rotacionActual;
    protected int rotacionesMaximas;
    protected int variacionActual;
    protected int variacionesMaximas;
    protected boolean haColisionado;

    public BasePiece() {
        super();
        setHaColisionado(false);
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
    } // se define por pieza

    protected void setMaxRotaciones(int maxRotaciones){
        this.rotacionesMaximas = maxRotaciones - 1;
    }

    public int getMaxRotaciones(){
        return rotacionesMaximas;
    }

    protected void setHaColisionado(boolean colision){
        this.haColisionado = colision;
    }

    public boolean getHaColisionado(){
        return haColisionado;
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

    public void rotarIzquierda(){
        rotacionActual = (rotacionActual - 1 + (rotacionesMaximas + 1)) % (rotacionesMaximas + 1);
    }

    public void rotarDerecha(){
        rotacionActual = (rotacionActual + 1) % (rotacionesMaximas + 1);
    }

    public void colision() {
        setHaColisionado(true);;
    }
}