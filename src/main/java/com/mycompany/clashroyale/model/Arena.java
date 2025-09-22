package com.mycompany.clashroyale.model;

import java.util.ArrayList;
import java.util.List;

public class Arena {
    public static final int ANCHO = 18; // columnas (X)
    public static final int ALTO = 32; // filas (Y)

    private Celda[][] grid;
    private List<CartaEnMapa> cartasActivas;
    private List<Unidad> unidades;
    private List<Torre> torres;

    public Arena() {
        grid = new Celda[ALTO][ANCHO];
        cartasActivas = new ArrayList<>();
        unidades = new ArrayList<>();
        torres = new ArrayList<>();
        inicializar();
    }

    public void actualizarUnidades() {
        // Eliminar unidades muertas
        unidades.removeIf(u -> u.getEstado() == Unidad.Estado.MUERTO);

        // Actualizar cada unidad
        for (Unidad unidad : unidades) {
            unidad.actualizar(this);
        }
    }

    public List<Unidad> getUnidades() {
        return unidades;
    }

    public List<Torre> getTorres() {
        return torres;
    }

    private void inicializar() {
        for (int y = 0; y < ALTO; y++) {
            for (int x = 0; x < ANCHO; x++) {
                grid[y][x] = new Celda(x, y, TipoCelda.VACIA);
            }
        }

        // Río
        for (int x = 0; x < ANCHO; x++) {
            grid[15][x].setTipo(TipoCelda.RIO);
            grid[16][x].setTipo(TipoCelda.RIO);
        }

        // Puentes (X=3 y X=14 en Y=15 y 16)
        grid[15][3].setTipo(TipoCelda.PUENTE);
        grid[16][3].setTipo(TipoCelda.PUENTE);
        grid[15][14].setTipo(TipoCelda.PUENTE);
        grid[16][14].setTipo(TipoCelda.PUENTE);

        // === Torres Jugador 1 ===
        // Rey J1 (4x4 en torno a (8,0))
        colocarBloque(7, 1, 4, 4, TipoCelda.TORRE_REY);

        // Princesa Izquierda J1 (3x3 en torno a (3,2))
        colocarBloque(2, 5, 3, 3, TipoCelda.TORRE_PRINCESA);

        // Princesa Derecha J1 (3x3 en torno a (14,2))
        colocarBloque(13, 5, 3, 3, TipoCelda.TORRE_PRINCESA);

        // === Torres Jugador 2 ===
        // Rey J2 (4x4 en torno a (8,31))
        colocarBloque(7, 27, 4, 4, TipoCelda.TORRE_REY);

        // Princesa Izquierda J2 (3x3 en torno a (3,29))
        colocarBloque(2, 24, 3, 3, TipoCelda.TORRE_PRINCESA);

        // Princesa Derecha J2 (3x3 en torno a (14,29))
        colocarBloque(13, 24, 3, 3, TipoCelda.TORRE_PRINCESA);
    }

    /** Coloca un bloque rectangular de celdas de un tipo */
    private void colocarBloque(int xInicio, int yInicio, int ancho, int alto, TipoCelda tipo) {
        for (int y = yInicio; y < yInicio + alto && y < ALTO; y++) {
            for (int x = xInicio; x < xInicio + ancho && x < ANCHO; x++) {
                grid[y][x].setTipo(tipo);
            }
        }
    }

    public Celda[][] getGrid() {
        return grid;
    }

    public boolean colocarCarta(Carta carta, int x, int y) {
        if (x < 0 || x >= ANCHO || y < 0 || y >= ALTO)
            return false;
        Celda celda = grid[y][x];

        // No permitir sobre río ni torres
        if (celda.getTipo() == TipoCelda.RIO ||
                celda.getTipo() == TipoCelda.TORRE_REY ||
                celda.getTipo() == TipoCelda.TORRE_PRINCESA) {
            return false;
        }

        // Si la celda está libre
        if (celda.getCarta() == null) {
            celda.setCarta(carta);
            cartasActivas.add(new CartaEnMapa(carta, x, y));

            // Crear una nueva unidad si es una tropa o estructura
            if (!carta.getTipo().equals("Hechizo")) {
                Unidad unidad = new Unidad(carta, x, y);
                unidades.add(unidad);
                System.out.println("Nueva unidad creada: " + carta.getNombre() + " en [" + x + "," + y + "]");
            }

            return true;
        }

        return false;
    }

    public List<CartaEnMapa> getCartasActivas() {
        return cartasActivas;
    }

    public static class CartaEnMapa {
        private Carta carta;
        private int x, y;

        public CartaEnMapa(Carta carta, int x, int y) {
            this.carta = carta;
            this.x = x;
            this.y = y;
        }

        public Carta getCarta() {
            return carta;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
