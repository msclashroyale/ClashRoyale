/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clashroyale.model;

/**
 *
 * @author matia
 */
public class Zona {
    private int xMin, yMin, xMax, yMax;

    public Zona(int xMin, int yMin, int xMax, int yMax) {
        this.xMin = xMin;
        this.yMin = yMin;
        this.xMax = xMax;
        this.yMax = yMax;
    }

    public boolean contiene(double x, double y) {
        return x >= xMin && x <= xMax && y >= yMin && y <= yMax;
    }
}

