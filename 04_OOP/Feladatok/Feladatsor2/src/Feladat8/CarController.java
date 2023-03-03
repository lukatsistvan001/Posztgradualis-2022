package Feladat8;

import java.util.Random;

public class CarController extends Thread {

    private Car car;
    private Random random;

    public CarController(Car car) {
        this.car = car;
        this.random = new Random();
    }

    @Override
    public void run() {
        while (!RaceController.finished && car.getPosition() < RaceController.TRACK_LENGHT) {
            car.setPosition(car.getPosition() + random.nextInt(5));
            try {
                Thread.sleep(random.nextInt(10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (car.getPosition() >= RaceController.TRACK_LENGHT) {
            RaceController.finished = true;
        }
    }
}
