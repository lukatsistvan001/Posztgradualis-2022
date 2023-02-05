import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class Main {

    /**
     * A túraösvény teljes hossza a csúcsig.
     */
    private static double MaxDistance = 8500;

    /**
     * A csúcs magassága.
     */
    private static double MaxHeight = 1221;

    public static void main(String[] args) throws IOException {
        // van egy szép kilátó 4500 méterre a starttól
        double targetDistance = 4500;

        // a kilátóg magassága
        double panoramaHeight = calculateHeightForDistance(targetDistance);

        File forraskod = Paths.get("", "mountain.java").toFile();
        FileWriter fw = new FileWriter(forraskod);
        fw.write("/n/r//" + panoramaHeight);
        System.out.println(panoramaHeight);
    }

    /**
     * Mekkora magasságba érünk fel az ösvényen a megadott távolság legyaloglása után?
     *
     * @param distance A ösvényen megtett távolság.
     * @return Az út végén elért magasság.
     */
    private static double calculateHeightForDistance(double distance) {
        return calculateHeightForDistance(distance, 0, MaxDistance);
    }

    private static double calculateHeightForDistance(double distance, double start, double end) {
        if (end - start == 1)
            return start;
        double halfWay = start + (end - start) / 2;
        if (Math.round(getDistanceToReachAltitude(halfWay)) == distance)
            return Math.round(halfWay);
        if (getDistanceToReachAltitude(halfWay) < distance)
            return calculateHeightForDistance(distance, halfWay, end);
        else
            return calculateHeightForDistance(distance, start, halfWay);
    }

    /**
     * Megadja, hogy adott szintkülönbség eléréséhez az ösvényen mekkora távolságot kell megtenni (méterben).
     *
     * @param altitude Az elért távolság.
     */
    private static double getDistanceToReachAltitude(double altitude) {
        double sineScale = 6 * 500;
        return ((Math.exp(altitude / 1000) / (1 + Math.exp(altitude / 1000)) - 0.5)
                + ((Math.sin(altitude / sineScale) + altitude / sineScale)) / MaxHeight * sineScale)
                / ((Math.exp(MaxHeight / 1000) / (1 + Math.exp(MaxHeight / 1000)) - 0.5)
                + ((Math.sin(MaxHeight / sineScale) + MaxHeight / sineScale)) / MaxHeight * sineScale) * MaxDistance;
    }
}
