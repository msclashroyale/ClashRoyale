package com.mycompany.clashroyale;

import com.mycompany.clashroyale.model.Carta;
import com.mycompany.clashroyale.model.Baraja;
import com.mycompany.clashroyale.ui.ClashRoyaleFrame;

import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;

public class ClashRoyale {
    public static void main(String[] args) {
        List<Carta> todasLasCartas = new ArrayList<>();

        // Ejemplo con imágenes de placeholder
        todasLasCartas.add(new Carta("Gigante", 5, "Tropa", 120, 2000, "Cuerpo a cuerpo", "Lenta", "Estructuras", "Individual", 80, "imagenes/Giant.png"));
        todasLasCartas.add(new Carta("Caballero", 3, "Tropa", 75, 660, "Cuerpo a cuerpo", "Media", "T", "Individual", 75, "imagenes/Knight.png"));
        todasLasCartas.add(new Carta("Valquiria", 4, "Tropa", 120, 880, "Cuerpo a cuerpo", "Media", "T", "Área", 120, "imagenes/Valk.png"));
        todasLasCartas.add(new Carta("Mini P.E.K.K.A", 4, "Tropa", 340, 600, "Cuerpo a cuerpo", "Lenta", "T", "Individual", 226, "imagenes/MP.png"));
        todasLasCartas.add(new Carta("Príncipe", 5, "Tropa", 220, 1100, "Cuerpo a cuerpo", "Media", "T", "Individual", 146, "imagenes/Prince.png"));
        todasLasCartas.add(new Carta("P.E.K.K.A", 7, "Tropa", 510, 2600, "Cuerpo a cuerpo", "Lenta", "T", "Individual", 255, "imagenes/PEKKA.png"));
        todasLasCartas.add(new Carta("Mosquetera", 4, "Tropa", 100, 340, "6", "Media", "T+A", "Individual", 91, "imagenes/Musk.png"));
        todasLasCartas.add(new Carta("Mago", 5, "Tropa", 130, 340, "5", "Media", "T+A", "Área", 65, "imagenes/Wiz.png"));
        todasLasCartas.add(new Carta("Mago de Hielo", 3, "Tropa", 63, 590, "5", "Media", "T+A", "Área", 42, "imagenes/Mago_de_hielo.png"));
        todasLasCartas.add(new Carta("Arqueras", 3, "Tropa", 33, 125, "5", "Media", "T+A", "Individual", 41, "imagenes/Arqueras.png"));
        todasLasCartas.add(new Carta("Bárbaros", 5, "Tropa", 75, 300, "Cuerpo a cuerpo", "Media", "T", "Individual", 62, "imagenes/Barbs.png"));
        todasLasCartas.add(new Carta("Ejército de Esqueletos", 3, "Tropa", 51, 51, "Cuerpo a cuerpo", "Rápida", "T", "Individual", 51, "imagenes/Skarmy.png"));
        todasLasCartas.add(new Carta("Bebé Dragón", 4, "Tropa", 100, 800, "3.5", "Media", "T+A", "Área", 62, "imagenes/BabyD.png"));
        todasLasCartas.add(new Carta("Globo Bombástico", 5, "Tropa", 600, 1050, "Cuerpo a cuerpo", "Lenta", "Estructuras", "Individual", 200, "imagenes/Balloon.png"));
        todasLasCartas.add(new Carta("Cañón", 3, "Estructura", 75, 740, "5.5", "Estático", "T", "Individual", 75, "imagenes/Cannon.png"));
        todasLasCartas.add(new Carta("Tesla", 4, "Estructura", 80, 800, "5.5", "Estático", "T+A", "Individual", 80, "imagenes/Tesla.png"));
        todasLasCartas.add(new Carta("Mortero", 4, "Estructura", 120, 600, "11.5", "Estático", "T", "Área", 40, "imagenes/Mortar.png"));
        todasLasCartas.add(new Carta("Torre Infernal", 5, "Estructura", -1, 950, "6", "Estático", "T+A", "Individual", -1, "imagenes/torreinfernal.png"));
        todasLasCartas.add(new Carta("Ballesta", 6, "Estructura", 26, 850, "11.5", "Estático", "T", "Individual", 60, "imagenes/ballesta.png"));
        todasLasCartas.add(new Carta("Choza de Duendes", 5, "Estructura", -1, 1100, "-", "Estático", "Área", "Invocación", -1, "imagenes/chozaduendes.png"));
        todasLasCartas.add(new Carta("Flechas", 3, "Hechizo", 144, -1, "Pantalla", "-", "T+A", "Área", -1, "imagenes/flechas.png"));
        todasLasCartas.add(new Carta("Bola de Fuego", 4, "Hechizo", 325, -1, "2.5", "-", "T+A", "Área", -1, "imagenes/boladefuego.png"));
        todasLasCartas.add(new Carta("Cohete", 6, "Hechizo", 700, -1, "2", "-", "T+A", "Área", -1, "imagenes/cohete.png"));
        todasLasCartas.add(new Carta("Descarga (Zap)", 2, "Hechizo", 75, -1, "2.5", "-", "T+A", "Área", -1, "imagenes/Zap.png"));


        Baraja baraja = new Baraja(todasLasCartas);

        SwingUtilities.invokeLater(() -> {
            ClashRoyaleFrame frame = new ClashRoyaleFrame(baraja);
            frame.setVisible(true);
        });
    }
}
