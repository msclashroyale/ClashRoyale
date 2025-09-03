package com.mycompany.clashroyale.model;

public class Carta {
    private String nombre;
    private int costoElixir;
    private String tipo;
    private int danio;
    private int vida;
    private String rango;
    private String velocidad;
    private String objetivo;
    private String area;
    private int dañoPorSegundo;
    private String imagen;

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
    public String getNombre() { return nombre; }
    public String getImagen() { return imagen; }
}
