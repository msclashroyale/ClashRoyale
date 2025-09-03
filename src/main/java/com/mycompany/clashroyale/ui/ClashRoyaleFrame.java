package com.mycompany.clashroyale.ui;

import com.mycompany.clashroyale.model.Baraja;
import com.mycompany.clashroyale.model.Carta;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ClashRoyaleFrame extends JFrame {

    public ClashRoyaleFrame(Baraja baraja) {
        setTitle("Clash Royale - Baraja Aleatoria");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel con GridLayout 2x4
        JPanel panel = new JPanel(new GridLayout(2, 4, 10, 10));
        List<Carta> cartas = baraja.getCartas();

        for (Carta carta : cartas) {
            JPanel cartaPanel = new JPanel(new BorderLayout());

            // Imagen
            ImageIcon icono = new ImageIcon(carta.getImagen());
            Image img = icono.getImage().getScaledInstance(150, 200, Image.SCALE_SMOOTH);
            JLabel lblImagen = new JLabel(new ImageIcon(img));

            // Nombre
            JLabel lblNombre = new JLabel(carta.getNombre(), SwingConstants.CENTER);

            cartaPanel.add(lblImagen, BorderLayout.CENTER);
            cartaPanel.add(lblNombre, BorderLayout.SOUTH);

            panel.add(cartaPanel);
        }

        add(panel);
    }
}
