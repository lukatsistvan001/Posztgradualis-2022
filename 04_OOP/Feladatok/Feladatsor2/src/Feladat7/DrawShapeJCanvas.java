package Feladat7;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.GenericArrayType;

public class DrawShapeJCanvas extends JPanel {

    private String shape;
    private int zoom;
    private boolean filled;
    private Color color;

    public DrawShapeJCanvas() {
        shape = "circle";
        zoom = 50;
        filled = false;
        color = Color.BLUE;
        setBackground(Color.WHITE);
    }

    public void refresh(String shape, int zoom, boolean filled, Color color) {
        this.shape = shape;
        this.zoom = zoom;
        this.filled = filled;
        this.color = color;
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(zoom * 12, zoom * 12);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        switch (shape) {
            case "circle":
                if (filled) {
                    g.fillOval(getWidth() / 2 - zoom * 4, getHeight() / 2 - zoom * 4, zoom * 8, zoom * 8);
                } else {
                    g.drawOval(getWidth() / 2 - zoom * 4, getHeight() / 2 - zoom * 4, zoom * 8, zoom * 8);
                }
                break;
            case "square":
                if (filled) {
                    g.fillRect(getWidth() / 2 - zoom * 4, getHeight() / 2 - zoom * 4, zoom * 8, zoom * 8);
                } else {
                    g.drawRect(getWidth() / 2 - zoom * 4, getHeight() / 2 - zoom * 4, zoom * 8, zoom * 8);
                }
                break;
        }
    }
}