package com.mycompany.clashroyale.ui;

import com.mycompany.clashroyale.model.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.List;

public class ClashRoyaleFrame extends JFrame {

    private Partida partida;

    public ClashRoyaleFrame(Partida partida) {
        this.partida = partida;

        setTitle("Clash Royale");
        setSize(720, 1280);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 1) Fondo de arena
        Image fondoArena = cargarImagenFlexible("/imagenes/arena.png");
        BackgroundPanel root = new BackgroundPanel(fondoArena);
        root.setLayout(new BorderLayout());
        setContentPane(root);

        // 2) Mostrar el mazo del Jugador 1 (ejemplo)
        JPanel gridJugador1 = construirPanelMazo(partida.getJugador1().getMano());
        root.add(gridJugador1, BorderLayout.SOUTH);

        // Mazo Jugador 2 arriba
        JPanel gridJugador2 = construirPanelMazo(partida.getJugador2().getMano());
        root.add(gridJugador2, BorderLayout.NORTH);

        // ⚡ Si quisieras mostrar también el mazo del Jugador 2:
        // JPanel grid2 = construirPanelMazo(partida.getJugador2().getMazo());
        // root.add(grid2, BorderLayout.NORTH);
    }

    private JPanel construirPanelMazo(List<Carta> cartas) {
        JPanel grid = new JPanel(new GridLayout(1, 8, 10, 10)); // Mazo de 8 cartas
        grid.setOpaque(false);

        for (Carta carta : cartas) {
            JPanel cartaPanel = construirPanelCarta(carta);
            grid.add(cartaPanel);
        }

        return grid;
    }

    private JPanel construirPanelCarta(Carta carta) {
        JPanel cartaPanel = new JPanel(new BorderLayout());
        cartaPanel.setOpaque(false);

        // Imagen de carta
        ImageIcon icono = cargarIconoFlexible(carta.getImagen());
        JLabel lblImagen;
        if (icono != null) {
            Image img = icono.getImage().getScaledInstance(100, 140, Image.SCALE_SMOOTH);
            lblImagen = new JLabel(new ImageIcon(img));
        } else {
            lblImagen = new JLabel("(sin imagen)", SwingConstants.CENTER);
            lblImagen.setPreferredSize(new Dimension(80, 120));
            lblImagen.setForeground(Color.WHITE);
        }
        lblImagen.setHorizontalAlignment(SwingConstants.CENTER);

        cartaPanel.add(lblImagen, BorderLayout.CENTER);
        return cartaPanel;
    }

    /** Intenta cargar un ImageIcon desde classpath o disco. */
    private ImageIcon cargarIconoFlexible(String ruta) {
        if (ruta == null || ruta.isEmpty()) return null;

        // 1) classpath
        URL url = getClass().getResource(ruta.startsWith("/") ? ruta : "/" + ruta);
        if (url != null) return new ImageIcon(url);

        // 2) disco
        File f = new File(ruta);
        if (!f.isAbsolute()) f = new File(System.getProperty("user.dir"), ruta);
        if (f.exists()) return new ImageIcon(f.getAbsolutePath());

        System.err.println("No se encontró la imagen: " + ruta);
        return null;
    }

    /** Igual que arriba pero devuelve Image (útil para fondo). */
    private Image cargarImagenFlexible(String ruta) {
        ImageIcon icon = cargarIconoFlexible(ruta);
        return icon != null ? icon.getImage() : null;
    }

    /** Panel que dibuja un fondo escalado. */
    private static class BackgroundPanel extends JPanel {
        private final Image background;

        BackgroundPanel(Image background) {
            this.background = background;
            setOpaque(true);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (background != null) {
                g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
            } else {
                g.setColor(new Color(25, 30, 45));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        }
    }
}
