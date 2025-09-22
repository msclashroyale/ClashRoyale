package com.mycompany.clashroyale.ui;

import com.mycompany.clashroyale.model.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.List;

public class ClashRoyaleFrame extends JFrame {

    private Partida partida;
    private Timer timer;

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
        // JPanel gridJugador1 = construirPanelMazo(partida.getJugador1().getMano());
        // root.add(gridJugador1, BorderLayout.SOUTH);

        // Mazo Jugador 2 arriba
        // JPanel gridJugador2 = construirPanelMazo(partida.getJugador2().getMano());
        // root.add(gridJugador2, BorderLayout.NORTH);

        iniciarSimulacion();
    }

    // private JPanel construirPanelMazo(List<Carta> cartas) {
    // JPanel grid = new JPanel(new GridLayout(1, 8, 10, 10)); // Mazo de 8 cartas
    // grid.setOpaque(false);

    // for (Carta carta : cartas) {
    // JPanel cartaPanel = construirPanelCarta(carta);
    // grid.add(cartaPanel);
    // }

    // return grid;
    // }

    // private JPanel construirPanelCarta(Carta carta) {
    // JPanel cartaPanel = new JPanel(new BorderLayout());
    // cartaPanel.setOpaque(false);

    // // Imagen de carta
    // ImageIcon icono = cargarIconoFlexible(carta.getImagen());
    // JLabel lblImagen;
    // if (icono != null) {
    // Image img = icono.getImage().getScaledInstance(100, 140, Image.SCALE_SMOOTH);
    // lblImagen = new JLabel(new ImageIcon(img));
    // } else {
    // lblImagen = new JLabel("(sin imagen)", SwingConstants.CENTER);
    // lblImagen.setPreferredSize(new Dimension(100, 120));
    // lblImagen.setForeground(Color.WHITE);
    // }
    // lblImagen.setHorizontalAlignment(SwingConstants.CENTER);

    // cartaPanel.add(lblImagen, BorderLayout.CENTER);
    // return cartaPanel;
    // }

    /** Intenta cargar un ImageIcon desde classpath o disco. */
    private ImageIcon cargarIconoFlexible(String ruta) {
        if (ruta == null || ruta.isEmpty()) {
            System.err.println("Ruta de imagen nula o vacía");
            return null;
        }

        try {
            // 1) Intentar desde el directorio del proyecto
            String projectDir = System.getProperty("user.dir");
            File imageFile = new File(projectDir, ruta);

            if (imageFile.exists()) {
                System.out.println("Cargando imagen desde: " + imageFile.getAbsolutePath());
                ImageIcon icon = new ImageIcon(imageFile.getAbsolutePath());
                if (icon.getIconWidth() <= 0) {
                    System.err.println("Error: Imagen inválida en " + imageFile.getAbsolutePath());
                    return null;
                }
                return icon;
            }

            // 2) Intentar desde classpath
            URL url = getClass().getResource(ruta.startsWith("/") ? ruta : "/" + ruta);
            if (url != null) {
                System.out.println("Cargando imagen desde classpath: " + url);
                return new ImageIcon(url);
            }

            // 3) Intentar como ruta absoluta
            File absoluteFile = new File(ruta);
            if (absoluteFile.exists()) {
                System.out.println("Cargando imagen desde ruta absoluta: " + absoluteFile.getAbsolutePath());
                return new ImageIcon(absoluteFile.getAbsolutePath());
            }

            System.err.println("No se encontró la imagen: " + ruta);
            System.err.println("Rutas intentadas:");
            System.err.println(" - " + imageFile.getAbsolutePath());
            System.err.println(" - classpath:/" + ruta);
            System.err.println(" - " + absoluteFile.getAbsolutePath());

        } catch (Exception e) {
            System.err.println("Error al cargar imagen " + ruta + ": " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    /** Igual que arriba pero devuelve Image (útil para fondo). */
    private Image cargarImagenFlexible(String ruta) {
        ImageIcon icon = cargarIconoFlexible(ruta);
        return icon != null ? icon.getImage() : null;
    }

    /** Panel que dibuja un fondo escalado. */
    private class BackgroundPanel extends JPanel {
        private final Image background;

        BackgroundPanel(Image background) {
            this.background = background;
            setOpaque(true);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int cellWidth = getWidth() / Arena.ANCHO;
            int cellHeight = getHeight() / Arena.ALTO;

            Celda[][] grid = partida.getArena().getGrid();

            for (int y = 0; y < Arena.ALTO; y++) {
                for (int x = 0; x < Arena.ANCHO; x++) {
                    Celda celda = grid[y][x];
                    int px = x * cellWidth;
                    int py = getHeight() - (y + 1) * cellHeight; // invertir Y

                    // Fondo según tipo
                    switch (celda.getTipo()) {
                        case RIO -> g.setColor(Color.CYAN);
                        case PUENTE -> g.setColor(Color.ORANGE);
                        case TORRE_PRINCESA, TORRE_REY -> g.setColor(Color.GRAY);
                        default -> g.setColor(Color.DARK_GRAY);
                    }
                    g.fillRect(px, py, cellWidth, cellHeight);
                    g.setColor(Color.BLACK);
                    g.drawRect(px, py, cellWidth, cellHeight);

                    // Carta
                    if (celda.getCarta() != null) {
                        String rutaImagen = celda.getCarta().getImagen();
                        ImageIcon icono = cargarIconoFlexible(rutaImagen);
                        if (icono != null) {
                            Image img = icono.getImage();
                            g.drawImage(img,
                                    px + 2, py + 2,
                                    cellWidth - 4, cellHeight - 4,
                                    this);
                        } else {
                            System.err.println("No se pudo cargar imagen: " + rutaImagen);
                            g.setColor(Color.MAGENTA);
                            g.fillRect(px + 2, py + 2, cellWidth - 4, cellHeight - 4);
                        }
                    }
                }
            }
        }
    }

    public void iniciarSimulacion() {
        timer = new Timer(1000, e -> tick());
        timer.start();
    }

    private void tick() {
        if (!partida.isEnCurso()) {
            timer.stop();
            return;
        }

        partida.reducirTiempo(1);

        Jugador j1 = partida.getJugador1();
        Jugador j2 = partida.getJugador2();

        // Recargar elixir
        j1.ganarElixir(1); // 1 de elixir por segundo
        j2.ganarElixir(1);

        // Jugador 1 (parte inferior)
        int x1 = (int) (Math.random() * Arena.ANCHO);
        int y1 = (int) (Math.random() * (Arena.ALTO / 2 - 2)); // hasta Y=14
        j1.respawnearCarta(partida.getArena(), x1, y1);

        // Jugador 2 (parte superior)
        int x2 = (int) (Math.random() * Arena.ANCHO);
        int y2 = (Arena.ALTO / 2) + (int) (Math.random() * (Arena.ALTO / 2 - 2)); // desde Y=17
        j2.respawnearCarta(partida.getArena(), x2, y2);

        // Actualizar todas las unidades en la arena
        partida.getArena().actualizarUnidades();

        repaint();
    }

}
