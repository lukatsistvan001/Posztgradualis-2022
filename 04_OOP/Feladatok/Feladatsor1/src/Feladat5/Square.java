package Feladat5;

import java.awt.*;

public class Square extends Shape implements Resize {

    private double side;

    public Square(int xPozition, int yPosiion, Color color, double side) {
        super(xPozition, yPosiion, color);
        this.side = side;
    }

    @Override
    public void resize(Dimension dimension) {
        if (dimension.length <= dimension.width) {
            this.side = dimension.length;
        } else {
            this.side = dimension.width;
        }
    }

    @Override
    public double calculatePerimeter() {
        return 4 * side;
    }

    @Override
    public double calculateArea() {
        return side * side;
    }

    public void frameSize() {
        System.out.println("Frame size of the square: " + side + " x " + side);
    }
}
