package com.company;

public class Main {

    public static void main(String[] args) {

        // állíts be egy tetszőleges mintakarakterláncot, amivel tesztelni szeretnél
        String s = "almaelmentaludni";

        // a tömb i-ik eleme azt mutatja meg, hogy s i-ik karaktere hányszor szerepel s-ben.
        int[] occurenceCount = calculateFrequencies(s);

        // azokat a karaktereket amiket már kiírtunk ebben a stringben gyűjtjük,
        // mert mindent csak egyszer kell kiírni
        String alreadyPrinted = "";

        for (int i = 0; i < s.length(); i++) {
            // az aktuálisan feldolgozott karakter
            char c = s.charAt(i);

            // a tömb nemnegatív elemei azt mutatják, hogy c mely pozíciókon szerepel s-ben
            int[] positionsOfC = getPositionsOfC(s, c);

            // igaz, ha c minden előfordulásánál a követő karakter kevesebbszer fordul elő mint c
            boolean moreThanEveryFollower = occursMoreThanEveryFollower(occurenceCount, i, positionsOfC);

            if (moreThanEveryFollower) {
                System.out.println(c);
            }
        }
    }

    /**
     * Ellenörzi, hogy az a currentPositionOfC-ik karakter esetében igaz-e, hogy minden előfordulása esetén az őt
     * követő karakter kevesebbszer fordul elő.
     * @param occurenceCount
     * @param currentPositionOfC
     * @param positionsOfC
     * @return
     */
    private static boolean occursMoreThanEveryFollower(int[] occurenceCount, int currentPositionOfC, int[] positionsOfC) {
        boolean moreThanEveryFollower = true;
        for (int i = 0; i < positionsOfC.length && positionsOfC[i] >= 0; i++) {
            if ((positionsOfC[i] < occurenceCount.length && positionsOfC[i] >= 0) &&
                    (occurenceCount[currentPositionOfC] < occurenceCount[positionsOfC[i]] + 1)) {
                moreThanEveryFollower = false;
                break;
            }
        }
        return moreThanEveryFollower;
    }

    /**
     * Megkeresi, hogy c mely pozíciókon fordul elő s-ben.
     * @param s
     * @param c
     * @return A visszaadott tömb első elemei c pozícióit tartalmazzák. A maradék helyek érvénytelensége jelölve van.
     */
    private static int[] getPositionsOfC(String s, char c) {
        int[] positions  = new int[s.length()];
        int nextValidPositionIdx =0;
        int nextPosition = s.indexOf(c);
        while (nextPosition >=0){
            positions[nextValidPositionIdx] = nextPosition;
            ++nextValidPositionIdx;
            nextPosition = s.indexOf(c, nextPosition+1);
        }

        return  positions;
    }

    /**
     * Kiszámolja minden karakterre, hogy az hányszor szerepel s-ben.
     * @param s
     * @return A visszaadott tömb i-ik helyén levő szám megmutatja, hogy s i-ik karaktere hányszor szerepel s-ben.
     */
    private static int[] countOccurences(String s) {
        return new int[0];
    }
}
