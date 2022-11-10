import java.util.Random;

/*
Oldd meg a 6.13 feladatot majd refaktoráld a megoldásodat, hogy függvények felhasználásával
oldd meg, jelentősen csökkentve a kód mennyiségét és javítva az olvashatóságot.
Használd az IDE beépített refaktorálási funkcióit (jobb klick, refactor, extract method).
*/

/*
6.13
Írj egy programot amely felölt egy 10 elemű egész szám tömböt 2 és 15000 közötti véletlen egész
számokkal, kiírja a tömb elemeit sorrendben vesszővel elválasztva, megcseréli a legkisebb és a
legnagyobb element, majd újra kiírja a tömb elemeit.
Mivel a számok hossza eltérő, ezért a kiírás láthatóan igénytelennek hat. Gondoskodj róla, hogy a
két kiírás egymás alatti sorokban legyen, és a számokat elválasztó vesszők egymás fölé legyenek
igazítva. (Használd fel az előző feladat kódját.)
*/

public class Main {
    private static int[] tomb = new int[10];
    private static Random r = new Random();

    public static void main(String[] args) {

        tombFeltoltese();

        tombKiirasa();

        csereElvegzese();

        tombKiirasa();

    }

    private static int[] csereElvegzese() {
        int legkisebbSzam = tomb[0];
        int legnagyobbSzam = tomb[0];
        int legkisebbPozicioja = 0;
        int legnagyobbPozicioja = 0;

        for (int i = 1; i < 10; i++) {
            if (legkisebbSzam > tomb[i]) {
                legkisebbSzam = tomb[i];
                legkisebbPozicioja = i;
            }
            if (legnagyobbSzam < tomb[i]) {
                legnagyobbSzam = tomb[i];
                legnagyobbPozicioja = i;
            }
        }

        int temp = tomb[legkisebbPozicioja];
        tomb[legkisebbPozicioja] = tomb[legnagyobbPozicioja];
        tomb[legnagyobbPozicioja] = temp;
        return tomb;
    }

    private static void tombKiirasa() {
        for (int i = 0; i < 10; i++) {
            if (tomb[i] < 10)
                System.out.print("    ");
            if (tomb[i] >= 10 && tomb[i] < 100)
                System.out.print("   ");
            if (tomb[i] >= 100 && tomb[i] < 1000)
                System.out.print("  ");
            if (tomb[i] >= 1000 && tomb[i] < 10000)
                System.out.print(" ");
            if (i != 9)
                System.out.print(tomb[i] + ", ");
            else
                System.out.println(tomb[i]);
        }
    }

    private static int[] tombFeltoltese() {
        for (int i = 0; i < 10; i++)
            tomb[i] = r.nextInt(2, 15000);
        return tomb;
    }
}