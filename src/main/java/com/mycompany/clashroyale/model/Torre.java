/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clashroyale.model;


public class Torre {
    private String nombre;
    private double x, y;
    private int vida;
    private int dano;
    private double velocidadAtaque;
    private double rango;

    public Torre(String nombre, double x, double y, int vida, int dano, double velocidadAtaque, double rango) {
        this.nombre = nombre;
        this.x = x;
        this.y = y;
        this.vida = vida;
        this.dano = dano;
        this.velocidadAtaque = velocidadAtaque;
        this.rango = rango;
    }
}
