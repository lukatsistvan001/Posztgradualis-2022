package Feladat5;

import org.w3c.dom.css.Rect;

import java.awt.*;

public class Rectangle extends Shape implements Resize {

    private double base;
    private double height;

    public Rectangle(int xPosition, int yPosition, Color color, double base, double height) {
        super(xPosition, yPosition, color);
        this.base = base;
        this.height = height;
    }

    @Override
    public void resize(Dimension dimension) {
        this.base = dimension.length;
        this.height = dimension.width;
    }

    @Override
    public double calculatePerimeter() {
        return (2 * (this.base + this.height));
    }

    @Override
    public double calculateArea() {
        return (this.base * this.height);
    }

    public void frameSize() {
        System.out.println("Frame size of the rectangle: " + this.base + " x " + this.height);
    }
}