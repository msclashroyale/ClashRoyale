/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clashroyale.model;

public class Celda {
    private int x, y;
    private TipoCelda tipo;
    private Carta carta; // si hay carta

    public Celda(int x, int y, TipoCelda tipo) {
        this.x = x;
        this.y = y;
        this.tipo = tipo;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public TipoCelda getTipo() { return tipo; }
    public void setTipo(TipoCelda tipo) { this.tipo = tipo; }

    public Carta getCarta() { return carta; }
    public void setCarta(Carta carta) { this.carta = carta; }
}


