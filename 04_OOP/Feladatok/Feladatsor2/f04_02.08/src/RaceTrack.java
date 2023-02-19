import javax.swing.*;
import java.awt.*;

public class RaceTrack extends JPanel {
    private Car[] cars;

    public RaceTrack() {
        setBackground(Color.LIGHT_GRAY);
    }

    public void setCars(Car[] cars) {
        this.cars = cars;
    }

    @Override
    public void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        gr.setColor(Color.RED);
        for (int i = 0; i < cars.length; i++) {
            gr.fillRect(cars[i].getX(), i * 20, 20, 10);
        }
    }
}
