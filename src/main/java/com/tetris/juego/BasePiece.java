package com.tetris.juego;

import com.tetris.Interfaces.*;

public abstract class BasePiece implements IRotador, IObtenerMatrizPieza, IGiro{
    protected int rotacionActual;
    protected int rotacionesMaximas;
    protected int variacionActual;
    protected int variacionesMaximas;
    protected boolean haColisionado;

    public BasePiece() {
        this.rotacionActual = 0;
        this.haColisionado = false;
        this.variacionActual = 0;
        this.variacionesMaximas = 0;
    }

    //ACA HAY ENCAPSULAMIENTO
    protected void setRotacion(int estado){
        this.rotacionActual = estado;
    }

    public int getRotacion(){
        return rotacionActual;
    }

    protected void setMaxRotaciones(int maxRotaciones){
        this.rotacionesMaximas = maxRotaciones;
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

    public void rotarIzquierda(){
        rotacionActual = (rotacionActual - 1 + (rotacionesMaximas + 1)) % (rotacionesMaximas + 1);
    }

    public void rotarDerecha(){
        rotacionActual = (rotacionActual + 1) % (rotacionesMaximas + 1);
    }

    public void colision() {
        this.haColisionado = true;
    }

    @Override
    public abstract String[] getMatriz();
}
