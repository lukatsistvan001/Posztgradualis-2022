package Feladat6WithTextFields;

import java.awt.*;

public class DrawShapeCanvas extends Canvas {

    private String shape;
    private int redColor;
    private int greenColor;
    private int blueColor;
    private boolean filled;

    public DrawShapeCanvas() {
        setBackground(Color.WHITE);
        shape = "circle";
        redColor = 0;
        greenColor = 0;
        blueColor = 0;
        filled = false;
    }

    public void refresh(String shape, int redColor, int greenColor, int blueColor, boolean filled) {
        this.shape = shape;
        this.redColor = redColor;
        this.greenColor = greenColor;
        this.blueColor = blueColor;
        this.filled = filled;
        repaint();
    }

    @Override
    public void paint(Graphics graphics) {
        Color color = new Color(redColor, greenColor, blueColor);
        graphics.setColor(color);
        switch (shape) {
            case "circle":
                if (filled) {
                    graphics.fillOval(getWidth() / 2 - 100, getHeight() / 2 - 100, 200, 200);
                } else {
                    graphics.drawOval(getWidth() / 2 - 100, getHeight() / 2 - 100, 200, 200);
                }
                break;
            case "square":
                if (filled) {
                    graphics.fillRect(getWidth() / 2 - 100, getHeight() / 2 - 100, 200, 200);
                } else {
                    graphics.drawRect(getWidth() / 2 - 100, getHeight() / 2 - 100, 200, 200);
                }
                break;
        }
    }
}