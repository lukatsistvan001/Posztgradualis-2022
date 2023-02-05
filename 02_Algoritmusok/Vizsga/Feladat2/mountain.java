package com.company;

public class Main {

    /**
     * A túraösvény teljes hossza a csúcsig.
     */
    private static double MaxDistance = 8500;

    /**
     * A csúcs magassága.
     */
    private static double MaxHeight = 1221;

    public static void main(String[] args) {
        // van egy szép kilátó 4500 méterre a starttól
        double targetDistance = 4500;

        // a kilátóg magassága
        double panoramaHeight = calculateHeightForDistance(targetDistance);
    }

    /**
     * Mekkora magasságba érünk fel az ösvényen a megadott távolság legyaloglása után?
     * @param distance A ösvényen megtett távolság.
     * @return Az út végén elért magasság.
     */
    private static double calculateHeightForDistance (double distance) {
        return  0;
    }

    /**
     * Megadja, hogy adott szintkülönbség eléréséhez az ösvényen mekkora távolságot kell megtenni (méterben).
     *
     * @param altitude Az elért távolság.
     */
    private static double getDistanceToReachAltitude(double altitude) {
        double sineScale = 6*500;
        return ((Math.exp(altitude / 1000) / (1 + Math.exp(altitude / 1000)) - 0.5)
                + ((Math.sin(altitude / sineScale) + altitude / sineScale)) / MaxHeight * sineScale)
                / ((Math.exp(MaxHeight / 1000) / (1 + Math.exp(MaxHeight / 1000)) - 0.5)
                + ((Math.sin(MaxHeight / sineScale) + MaxHeight / sineScale)) / MaxHeight * sineScale) * MaxDistance;
    }
}
