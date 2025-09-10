
package com.mycompany.clashroyale.model;

public class Arena {

    // Constantes de dimensiones
    public static final int ANCHO = 18;   // tiles en X
    public static final int ALTO = 32;    // tiles en Y

    // Zonas de la arena
    private Zona zonaJugador1;
    private Zona zonaJugador2;
    private Zona rio;

    // Puentes
    private Puente puenteIzquierdo;
    private Puente puenteDerecho;

    // Torres
    private Torre torreReyJugador1;
    private Torre torrePrincesaIzquierdaJ1;
    private Torre torrePrincesaDerechaJ1;

    private Torre torreReyJugador2;
    private Torre torrePrincesaIzquierdaJ2;
    private Torre torrePrincesaDerechaJ2;

    public Arena() {
        // Inicializar zonas
        zonaJugador1 = new Zona(0, 0, ANCHO - 1, 14);
        rio = new Zona(0, 15, ANCHO - 1, 16);
        zonaJugador2 = new Zona(0, 17, ANCHO - 1, 31);

        // Inicializar puentes
        puenteIzquierdo = new Puente(5, 15, 1, 2);
        puenteDerecho = new Puente(12, 15, 1, 2);

        // Inicializar torres jugador 1
        torreReyJugador1 = new Torre("Rey J1", 8.5, 0, 2534, 90, 1.0, 7.0);
        torrePrincesaIzquierdaJ1 = new Torre("Princesa Izq J1", 3, 2, 1400, 90, 0.8, 7.5);
        torrePrincesaDerechaJ1 = new Torre("Princesa Der J1", 14, 2, 1400, 90, 0.8, 7.5);

        // Inicializar torres jugador 2
        torreReyJugador2 = new Torre("Rey J2", 8.5, 31, 2534, 90, 1.0, 7.0);
        torrePrincesaIzquierdaJ2 = new Torre("Princesa Izq J2", 3, 29, 1400, 90, 0.8, 7.5);
        torrePrincesaDerechaJ2 = new Torre("Princesa Der J2", 14, 29, 1400, 90, 0.8, 7.5);
    }

    // Getters
    public Zona getZonaJugador1() { return zonaJugador1; }
    public Zona getZonaJugador2() { return zonaJugador2; }
    public Zona getRio() { return rio; }

    public Torre getTorreReyJugador1() { return torreReyJugador1; }
    public Torre getTorreReyJugador2() { return torreReyJugador2; }

    // Método de validación de coordenadas
    public boolean coordenadaValida(double x, double y) {
        return (x >= 0 && x < ANCHO) && (y >= 0 && y < ALTO);
    }
}

