package com.tetris.juego;

public class Tetris {
    public Clock clock;
    public Board board;
    private final int cantidadLineasParaGanar;

    private boolean juegoIniciado;
    private boolean juegoPerdido;
    private boolean juegoGanado;

    public Tetris(int lineasParaGanar){
        this.cantidadLineasParaGanar = lineasParaGanar;
        this.clock = new Clock();
        this.board = new Board();
        this.juegoIniciado = false;
        this.juegoPerdido = false;
        this.juegoGanado = false;
    }

    public boolean getGameLost(){ return juegoPerdido; }
    private void setGameLost(boolean perdido){ this.juegoPerdido = perdido; }

    public boolean getGameWinned(){ return juegoGanado; }
    public void setGameWinned(boolean ganado){ this.juegoGanado = ganado; }

    public boolean getGameStarted(){ return juegoIniciado; }
    public void setGameStarted(boolean iniciado){ this.juegoIniciado = iniciado; }

    public int getCantLineasParaGanar(){ return cantidadLineasParaGanar; }

    public void start(){
        setGameStarted(true);
        board.addPieza();
        tick();
    }

    public String state(){
        if (!getGameStarted()) return "juego no iniciado";
        if (board.getLineCount() >= getCantLineasParaGanar()){ 
            setGameWinned(true); 
            return "juego ganado"; 
        }
        if (getGameLost()) return "juego perdido";
        return "juego en curso";
    }

    public boolean rotateLeft(){ 
        if (!juegoIniciado || juegoGanado || juegoPerdido) return false; 
        return board.turnActivePieceLeft(); 
    }
    
    public boolean rotateRight(){ 
        if (!juegoIniciado || juegoGanado || juegoPerdido) return false; 
        return board.turnActivePieceRight(); 
    }

    public void tick() {
        clock.tick();

        if (juegoGanado || juegoPerdido || !juegoIniciado) 
            return;

        if (clock.getTicks() % 2 != 0) 
            return;

        if (board.getLineCount() >= cantidadLineasParaGanar) {
            setGameWinned(true);
            return;
        }

        if (board.moveDownActivePiece()) {
            if (board.noSpaceLeft()) {
                setGameLost(true);
            } else {
                board.addPieza();
            }
        }
    }
}