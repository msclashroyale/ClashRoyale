/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clashroyale.model;

public class Partida {

    private Arena arena;
    private Jugador jugador1;
    private Jugador jugador2;
    private int tiempoRestante; // en segundos
    private boolean enCurso;

    public Partida(Jugador jugador1, Jugador jugador2) {
        this.arena = new Arena();
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.tiempoRestante = 180; // 3 minutos estándar
        this.enCurso = true;
    }

    // Métodos de control de la partida
    public void iniciar() {
        enCurso = true;
        tiempoRestante = 180;
    }

    public void finalizar() {
        enCurso = false;
        tiempoRestante = 0;
    }

    public void reducirTiempo(int segundos) {
        if (enCurso) {
            tiempoRestante -= segundos;
            if (tiempoRestante <= 0) {
                tiempoRestante = 0;
                finalizar();
            }
        }
    }

    // Getters
    public Arena getArena() { return arena; }
    public Jugador getJugador1() { return jugador1; }
    public Jugador getJugador2() { return jugador2; }
    public int getTiempoRestante() { return tiempoRestante; }
    public boolean isEnCurso() { return enCurso; }
}

