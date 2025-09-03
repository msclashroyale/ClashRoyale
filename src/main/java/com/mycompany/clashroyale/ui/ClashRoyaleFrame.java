package com.mycompany.clashroyale.ui;

import com.mycompany.clashroyale.model.Baraja;
import com.mycompany.clashroyale.model.Carta;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.List;

public class ClashRoyaleFrame extends JFrame {

    public ClashRoyaleFrame(Baraja baraja) {
        setTitle("Clash Royale - Baraja Aleatoria");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 1) Cargar imagen de fondo (primero classpath, luego disco)
        //    Cambiá el nombre si tu archivo es otro (ideal: PNG/JPG)
        Image fondoArena = cargarImagenFlexible("/imagenes/arena.jpg"); // <-- poné tu archivo acá
        BackgroundPanel root = new BackgroundPanel(fondoArena);
        root.setLayout(new BorderLayout());
        setContentPane(root);

        // 2) Grid 2x4 transparente
        JPanel grid = new JPanel(new GridLayout(2, 4, 10, 10));
        grid.setOpaque(false);
        grid.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        List<Carta> cartas = baraja.getCartas();
        for (Carta carta : cartas) {
            JPanel cartaPanel = construirPanelCarta(carta);
            grid.add(cartaPanel);
        }

        root.add(grid, BorderLayout.CENTER);
    }

    private JPanel construirPanelCarta(Carta carta) {
        JPanel cartaPanel = new JPanel(new BorderLayout());
        cartaPanel.setOpaque(false);

        // 3) Cargar imagen de la carta (ruta puede ser classpath o disco)
        ImageIcon icono = cargarIconoFlexible(carta.getImagen());
        JLabel lblImagen;
        if (icono != null) {
            Image img = icono.getImage().getScaledInstance(150, 200, Image.SCALE_SMOOTH);
            lblImagen = new JLabel(new ImageIcon(img));
        } else {
            // Placeholder si no se pudo cargar
            lblImagen = new JLabel("(sin imagen)", SwingConstants.CENTER);
            lblImagen.setPreferredSize(new Dimension(150, 200));
            lblImagen.setForeground(Color.WHITE);
        }
        lblImagen.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lblNombre = new JLabel(carta.getNombre(), SwingConstants.CENTER);
        lblNombre.setForeground(Color.WHITE);
        lblNombre.setFont(lblNombre.getFont().deriveFont(Font.BOLD, 16f));

        // Opcional: marco suave
        cartaPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(255,255,255,100), 1),
                BorderFactory.createEmptyBorder(6,6,6,6)
        ));

        cartaPanel.add(lblImagen, BorderLayout.CENTER);
        cartaPanel.add(lblNombre, BorderLayout.SOUTH);
        return cartaPanel;
    }

    /** Intenta cargar un ImageIcon desde classpath o disco (relativo a user.dir o absoluto). */
    private ImageIcon cargarIconoFlexible(String ruta) {
        if (ruta == null || ruta.isEmpty()) return null;

        // 1) classpath
        URL url = getClass().getResource(ruta.startsWith("/") ? ruta : "/" + ruta);
        if (url != null) return new ImageIcon(url);

        // 2) disco: si la ruta no es absoluta, la arma con user.dir
        File f = new File(ruta);
        if (!f.isAbsolute()) f = new File(System.getProperty("user.dir"), ruta);
        if (f.exists()) return new ImageIcon(f.getAbsolutePath());

        System.err.println("No se encontró la imagen: " + ruta);
        return null;
    }

    /** Igual que arriba pero devuelve Image (útil para el fondo). */
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
                // Color de respaldo si no hay imagen
                g.setColor(new Color(25, 30, 45));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        }
    }
}
