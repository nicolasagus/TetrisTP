package com.tetris.juego;

import java.util.ArrayList;

public class Board {
    private static final String CELDA_VACIA = "0000000000";
    private static final char PIEZA_ACTIVA = 'x';
    private static final char PIEZA_FIJA = 'X';
    private static final char SPACE_CHAR = ' ';

    private ArrayList<String> matrizTablero;
    private ArrayList<BasePiece> piezasEnTablero;
    private int contadorLineasCompletas;

    public Board() {
        this.matrizTablero = new ArrayList<>();
        this.piezasEnTablero = new ArrayList<>();
        this.contadorLineasCompletas = 0;

        for (int i = 0; i < 20; i++) {
            matrizTablero.add(CELDA_VACIA);
        }
    }

    public String getMatrix(int indice) {
        return matrizTablero.get(indice);
    }

    public ArrayList<String> getMatrix() {
        return this.matrizTablero;
    }

    public void setMatrix(int indice, String valor) {
        this.matrizTablero.set(indice, valor);
    }

    public ArrayList<BasePiece> getPieces() {
        return this.piezasEnTablero;
    }

    public BasePiece getPieces(int indice) {
        return piezasEnTablero.get(indice);
    }

    private void agregarPieza(BasePiece pieza) {
        piezasEnTablero.add(pieza);
    }

    public int getLastActivePieceIndex() {
        return getPieces().size() - 1;
    }

    public int getLineCount() {
        return contadorLineasCompletas;
    }

    // Agregar pieza aleatoria
    public void addPiece() {
        int numeroPieza = (int)(Math.random() * 5);
        switch (numeroPieza) {
            case 0: addPiece(new PieceDog()); break;
            case 1: addPiece(new PieceL()); break;
            case 2: addPiece(new PieceSquare()); break;
            case 3: addPiece(new PieceStick()); break;
            case 4: addPiece(new PieceT()); break;
        }
    }

    public void addPiece(BasePiece pieza, int posicionX) {
        if (!pieceActiveOnBoard()) {
            if (!hayEspacioArriba(pieza, posicionX)) return;

            agregarPieza(pieza);
            int alturaP = countHeight(pieza);
            int posicionY = alturaP - 1;
            colocarPiezaEnTablero(posicionX, posicionY, pieza.getMatriz());
        }
    }

    public void addPiece(BasePiece pieza) {
        if (!pieceActiveOnBoard()) {
            int anchuraP = countWidth(pieza);
            int posicionX = (int)(Math.random() * (10 - anchuraP));

            if (!hayEspacioArriba(pieza, posicionX)) return;

            agregarPieza(pieza);
            int alturaP = countHeight(pieza);
            int posicionY = alturaP - 1;
            colocarPiezaEnTablero(posicionX, posicionY, pieza.getMatriz());
        }
    }

    // Verificar espacio arriba
    private boolean hayEspacioArriba(BasePiece pieza, int posX) {
        String[] matriz = pieza.getMatriz();
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if (matriz[y].charAt(x) != SPACE_CHAR) {
                    int col = posX + x;
                    if (col < 0 || col >= 10) return false; // <-- VERIFICAR LÍMITES
                    if (getMatrix(y).charAt(col) != '0') {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // Contar altura y ancho de pieza
    private int countHeight(BasePiece pieza) {
        int altura = 0;
        String[] matriz = pieza.getMatriz();
        for (int i = 3; i >= 0; i--) {
            if (matriz[i] != null && !matriz[i].equals("    ")) {
                altura++;
            }
        }
        return altura;
    }

    private int countWidth(BasePiece pieza) {
        int anchura = 0;
        String[] matriz = pieza.getMatriz();
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (matriz[y].charAt(x) != SPACE_CHAR) {
                    anchura++;
                    break;
                }
            }
        }
        return anchura;
    }

    private void colocarPiezaEnTablero(int posX, int posY, String[] matrizPieza) {
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if (matrizPieza[y].charAt(x) != SPACE_CHAR) {
                    int filaTablero = posY + (y - 3);
                    int colTablero = posX + x;

                    if (filaTablero >= 0 && filaTablero < 20 && colTablero >= 0 && colTablero < 10) {
                        String filaActual = getMatrix(filaTablero);
                        StringBuilder nuevaFila = new StringBuilder(filaActual);
                        nuevaFila.setCharAt(colTablero, PIEZA_ACTIVA);
                        setMatrix(filaTablero, nuevaFila.toString());
                    }
                }
            }
        }
    }

    // Movimiento
    public boolean moveDownActivePiece() {
        if (!pieceActiveOnBoard()) return true;

        BasePiece piezaActiva = getPieces(getLastActivePieceIndex());
        if (verificarColisionAbajo()) {
            piezaActiva.colision();
            fijarPiezaActiva();
            contarLineCount();
            return true;
        }

        int[] posicion = getActivePieceLocation();
        borrarPiezaActiva();
        posicion[1]++;
        colocarPiezaEnTablero(posicion[0], posicion[1], piezaActiva.getMatriz());
        return false;
    }

    private boolean verificarColisionAbajo() {
        int[] posicion = getActivePieceLocation();
        if (posicion[1] >= 19) return true;

        for (int x = 0; x < 10; x++) {
            if (getMatrix(posicion[1]).charAt(x) == PIEZA_ACTIVA &&
                getMatrix(posicion[1] + 1).charAt(x) == PIEZA_FIJA) {
                return true;
            }
        }
        return false;
    }

    private void fijarPiezaActiva() {
        for (int y = 0; y < 20; y++) {
            String fila = getMatrix(y);
            StringBuilder nuevaFila = new StringBuilder();
            for (int x = 0; x < 10; x++) {
                nuevaFila.append(fila.charAt(x) == PIEZA_ACTIVA ? PIEZA_FIJA : fila.charAt(x));
            }
            setMatrix(y, nuevaFila.toString());
        }
    }

    private void borrarPiezaActiva() {
        for (int y = 0; y < 20; y++) {
            String fila = getMatrix(y);
            StringBuilder nuevaFila = new StringBuilder();
            for (int x = 0; x < 10; x++) {
                nuevaFila.append(fila.charAt(x) == PIEZA_ACTIVA ? '0' : fila.charAt(x));
            }
            setMatrix(y, nuevaFila.toString());
        }
    }

    public int[] getActivePieceLocation() {
        int posY = -1;
        int posX = -1;

        for (int y = 19; y >= 0; y--) {
            if (posY == -1 && getMatrix(y).contains("x")) {
                posY = y;
            }
        }

        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 20; y++) {
                if (posX == -1 && getMatrix(y).charAt(x) == PIEZA_ACTIVA) {
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

        BasePiece pieza = getPieces(getLastActivePieceIndex());
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

        BasePiece pieza = getPieces(getLastActivePieceIndex());
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
                    if (filaTablero >= 0 && getMatrix(filaTablero).charAt(colTablero) == PIEZA_FIJA) return false;
                }
            }
        }
        return true;
    }

    // Contar líneas completas
    public void contarLineCount() {
        for (int y = 19; y >= 0; y--) {
            String fila = getMatrix(y);
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
        for (int y = 0; y < 4; y++) { // primeras 4 filas
            String fila = getMatrix(y);
            for (int x = 0; x < 10; x++) {
                if (fila.charAt(x) == PIEZA_FIJA) { // solo bloques fijos cuentan
                    return true; // no hay espacio
                }
            }
        }
        return false; // hay espacio
    }

    public boolean pieceActiveOnBoard() {
        for (int y = 0; y < 20; y++) {
            if (getMatrix(y).contains("x")) return true;
        }
        return false;
    }

    public void printBoard() {
        for (int i = 0; i < getMatrix().size(); i++) {
            System.out.println(getMatrix(i));
        }
    }
}