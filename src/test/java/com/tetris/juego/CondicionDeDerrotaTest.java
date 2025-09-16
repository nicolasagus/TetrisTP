package com.tetris.juego;

import static org.junit.Assert.*;
import org.junit.Test;

public class CondicionDeDerrotaTest {

    @Test
    public void testNoDerrotaInicial() {
        CondicionDeDerrota condicion = new CondicionDeDerrota();
        assertFalse("No debería haber derrota al iniciar", condicion.esDerrota());
    }

    @Test
    public void testDerrotaAlActivarCondicion() {
        CondicionDeDerrota condicion = new CondicionDeDerrota();
        condicion.activarDerrota(); // Simula la condición de derrota
        assertTrue("Debe detectar derrota cuando se activa la condición", condicion.esDerrota());
    }

    @Test
    public void testDerrotaNoSeActivaSinCondicion() {
        CondicionDeDerrota condicion = new CondicionDeDerrota();
        assertFalse("No debe haber derrota si no se activa la condición", condicion.esDerrota());
    }
    
}