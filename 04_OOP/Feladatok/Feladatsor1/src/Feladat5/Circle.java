package Feladat5;

import java.awt.*;

public class Circle extends Shape implements Resize {

    private double radius;

    public Circle(int xPosition, int yPosition, Color color, double radius) {
        super(xPosition, yPosition, color);
        this.radius = radius;
    }

    @Override
    public void resize(Dimension dimension) {
        if (dimension.length <= dimension.width) {
            this.radius = dimension.length / 2;
        } else {
            this.radius = dimension.width / 2;
        }
    }

    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public double calculateArea() {
        return radius * radius * Math.PI;
    }

    public void frameSize() {
        System.out.println("Frame size of the circle: " + radius * 2 + " x " + radius * 2);
    }
}