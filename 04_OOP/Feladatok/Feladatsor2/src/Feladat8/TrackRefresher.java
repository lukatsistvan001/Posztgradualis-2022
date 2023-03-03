package Feladat8;

public class TrackRefresher extends Thread {

    private RaceTrack raceTrack;

    public TrackRefresher(RaceTrack raceTrack) {
        this.raceTrack = raceTrack;
    }

    @Override
    public void run() {
        while (!RaceController.finished) {
            raceTrack.repaint();
            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
