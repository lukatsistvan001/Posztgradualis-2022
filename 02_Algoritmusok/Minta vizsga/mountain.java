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
        // van egy szép kilátó 890 méteren
        double targetHeight = 890;

        // sétatávolság a kilátóig
        double distanceToPanorama = calculateDistanceToHeight(targetHeight);
    }

    /**
     * Mekkora távolságot kell megtenni ahhoz, hogy egy adott magasságot elérjünk?
     * @param targetHeight Az elérendő magasság.
     * @return Az ösvényen megtett út hossza amíg elérjük a kívánt magasságot.
     */
    private static double calculateDistanceToHeight(double targetHeight) {
        return 0;
    }

    /**
     * Megadja, hogy az ösvényen megtett adott sétatávolság után mennyi szintkülönbséget
     * tesz meg a túrázó (méterben).
     *
     * @param walkingDistance Az ösvény mentén lesétált távolság méterben.
     */
    private static double getAltitudeReachedAfterWalking(double walkingDistance) {
        double sineScale = 500;
        return ((Math.exp(walkingDistance / 1000) / (1 + Math.exp(walkingDistance / 1000)) - 0.5)
                + ((Math.sin(walkingDistance / sineScale) + walkingDistance / sineScale)) / MaxDistance * sineScale)
                / ((Math.exp(MaxDistance / 1000) / (1 + Math.exp(MaxDistance / 1000)) - 0.5)
                + ((Math.sin(MaxDistance / sineScale) + MaxDistance / sineScale)) / MaxDistance * sineScale) * MaxHeight;
    }
}
