package com.tetris.juego;

public class Tetris {
    private boolean autoSpawn = true; // SOLO PARA LOS TEST
    public Clock clock = new Clock();
    public Board board = new Board();
    private final int cantidadLineasParaGanar;

    private boolean juegoIniciado;
    private boolean juegoPerdido;
    private boolean juegoGanado;

    public Tetris(int lineasParaGanar) {
        super();
        cantidadLineasParaGanar = lineasParaGanar;
    }

    public boolean getGameLost() {
        return juegoPerdido;
    }

    private void setGameLost(boolean perdido) {
        this.juegoPerdido = perdido;
    }

    public boolean getGameWinned() {
        return juegoGanado;
    }

    public void setGameWinned(boolean ganado) {
        this.juegoGanado = ganado;
    }

    public boolean getGameStarted() {
        return juegoIniciado;
    }

    public void setGameStarted(boolean iniciado) {
        this.juegoIniciado = iniciado;
    }

    public int getCantLineasParaGanar() {
        return cantidadLineasParaGanar;
    }

    public void setAutoSpawn(boolean autoSpawn) {
        this.autoSpawn = autoSpawn;
    }

    public void start() {
        setGameStarted(true);
        tick();
    }

    public String state() {
        if (!getGameStarted()) {
            return "juego no iniciado";
        }

        if (board.getLineCount() >= getCantLineasParaGanar()) {
            setGameWinned(true);
            return "juego ganado";
        } else if (getGameLost()) {
            return "juego perdido";
        }
        return "juego en curso";
    }

    public boolean rotateLeft() {
        if (!juegoIniciado || juegoGanado || juegoPerdido) {
            return false;
        }
        return board.turnActivePieceLeft();
    }

    public boolean rotateRight() {
        if (!juegoIniciado || juegoGanado || juegoPerdido) {
            return false;
        }
        return board.turnActivePieceRight();
    }

    public void tick() {
        clock.tick();

        if (getGameWinned() || getGameLost() || !getGameStarted()) {
            return;
        }

        if (clock.getTicks() % 2 != 0) {
            return;
        }

        if (board.moveDownActivePiece()) {
            if (board.noSpaceLeft()) {
                setGameLost(true);
            } else {
                if (autoSpawn) {
                    board.addPieza();
                }
            }
        }

        if (board.getLineCount() >= getCantLineasParaGanar()) {
            setGameWinned(true);
        }
    }
}