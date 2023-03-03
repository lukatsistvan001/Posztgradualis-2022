package Feladat8;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class RaceTrack extends JPanel {

    private Car[] cars;

    public RaceTrack() {
        setBackground(Color.LIGHT_GRAY);
    }

    public void setCars(Car[] cars) {
        this.cars = cars;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        Line2D finishLine = new Line2D.Float(RaceController.TRACK_LENGHT + 19, 0, RaceController.TRACK_LENGHT + 19, 700);
        graphics2D.draw(finishLine);
        g.setColor(Color.RED);
        for (int i = 0; i < cars.length; i++) {
            g.fillRect(cars[i].getPosition(), i * 20, 20, 10);
        }
    }
}