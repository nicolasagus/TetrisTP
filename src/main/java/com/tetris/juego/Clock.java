package com.tetris.juego;

public class Clock {
    private int contadorTicks;
    private final long tiempoInicio;

    public Clock() {
        this.contadorTicks = 0;
        this.tiempoInicio = System.currentTimeMillis();
    }

    public int getTicks(){
        return contadorTicks;
    }

    public void setTicks(int ticks){
        this.contadorTicks = ticks;
    }

    public long getTimeMiliSeconds(){
        return tiempoInicio - System.currentTimeMillis();
    }

    public void tick(){
        setTicks(getTicks() + 2);
    }
}