public class Main {
    public static void main(String[] args) {
        int kersettElem = pascalHaromszog(7, 6);
        System.out.println("A keresett elem: " + kersettElem);
    }

    private static int pascalHaromszog(int sor, int oszlop) {
        int[] tomb = {1};
        return pascalHaromszog(sor, oszlop, tomb);
    }

    private static int pascalHaromszog(int sor, int oszlop, int[] tomb) {
        if (tomb.length == sor + 1)
            return tomb[oszlop];
        else {
            int[] ujtomb = new int[tomb.length + 1];
            ujtomb[0] = 1;
            ujtomb[ujtomb.length - 1] = 1;
            for (int i = 1; i < ujtomb.length - 1; i++) {
                ujtomb[i] = tomb[i - 1] + tomb[i];
            }
            return pascalHaromszog(sor, oszlop, ujtomb);
        }
    }
}