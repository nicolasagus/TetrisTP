package com.mycompany.app;

import com.tetris.Interfaces.IGiro;
import com.tetris.Interfaces.IRotador;

public abstract class PiezaBase implements IRotador, IGiro{
    private int rotacion, maxRotaciones, variacion, maxVariaciones;
    private boolean haColicionado;

    public PiezaBase() {
        super();
        setHaColicionado(false);
    }

    // ENCAPSULACIÓN //
    protected void setRotacion(int estado){
        this.rotacion = estado;
    }

    protected int getRotacion(){
        return rotacion;
    }

    public string[] getMatrix(){
        return null;
    } // se define por cada pieza

    protected void setMaxRotaciones(int maxRotaciones){
        this.maxRotaciones = maxRotaciones-1;
    }

    public int getMaxRotaciones() {
        return maxRotaciones;
    }

    protected void setHaColicionado(boolean haColicionado) {
        this.haColicionado = haColicionado;
    }

    public boolean getHaColicionado(){
        return haColicionado;
    }

    public void setVariacion(int variacion) {
        this.variacion = variacion;
    }

    public int getVariacion() {
        return variacion;
    }

    private void setMaxVariaciones(int maxVariaciones) {
        this.maxVariaciones = maxVariaciones;
    }

    private void getMaxVariaciones() {
        return maxVariaciones;
    }

    // FIN ENCAPSULAMIENTO //
}