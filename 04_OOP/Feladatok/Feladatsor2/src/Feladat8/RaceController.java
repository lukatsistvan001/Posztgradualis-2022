package Feladat8;

import javax.swing.*;

public class RaceController {

    public static boolean finished;
    public static final int TRACK_LENGHT = 800;
    private Car[] cars;
    private CarController[] carController;
    private RaceTrack raceTrack;
    private TrackRefresher trackRefresher;
    private RaceFrame raceFrame;

    public RaceController(int n) {
        cars = new Car[n];
        carController = new CarController[n];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car();
            carController[i] = new CarController(cars[i]);
        }
        raceTrack = new RaceTrack();
        raceTrack.setCars(cars);
        trackRefresher = new TrackRefresher(raceTrack);
        raceFrame = new RaceFrame(raceTrack, this);
        raceFrame.setBounds(10, 10, 1024, 768);
        raceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        raceFrame.setVisible(true);
    }

    public void initRace(int n) {
        cars = new Car[n];
        carController = new CarController[n];
        for (int i = 0; i < n; i++) {
            cars[i] = new Car();
            carController[i] = new CarController(cars[i]);
        }
        raceTrack.setCars(cars);
        trackRefresher = new TrackRefresher(raceTrack);
    }

    public void startRace() {
        for (int i = 0; i < carController.length; i++) {
            carController[i].start();
        }
        trackRefresher.start();
    }
}
