package com.tetris.juego;

import java.util.ArrayList;

public class Board {
    private static final String CELDA_VACIA = "0000000000";
    private static final char PIEZA_ACTIVA = 'x';
    private static final char PIEZA_FIJA = 'X';
    private static final char SPACE_CHAR = ' ';

    private final ArrayList<String> matrizTablero;
    private final ArrayList<BasePiece> piezasEnTablero;
    private int contadorLineasCompletas;

    public Board() {
        this.matrizTablero = new ArrayList<>();
        this.piezasEnTablero = new ArrayList<>();
        this.contadorLineasCompletas = 0;


        for (int i = 0; i < 20; i++) {
            matrizTablero.add(CELDA_VACIA);
        }
    }

    // Encapsulamiento

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

    public int getLastActivePieceIndex() {
        return getPiezas().size() - 1;
    }

    public void setLineCount(int lineasCompletas){
        this.contadorLineasCompletas = lineasCompletas;
    }

    public int getLineCount() {
        return contadorLineasCompletas;
    }

    // Aquí termina el encapsulamiento

    public void addPieza() {
        int numeroPieza = (int)(Math.random() * 5);
        switch (numeroPieza) {
            case 0: addPieza(new PieceDog()); break;
            case 1: addPieza(new PieceL()); break;
            case 2: addPieza(new PieceSquare()); break;
            case 3: addPieza(new PieceStick()); break;
            case 4: addPieza(new PieceT()); break;
        }
    }

    public void addPieza(BasePiece pieza, int posicionX) {
        if (!pieceActiveOnBoard()) {
            if (!hayEspacioArriba(pieza, posicionX)) return;

            agregarPieza(pieza);
            int alturaP = countAltura(pieza);
            int posicionY = alturaP - 1;
            colocarPiezaEnTablero(posicionX, posicionY, pieza.getMatriz());
        }
    }

    public void addPieza(BasePiece pieza) {
        if (!pieceActiveOnBoard()) {
            int anchoP = countAncho(pieza);
            int posicionX = (int)(Math.random() * (10 - anchoP));

            if (!hayEspacioArriba(pieza, posicionX)) return;

            agregarPieza(pieza);
            int alturaP = countAltura(pieza);
            int posicionY = alturaP - 1;
            colocarPiezaEnTablero(posicionX, posicionY, pieza.getMatriz());
        }
    }

    private boolean hayEspacioArriba(BasePiece pieza, int posX) {
        String[] matriz = pieza.getMatriz();
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if (matriz[y].charAt(x) != SPACE_CHAR) {
                    int col = posX + x;
                    if (col < 0 || col >= 10) return false; // <-- VERIFICAR LÍMITES
                    if (getMatriz(y).charAt(col) != '0') {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private int countAltura(BasePiece pieza) {
        int altura = 0;
        String[] matriz = pieza.getMatriz();
        for (int i = 3; i >= 0; i--) {
            if (matriz[i] != null && !matriz[i].equals("    ")) {
                altura++;
            }
        }
        return altura;
    }

    private int countAncho(BasePiece pieza) {
        int ancho = 0;
        String[] matriz = pieza.getMatriz();
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (matriz[y].charAt(x) != SPACE_CHAR) {
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
                if (matrizPieza[y].charAt(x) != SPACE_CHAR) {
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

    public boolean moveDownActivePiece() {
        if (!pieceActiveOnBoard()) return true;

        BasePiece piezaActiva = getPiezas(getLastActivePieceIndex());
        if (verificarColisionAbajo()) {
            fijarPiezaActiva();
            LineCount();
            return true;
        }

        int[] posicion = getActivePieceLocation();
        borrarPiezaActiva();
        posicion[1]++;
        colocarPiezaEnTablero(posicion[0], posicion[1], piezaActiva.getMatriz());
        return false; // sigue cayendo
    }

    private boolean verificarColisionAbajo() {
        int[] posicion = getActivePieceLocation();
        if (posicion[1] >= 19) return true;

        for (int x = 0; x < 10; x++) {
            if (getMatriz(posicion[1]).charAt(x) == PIEZA_ACTIVA &&
                getMatriz(posicion[1] + 1).charAt(x) == PIEZA_FIJA) {
                return true;
            }
        }
        return false;
    }

    private void fijarPiezaActiva() {
        for (int y = 0; y < 20; y++) {
            String fila = getMatriz(y);
            StringBuilder nuevaFila = new StringBuilder();
            for (int x = 0; x < 10; x++) {
                nuevaFila.append(fila.charAt(x) == PIEZA_ACTIVA ? PIEZA_FIJA : fila.charAt(x));
            }
            setMatriz(y, nuevaFila.toString());
        }
    }

    private void borrarPiezaActiva() {
        for (int y = 0; y < 20; y++) {
            String fila = getMatriz(y);
            StringBuilder nuevaFila = new StringBuilder();
            for (int x = 0; x < 10; x++) {
                nuevaFila.append(fila.charAt(x) == PIEZA_ACTIVA ? '0' : fila.charAt(x));
            }
            setMatriz(y, nuevaFila.toString());
        }
    }

    public int[] getActivePieceLocation() {
        int posY = -1;
        int posX = -1;

        for (int y = 19; y >= 0; y--) {
            if (posY == -1 && getMatriz(y).contains("x")) {
                posY = y;
            }
        }

        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 20; y++) {
                if (posX == -1 && getMatriz(y).charAt(x) == PIEZA_ACTIVA) {
                    posX = x;
                }
            }
            if (posX != -1) break;
        }

        return new int[]{posX, posY};
    }

    // Rotación
    public boolean turnActivePieceLeft() {
        if (!pieceActiveOnBoard()) return false;

        BasePiece pieza = getPiezas(getLastActivePieceIndex());
        int[] posicion = getActivePieceLocation();

        borrarPiezaActiva();
        pieza.rotateLeft();
        if (!verificarRotacionValida(posicion, pieza.getMatriz())) {
            pieza.rotateRight();
        }
        colocarPiezaEnTablero(posicion[0], posicion[1], pieza.getMatriz());
        return true;
    }

    public boolean turnActivePieceRight() {
        if (!pieceActiveOnBoard()) return false;

        BasePiece pieza = getPiezas(getLastActivePieceIndex());
        int[] posicion = getActivePieceLocation();

        borrarPiezaActiva();
        pieza.rotateRight();
        if (!verificarRotacionValida(posicion, pieza.getMatriz())) {
            pieza.rotateLeft();
        }
        colocarPiezaEnTablero(posicion[0], posicion[1], pieza.getMatriz());
        return true;
    }

    private boolean verificarRotacionValida(int[] posicion, String[] matrizPieza) {
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if (matrizPieza[y].charAt(x) != SPACE_CHAR) {
                    int filaTablero = posicion[1] + (y - 3);
                    int colTablero = posicion[0] + x;
                    if (filaTablero < 0 || filaTablero >= 20 || colTablero < 0 || colTablero >= 10) return false;
                    if (filaTablero >= 0 && getMatriz(filaTablero).charAt(colTablero) == PIEZA_FIJA) return false;
                }
            }
        }
        return true;
    }

    public void LineCount() {
        for (int y = 19; y >= 0; y--) {
            String fila = getMatriz(y);
            boolean lineaCompleta = true;
            for (int x = 0; x < 10; x++) {
                if (fila.charAt(x) != PIEZA_FIJA) {
                    lineaCompleta = false;
                    break;
                }
            }
            if (lineaCompleta) {
                matrizTablero.remove(y);
                matrizTablero.add(0, CELDA_VACIA);
                contadorLineasCompletas++;
                y++; // Revisar misma fila nuevamente
            }
        }
    }

    public boolean noSpaceLeft() {
        for (int y = 0; y < 4; y++) { 
            for (int x = 0; x < getMatriz(y).length(); x++) {
                char c = getMatriz(y).charAt(x);
                if (c == PIEZA_ACTIVA || c == PIEZA_FIJA) { 
                    return true; 
                }
            }
        }
        return false; 
    }

    public boolean pieceActiveOnBoard() {
        for (int y = 0; y < 20; y++) {
            if (getMatriz(y).contains("x")) return true;
        }
        return false;
    }

    public boolean piezaActivaFija() {
        return !pieceActiveOnBoard();
    }

    public int getWidth() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWidth'");
    }
}