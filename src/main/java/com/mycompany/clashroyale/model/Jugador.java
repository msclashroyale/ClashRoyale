package com.mycompany.clashroyale.model;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private String nombre;
    private int nivel;
    private int trofeos;
    private int elixir;
    private Baraja baraja;       // la baraja completa de 8 cartas
    private List<Carta> mano;    // cartas actualmente disponibles
    private int proximaCarta;    // índice de la próxima carta a entrar

    public Jugador(String nombre, int nivel, int trofeos, Baraja baraja) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.trofeos = trofeos;
        this.baraja = baraja;
        this.elixir = 5;

        // Inicializar mano con las 4 primeras cartas
        this.mano = new ArrayList<>(baraja.getCartas().subList(0, 4));
        this.proximaCarta = 4;
    }

    /** Jugar una carta de la mano */
    public boolean jugarCarta(Carta carta) {
        if (mano.contains(carta) && gastarElixir(carta.getCostoElixir())) {
            // Reemplazar la carta jugada por la próxima en la baraja
            int index = mano.indexOf(carta);
            mano.set(index, baraja.getCartas().get(proximaCarta));
            // Avanzar el índice de la próxima carta (cíclico con %8)
            proximaCarta = (proximaCarta + 1) % baraja.getCartas().size();
            return true;
        }
        return false;
    }

    // Métodos para manejar elixir
    public void ganarElixir(int cantidad) {
        elixir = Math.min(elixir + cantidad, 10);
    }

    public boolean gastarElixir(int cantidad) {
        if (elixir >= cantidad) {
            elixir -= cantidad;
            return true;
        }
        return false;
    }

    // Getters
    public String getNombre() { return nombre; }
    public int getNivel() { return nivel; }
    public int getTrofeos() { return trofeos; }
    public int getElixir() { return elixir; }
    public Baraja getBaraja() { return baraja; }
    public List<Carta> getMano() { return mano; }
    
    public boolean respawnearCarta(Arena arena, int x, int y) {
        if (mano.isEmpty()) return false;

        // Elegir carta aleatoria de la mano
        Carta carta = mano.get((int)(Math.random() * mano.size()));

        if (carta != null && elixir >= carta.getCostoElixir()) {
            if (gastarElixir(carta.getCostoElixir())) {
                if (arena.colocarCarta(carta, x, y)) {
                    System.out.println(nombre + " colocó " + carta.getNombre() +
                                       " en [" + x + "," + y + "] (elixir restante: " + elixir + ")");
                }

                // Reemplazar por la próxima carta de la baraja
                int index = mano.indexOf(carta);
                mano.set(index, baraja.getCartas().get(proximaCarta));
                proximaCarta = (proximaCarta + 1) % baraja.getCartas().size();

                return true;
            }
        }

        return false;
    }



}
