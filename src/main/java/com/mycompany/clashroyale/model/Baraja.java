package com.mycompany.clashroyale.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Baraja {
    private List<Carta> cartas;

    public Baraja(List<Carta> todasLasCartas) {
        // Mezclamos todas las cartas
        Collections.shuffle(todasLasCartas);
        // Elegimos las primeras 8 como baraja
        this.cartas = new ArrayList<>(todasLasCartas.subList(0, 8));
    }

    public List<Carta> getCartas() {
        return cartas;
    }
}
