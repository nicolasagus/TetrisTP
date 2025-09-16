package com.tetris.juego;

import java.util.ArrayList;

public class Board {
        private static final String CELDA_VACIA = "0000000000";
        private static final char PIEZA_ACTIVA = 'x';
        private static final char PIEZA_FIJA = 'X';
        private static final char ESPACIO_VACIO = ' ';

        private ArrayList<String> matrizTablero;
        private ArrayList<BasePiece> piezasEnTablero;
        private int contadorLineasCompletas;

    public Board() {
        this.matrizTablero = new ArrayList<String>();
        this.piezasEnTablero = new ArrayList<BasePiece>();
        this.contadorLineasCompletas = 0;

        for (int i = 0; i < 20; i++) {
            matrizTablero.add(CELDA_VACIA);
        }
    }

    // ENCAPSULAMIENTO
    public String getMatriz(int indice) {
        return matrizTablero.get(indice);
    }

    public ArrayList<String> getMatriz() {
        return this.matrizTablero;
    }

    public void setMatriz(int indice, String valor) {
        this.matrizTablero.set(indice, valor);
    }

    public ArrayList<BasePiece> getPiezas() {
        return this.piezasEnTablero;
    }

    public BasePiece getPiezas(int indice) {
        return piezasEnTablero.get(indice);
    }

    private void agregarPieza(BasePiece pieza) {
        piezasEnTablero.add(pieza);
    }

    public int getUltimaPiezaActivaEnIndex() {
        return getPiezas().size() - 1;
    }

    public int getContarLineas() {
        return contadorLineasCompletas;
    }

    public void addPieza() {
        int numeroPieza = (int)(Math.random() * 5);
        switch (numeroPieza) {
            case 0:
                agregarPieza(new PieceDog());
                break;
            case 1:
                agregarPieza(new PieceL());
                break;
            case 2:
                agregarPieza(new PieceSquare());
                break;
            case 3:
                agregarPieza(new PieceStick());
                break;
            case 4:
                agregarPieza(new PieceT());
                break;
        }
    }

    public void addPieza(BasePiece pieza, int posicionX){
        if(!piezaActivaEnTablero()) {
            if(!hayEspacioArriba(pieza, posicionX)) 
            return;

            agregarPieza(pieza);
            int alturaP = contarAltura(pieza);
            int posicionY = alturaP - 1;
            colocarPiezaEnTablero(posicionX, posicionY, pieza.getMatriz());
        }
    }

    public void addPieza(BasePiece pieza) {
        if(!piezaActivaEnTablero()) {
            int anchuraP = contarAncho(pieza);
            int posicionX = (int)(Math.random() * (10 - anchuraP));

            if (!hayEspacioArriba(pieza, posicionX)) 
            return;

            agregarPieza(pieza);
            int alturaP = contarAltura(pieza);
            int posicionY = alturaP - 1;
            colocarPiezaEnTablero(posicionX, posicionY, pieza.getMatriz());
        }
    }

    private boolean hayEspacioArriba(BasePiece pieza, int posX) {
        String[] matriz = pieza.getMatriz();
        for (int y = 0; y < 4; y++) {
            for(int x = 0; x < 4; x++) {
                if (matriz[y].charAt(x)  != ESPACIO_VACIO) {
                    int col = posX + x;
                    if (col < 0 || col >= 10) 
                    return false; 
                }
            }
        }
        return true;
    }

    private int contarAltura(BasePiece pieza) {
        int altura = 0;
        String[] matriz = pieza.getMatriz();
        for (int i = 3; i >= 0; i--) {
            if (matriz[i] != null && !matriz[i].equals("    ")) {
                altura++;
            }
        }
        return altura;
    }

    private int contarAncho(BasePiece pieza) {
        int ancho = 0;
        String[] matriz = pieza.getMatriz();
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (matriz[y].charAt(x) != ESPACIO_VACIO) {
                    ancho++;
                    break;
                }
            }
        }
        return ancho;
    }

    private void colocarPiezaEnTablero(int posX, int posY, String[] matrizPieza) {
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if (matrizPieza[y].charAt(x) != ESPACIO_VACIO) {
                    int filaTablero = posY + (y - 3);
                    int colTablero = posX + x;

                    if (filaTablero >= 0 && filaTablero < 20 && colTablero >= 0 && colTablero < 10) {
                        String filaActual = getMatriz(filaTablero);
                        StringBuilder nuevaFila = new StringBuilder(filaActual);
                        nuevaFila.setCharAt(colTablero, PIEZA_ACTIVA);
                        setMatriz(filaTablero, nuevaFila.toString());
                    }
                }
            }
        }
    }

    public boolean moverPiezaActivaHaciaAbajo() {
        if (!piezaActivaEnTablero())
        return true;

        BasePiece piezaActiva = getPiezas(getUltimaPiezaActivaEnIndex());
        if (verificarColisionAbajo()) {
            piezaActiva.colision();
            fijarPiezaActiva();
            contarLineCount();
            return true;
        }

        int[] posicion = getLocacionPiezaActiva();
        borrarPiezaActiva();
        posicion[1]++;
        colocarPiezaEnTablero(posicion[0], posicion[1], piezaActiva.getMatriz());
        return false;
    }

    private boolean verificarColisionAbajo() {
        int[] posicion = getLocacionPiezaActiva();
        if (posicion[1] >= 19)
        return true;

        for (int x = 0; x < 10; x++) {
            if (getMatriz(posicion[1]).charAt(x) == PIEZA_ACTIVA && getMatriz(posicion[1] + 1).charAt(x) == PIEZA_FIJA) {
                return true;
            }
        }
        return false;
    }

    private void fijarPiezaActiva() {
        for (int y = 0; y <  20; y++) {
            String fila = getMatriz(y);
            StringBuilder nuevaFila = new StringBuilder();
            for (int x = 0; x < 10; x++) {
                nuevaFila.append(fila.charAt(x) == PIEZA_ACTIVA ? PIEZA_FIJA : fila.charAt(x));
            }
            setMatriz(y, nuevaFila.toString());
        }
    }

    private void borrarPiezaActiva(){
        for (int y = 0; y < 20; y++) {
            String fila = getMatriz(y);
            StringBuilder nuevaFila = new StringBuilder();
            for (int x = 0; x < 10; x++) {
                nuevaFila.append(fila.charAt(x) == PIEZA_ACTIVA ? '0' : fila.charAt(x));
            }
            setMatriz(y, nuevaFila.toString());
        }
    }

    public int[] getLocacionPiezaActiva() {
        int posX = -1;
        int posY = -1;

        for (int y = 19; y >= 0; y--) {
            if (posY == -1 && getMatriz(y).contains("X")) {
                posY = y;
            }
        }

        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 20; y++) {
                if (posX == -1 && getMatriz(y).charAt(x) == PIEZA_ACTIVA) {
                    posX = x;
                }
            }
            if (posX != -1)
            break;
        }
        return new int[]{posX, posY};
    }

    public boolean rotarPiezaActivaIzquierda() {
        if (!piezaActivaEnTablero())
        return false;

        BasePiece pieza = getPiezas(getUltimaPiezaActivaEnIndex());
        int[] posicion = getLocacionPiezaActiva();

        borrarPiezaActiva();
        pieza.rotarIzquierda();
        if (!verificarRotacionValida(posicion, pieza.getMatriz())) {
            pieza.rotarDerecha();
        }
        colocarPiezaEnTablero(posicion[0], posicion[1], pieza.getMatriz());
        return true;
    }

    public boolean rotarPiezaActivaDerecha() {
        if (!piezaActivaEnTablero())
        return false;

        BasePiece pieza = getPiezas(getUltimaPiezaActivaEnIndex());
        int[] posicion = getLocacionPiezaActiva();

        borrarPiezaActiva();
        pieza.rotarDerecha();
        if (!verificarRotacionValida(posicion, pieza.getMatriz())) {
            pieza.rotarIzquierda();
        }
        colocarPiezaEnTablero(posicion[0], posicion[1], pieza.getMatriz());
        return true;
    }

    private boolean verificarRotacionValida(int[] posicion, String[] matrizPieza) {
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if (matrizPieza[y].charAt(x) != ESPACIO_VACIO) {
                    int filaTablero = posicion[1] + (y - 3);
                    int colTablero = posicion[0] + x;
                    if (filaTablero < 0 || filaTablero >= 20 || colTablero < 0 || colTablero >= 10)
                    return false;
                    if (filaTablero >= 0 && getMatriz(filaTablero).charAt(colTablero) == PIEZA_FIJA)
                    return false;
                }
            }
        }
        return true;
    }

    public void contarLineCount() {
        for (int y = 19; y >= 0; y++) {
            String fila = getMatriz(y);
            boolean lineaCompleta = true;
            for(int x = 0; x < 10; x++) {
                if (fila.charAt(x) != PIEZA_FIJA) {
                    lineaCompleta = false;
                    break;
                }
            }
            if (lineaCompleta) {
                matrizTablero.remove(y);
                matrizTablero.add(0, CELDA_VACIA);
                contadorLineasCompletas++;
                y++;
            }
        }
    }

    public boolean noHayEspacio() {
        for (int y = 0; y < 4; y++) {
            String fila = getMatriz(y);
            for(int x = 0; x < 10; x++) {
                if (fila.charAt(x) == PIEZA_FIJA) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean piezaActivaEnTablero() {
        for (int y = 0; y < 20; y++) {
            if (getMatriz(y).contains("x")) return true;
        }
        return false;
    }

    public void printBoard() {
        for (int i = 0; i < getMatriz().size(); i++) {
            System.out.println(getMatriz(i));
        }
    }
}
