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
        while (!RaceController.finished && car.getX() < RaceController.TRACK_LENGTH) {
            car.setX(car.getX() + random.nextInt(5));
            try {
                Thread.sleep(random.nextInt(10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (car.getX() >= RaceController.TRACK_LENGTH) {
            RaceController.finished = true;
        }
    }
}
