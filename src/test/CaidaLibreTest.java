package com.tetris.juego;

import static org.junit.Assert.*;
import org.junit.Test;
public class CaidaLibreTest {

    @Test 
    public void testCaidaLibreInicial() {
        CaidaLibre caidaLibre = new CaidaLibre();
        assertFalse("La caída libre no debería estar activa al iniciar", caidaLibre.estaActiva());
    }
    @Test
    public void testActivarCaidaLibre() {
        CaidaLibre caidaLibre = new CaidaLibre();
        caidaLibre.activarCaidaLibre();
        assertTrue("La caída libre debería estar activa después de activarla", caidaLibre.estaActiva());
    }
}