package com.mycompany.clashroyale.model;

public class Carta {
    private String nombre;
    private int costoElixir;
    private String tipo; // "Tropa", "Estructura", "Hechizo"
    private int danio; // Daño por golpe
    private int vida; // Puntos de vida
    private String rango; // "Cuerpo a cuerpo" o valor numérico
    private String velocidad; // "Muy lenta", "Lenta", "Media", "Rápida", "Muy rápida"
    private String objetivo; // "T" (tierra), "T+A" (tierra y aire), "Estructuras"
    private String area; // "Individual" o "Área"
    private int dañoPorSegundo;
    private String imagen;

    // Getters para los nuevos campos
    public int getDanio() {
        return danio;
    }

    public int getVida() {
        return vida;
    }

    public String getRango() {
        return rango;
    }

    public String getVelocidad() {
        return velocidad;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public String getTipo() {
        return tipo;
    }

    // Constructor con todos los parámetros
    public Carta(String nombre, int costoElixir, String tipo, int danio, int vida,
            String rango, String velocidad, String objetivo, String area, int dañoPorSegundo, String imagen) {
        this.nombre = nombre;
        this.costoElixir = costoElixir;
        this.tipo = tipo;
        this.danio = danio;
        this.vida = vida;
        this.rango = rango;
        this.velocidad = velocidad;
        this.objetivo = objetivo;
        this.area = area;
        this.dañoPorSegundo = dañoPorSegundo;
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return nombre + " | " + tipo + " | Costo: " + costoElixir + " | Daño: " + danio;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public int getCostoElixir() {
        return costoElixir;
    }
}
