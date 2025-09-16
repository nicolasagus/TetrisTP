package com.tetris.juego;

import static org.junit.Assert.*;
import org.junit.Test;

public class CompletarLineaTest {

    

    @Test
    public void testCompletarLinea() {
        CompletarLinea completarLinea = new CompletarLinea();
        completarLinea.marcarLineaCompleta(); // Simula completar una línea
        assertTrue("Debe detectar línea completa cuando se marca", completarLinea.hayLineaCompleta());
    }

    @Test
    public void testNoHayLineaCompletaSinMarcar() {
        CompletarLinea completarLinea = new CompletarLinea();
        assertFalse("No debe haber línea completa si no se marca", completarLinea.hayLineaCompleta());
    }
    @Test
    public void testLineaCompletaSeResetea() {
        CompletarLinea completarLinea = new CompletarLinea();
        completarLinea.marcarLineaCompleta(); 
        completarLinea.resetearLineaCompleta(); 
}
    @Test
}