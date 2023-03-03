import javax.swing.*;

public class RaceController {
    public static boolean finished;
    public static final int TRACK_LENGTH = 800;
    private Car[] cars;
    private CarController[] carControllers;
    private RaceTrack raceTrack;
    private TrackRefresher trackRefresher;
    private RaceFrame raceFrame;

    public RaceController(int n){
        cars = new Car[n];
        carControllers = new CarController[n];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car();
            carControllers[i] = new CarController(cars[i]);
        }
        raceTrack = new RaceTrack();
        raceTrack.setCars(cars);
        trackRefresher = new TrackRefresher(raceTrack);
        raceFrame = new RaceFrame(raceTrack, this);
        raceFrame.setBounds(10,10,1024,768);
        raceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        raceFrame.setVisible(true);
    }
    public void initRace(int n) {
        cars = new Car[n];
        carControllers = new CarController[n];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car();
            carControllers[i] = new CarController(cars[i]);
        }
        //raceTrack = new RaceTrack();
        raceTrack.setCars(cars);
        trackRefresher = new TrackRefresher(raceTrack);
        /*raceFrame = new RaceFrame(raceTrack, this);
        raceFrame.setBounds(10,10,1024,768);
        raceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        raceFrame.setVisible(true);*/
    }

    public void startRace(){
        for (int i = 0; i < carControllers.length; i++) {
            carControllers[i].start();
        }
        trackRefresher.start();
    }
}
