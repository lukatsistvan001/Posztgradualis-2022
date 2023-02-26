package Feladat5;

import java.awt.*;

public abstract class Shape {

    private int xPosition;
    private int yPosition;
    private Color color;

    public Shape(int xPosition, int yPosition, Color color) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.color = color;
    }

    public abstract double calculatePerimeter();

    public abstract double calculateArea();
}