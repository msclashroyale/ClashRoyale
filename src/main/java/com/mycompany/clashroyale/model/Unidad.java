package com.mycompany.clashroyale.model;

public class Unidad {
    private Carta carta;
    private int x, y;
    private int vida;
    private int velocidadMovimiento;
    private double velocidadAtaque;
    private long ultimoAtaque;
    private Unidad objetivo;
    private Estado estado;

    public enum Estado {
        BUSCANDO_OBJETIVO,
        MOVIENDO_A_OBJETIVO,
        ATACANDO,
        MUERTO
    }

    public Unidad(Carta carta, int x, int y) {
        this.carta = carta;
        this.x = x;
        this.y = y;
        this.vida = carta.getVida();
        this.velocidadMovimiento = convertirVelocidad(carta.getVelocidad());
        this.velocidadAtaque = 1.0; // Ataques por segundo, ajustar según carta
        this.estado = Estado.BUSCANDO_OBJETIVO;
    }

    private int convertirVelocidad(String velocidad) {
        return switch (velocidad.toLowerCase()) {
            case "muy lenta" -> 1;
            case "lenta" -> 2;
            case "media" -> 3;
            case "rápida" -> 4;
            case "muy rápida" -> 5;
            default -> 2;
        };
    }

    public void actualizar(Arena arena) {
        if (estado == Estado.MUERTO)
            return;

        switch (estado) {
            case BUSCANDO_OBJETIVO -> buscarObjetivo(arena);
            case MOVIENDO_A_OBJETIVO -> moverHaciaObjetivo();
            case ATACANDO -> atacar();
        }
    }

    private void buscarObjetivo(Arena arena) {
        // Buscar objetivo más cercano según preferencias de la carta
        Unidad mejorObjetivo = null;
        double distanciaMinima = Double.MAX_VALUE;

        for (Unidad unidad : arena.getUnidades()) {
            if (unidad == this || !esObjetivoValido(unidad))
                continue;

            double distancia = calcularDistancia(unidad);
            if (distancia < distanciaMinima) {
                distanciaMinima = distancia;
                mejorObjetivo = unidad;
            }
        }

        if (mejorObjetivo != null) {
            objetivo = mejorObjetivo;
            estado = Estado.MOVIENDO_A_OBJETIVO;
        }
    }

    private boolean esObjetivoValido(Unidad unidad) {
        String tipoObjetivo = carta.getObjetivo().toLowerCase();
        if (unidad.getVida() <= 0)
            return false;

        return switch (tipoObjetivo) {
            case "t" -> true; // Ataca cualquier tropa
            case "t+a" -> true; // Ataca tropas y aire
            case "estructuras" -> unidad.getCarta().getTipo().equals("Estructura");
            default -> false;
        };
    }

    private void moverHaciaObjetivo() {
        if (objetivo == null || objetivo.getVida() <= 0) {
            estado = Estado.BUSCANDO_OBJETIVO;
            return;
        }

        double distancia = calcularDistancia(objetivo);
        double rangoAtaque = obtenerRangoAtaque();

        if (distancia <= rangoAtaque) {
            estado = Estado.ATACANDO;
            return;
        }

        // Mover hacia el objetivo
        double dx = objetivo.getX() - x;
        double dy = objetivo.getY() - y;
        double angulo = Math.atan2(dy, dx);

        x += (int) (Math.cos(angulo) * velocidadMovimiento);
        y += (int) (Math.sin(angulo) * velocidadMovimiento);
    }

    private void atacar() {
        if (objetivo == null || objetivo.getVida() <= 0) {
            estado = Estado.BUSCANDO_OBJETIVO;
            return;
        }

        long ahora = System.currentTimeMillis();
        if (ahora - ultimoAtaque >= (1000 / velocidadAtaque)) {
            objetivo.recibirDaño(carta.getDanio());
            ultimoAtaque = ahora;

            if (objetivo.getVida() <= 0) {
                estado = Estado.BUSCANDO_OBJETIVO;
                objetivo = null;
            }
        }
    }

    public void recibirDaño(int cantidad) {
        vida -= cantidad;
        if (vida <= 0) {
            estado = Estado.MUERTO;
            vida = 0;
        }
    }

    private double calcularDistancia(Unidad otra) {
        int dx = otra.getX() - x;
        int dy = otra.getY() - y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    private double obtenerRangoAtaque() {
        try {
            return Double.parseDouble(carta.getRango().replace(",", "."));
        } catch (NumberFormatException e) {
            return carta.getRango().toLowerCase().equals("cuerpo a cuerpo") ? 1.0 : 5.0;
        }
    }

    // Getters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getVida() {
        return vida;
    }

    public Carta getCarta() {
        return carta;
    }

    public Estado getEstado() {
        return estado;
    }
}