package src;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MapPanel extends JPanel {
    private final int gridSize = 10; // Taille de la grille (10x10)
    private final int cellSize = 50; // Taille d'une cellule
    private final List<Personne> personnes; // Liste des personnes

    public MapPanel(List<Personne> personnes) {
        this.personnes = personnes;
        setPreferredSize(new Dimension(gridSize * cellSize, gridSize * cellSize));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Dessiner le quadrillage
        g2d.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i <= gridSize; i++) {
            g2d.drawLine(i * cellSize, 0, i * cellSize, gridSize * cellSize); // Lignes verticales
            g2d.drawLine(0, i * cellSize, gridSize * cellSize, i * cellSize); // Lignes horizontales
        }

        // Dessiner les points des personnes
        for (Personne p : personnes) {
            switch (p.getEtat().toString()) {
                case "Sain":
                    g2d.setColor(Color.GREEN);
                    break;
                case "Malade":
                    g2d.setColor(Color.RED);
                    break;
                case "Gueri":
                    g2d.setColor(Color.BLUE);
                    break;
                case "Contagieux":
                    g2d.setColor(Color.ORANGE);
                    break;
                case "Decede":
                    g2d.setColor(Color.BLACK);
                    break;
            }

            // Calculer la position
            int x = (int) (p.getX() * cellSize + cellSize / 4);
            int y = (int) (p.getY() * cellSize + cellSize / 4);

            // Dessiner le cercle
            g2d.fillOval(x, y, cellSize / 2, cellSize / 2);
        }
    }
}
