package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== ARENA DE JUEGO - PRUEBA ===");
        System.out.println("Instrucciones:");
        System.out.println("1. Haz click en cualquier celda del grid");
        System.out.println("2. Las coordenadas aparecerán en la parte inferior");
        System.out.println("3. También se mostrarán en la consola");
        System.out.println("4. El sistema de coordenadas tiene (0,0) en la esquina inferior izquierda");
        System.out.println("=====================================\n");

        // Ejecutar la aplicación
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    // Crear y mostrar la ventana del juego
                    ArenaJuego arena = new ArenaJuego();
                    arena.setVisible(true);

                    System.out.println("Arena de juego iniciada correctamente.");
                    System.out.println("¡Haz click en las celdas para probar!");

                } catch (Exception e) {
                    System.err.println("Error al iniciar la arena de juego: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        });
    }
}