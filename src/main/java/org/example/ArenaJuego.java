package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ArenaJuego extends JFrame {
    private static final int GRID_WIDTH = 18;
    private static final int GRID_HEIGHT = 32;
    private static final int CELL_SIZE = 20;

    // Colores para las diferentes zonas
    private static final Color ZONA_JUGADOR_1 = new Color(255, 100, 100); // Rojo
    private static final Color ZONA_JUGADOR_2 = new Color(200, 200, 255); // Azul claro
    private static final Color RIO = new Color(100, 100, 255);            // Azul
    private static final Color PUENTES = new Color(100, 255, 100);        // Verde
    private static final Color TORRE_CENTRAL = new Color(128, 128, 128);  // Gris
    private static final Color TORRES_LATERALES = new Color(255, 100, 255); // Magenta
    private static final Color NEGRO = Color.BLACK;                       // Negro

    private int[][] grid;
    private JPanel gamePanel;
    private JLabel statusLabel;

    // Tipos de zona
    private static final int VACIO = 0;
    private static final int ZONA_J1 = 1;
    private static final int ZONA_J2 = 2;
    private static final int RIO_ZONA = 3;
    private static final int PUENTE = 4;
    private static final int TORRE_C = 5;
    private static final int TORRE_L = 6;
    private static final int NEGRO_ZONA = 7;

    public ArenaJuego() {
        setTitle("Arena de Juego");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        initializeGrid();
        setupUI();

        pack();
        setLocationRelativeTo(null);
    }

    private void initializeGrid() {
        grid = new int[GRID_HEIGHT][GRID_WIDTH];

        // Llenar todo con zona jugador 2 (azul claro) por defecto
        for (int y = 0; y < GRID_HEIGHT; y++) {
            for (int x = 0; x < GRID_WIDTH; x++) {
                grid[y][x] = ZONA_J2;
            }
        }

        // Zona Jugador 1 (parte inferior roja) - del Y=0 al Y=14
        for (int y = 0; y <= 14; y++) {
            for (int x = 0; x < GRID_WIDTH; x++) {
                grid[y][x] = ZONA_J1;
            }
        }

        // Río (franjas azules) - Y=15 y Y=16
        for (int y = 15; y <= 16; y++) {
            for (int x = 0; x < GRID_WIDTH; x++) {
                grid[y][x] = RIO_ZONA;
            }
        }

        // Puentes (verde) 1x2 - hay 2 puentes
        // Puente izquierdo (vertical 1x2)
        grid[15][3] = PUENTE;
        grid[16][3] = PUENTE;

        // Puente derecho (vertical 1x2)
        grid[15][14] = PUENTE;
        grid[16][14] = PUENTE;

        // Torres laterales (magenta) 3x3 en zona jugador 1
        // Torre izquierda inferior
        for (int y = 6; y <= 8; y++) {
            for (int x = 2; x <= 4; x++) {
                grid[y][x] = TORRE_L;
            }
        }
        // Torre derecha inferior
        for (int y = 6; y <= 8; y++) {
            for (int x = 13; x <= 15; x++) {
                grid[y][x] = TORRE_L;
            }
        }

        // Torres laterales 3x3 en zona jugador 2
        // Torre izquierda superior
        for (int y = 24; y <= 26; y++) {
            for (int x = 2; x <= 4; x++) {
                grid[y][x] = TORRE_L;
            }
        }
        // Torre derecha superior
        for (int y = 24; y <= 26; y++) {
            for (int x = 13; x <= 15; x++) {
                grid[y][x] = TORRE_L;
            }
        }

        // Torres centrales 4x4 (gris)
        // Torre central inferior
        for (int y = 2; y <= 5; y++) {
            for (int x = 7; x <= 10; x++) {
                grid[y][x] = TORRE_C;
            }
        }

        // Torre central superior
        for (int y = 27; y <= 30; y++) {
            for (int x = 7; x <= 10; x++) {
                grid[y][x] = TORRE_C;
            }
        }

        // Zonas negras 6x1 en las esquinas
        // Esquina inferior izquierda
        for (int x = 0; x <= 5; x++) {
            grid[0][x] = NEGRO_ZONA;
        }
        // Esquina inferior derecha
        for (int x = 12; x <= 17; x++) {
            grid[0][x] = NEGRO_ZONA;
        }
        // Esquina superior izquierda
        for (int x = 0; x <= 5; x++) {
            grid[31][x] = NEGRO_ZONA;
        }
        // Esquina superior derecha
        for (int x = 12; x <= 17; x++) {
            grid[31][x] = NEGRO_ZONA;
        }
    }

    private void setupUI() {
        setLayout(new BorderLayout());

        // Panel principal del juego
        gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawGrid(g);
            }
        };

        gamePanel.setPreferredSize(new Dimension(
                GRID_WIDTH * CELL_SIZE,
                GRID_HEIGHT * CELL_SIZE
        ));

        // Agregar listener para clicks del mouse
        gamePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleMouseClick(e.getX(), e.getY());
            }
        });

        // Label para mostrar información
        statusLabel = new JLabel("Click en una celda válida para obtener sus coordenadas");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(gamePanel, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);
    }

    private void drawGrid(Graphics g) {
        for (int y = 0; y < GRID_HEIGHT; y++) {
            for (int x = 0; x < GRID_WIDTH; x++) {
                Color cellColor = getCellColor(grid[y][x]);
                g.setColor(cellColor);
                g.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);

                // Dibujar bordes de las celdas
                g.setColor(Color.GRAY);
                g.drawRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
    }

    private Color getCellColor(int cellType) {
        switch (cellType) {
            case ZONA_J1: return ZONA_JUGADOR_1;
            case ZONA_J2: return ZONA_JUGADOR_2;
            case RIO_ZONA: return RIO;
            case PUENTE: return PUENTES;
            case TORRE_C: return TORRE_CENTRAL;
            case TORRE_L: return TORRES_LATERALES;
            case NEGRO_ZONA: return NEGRO;
            default: return Color.WHITE;
        }
    }

    private void handleMouseClick(int mouseX, int mouseY) {
        int gridX = mouseX / CELL_SIZE;
        int gridY = mouseY / CELL_SIZE;

        // Verificar que el click esté dentro de los límites
        if (gridX >= 0 && gridX < GRID_WIDTH && gridY >= 0 && gridY < GRID_HEIGHT) {
            // Invertir Y para que 0 esté en la parte inferior (como en la imagen)
            int gameY = GRID_HEIGHT - 1 - gridY;

            int cellType = grid[gridY][gridX];

            // Verificar si es una zona clickeable
            if (isClickableZone(cellType)) {
                String zoneName = getZoneName(cellType);
                statusLabel.setText(String.format(
                        "Coordenadas: X=%d, Y=%d | Zona: %s",
                        gridX, gameY, zoneName
                ));

                // También imprimir en consola
                System.out.println(String.format(
                        "Click válido en: X=%d, Y=%d | Zona: %s",
                        gridX, gameY, zoneName
                ));
            } else {
                statusLabel.setText("Zona no accesible - Click en una celda válida");
                System.out.println("Click en zona no accesible: " + getZoneName(cellType));
            }
        }
    }

    private boolean isClickableZone(int cellType) {
        // Solo las zonas de jugadores y los puentes son clickeables
        return cellType == ZONA_J1 || cellType == ZONA_J2 || cellType == PUENTE;
    }

    private String getZoneName(int cellType) {
        switch (cellType) {
            case ZONA_J1: return "Zona Jugador 1";
            case ZONA_J2: return "Zona Jugador 2";
            case RIO_ZONA: return "Río";
            case PUENTE: return "Puente";
            case TORRE_C: return "Torre Central";
            case TORRE_L: return "Torre Lateral";
            case NEGRO_ZONA: return "Obstáculo";
            default: return "Vacío";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ArenaJuego().setVisible(true);
        });
    }
}