import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Math.abs;

/*
Írj egy programot ami előszőr feltölt egy 5 elemű tömböt véletlen számokkal, majd rendezi és
kiírja. Ezek után addig kér be adatot a felhasználótól, amíg az *-ot nem ad meg. Ha nem számot
adott meg, akkor kiírja, hogy csak számokat vagy *-ot fogadunk el. Ha számot adott meg, akkor a
tömb azon elemét amelynek értéke a legközelebb esik a megadott új számhoz kicseréli az új
számra, rendezi a tömböt, majd kiírja.
*/

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random r = new Random();

        int[] tomb = new int[5];

        for (int i = 0; i < 5; i++)
            tomb[i] = r.nextInt();

        System.out.println("A tömb:");
        for (int i = 0; i < 5; i++)
            System.out.println(tomb[i]);

        Arrays.sort(tomb);
        System.out.println("A rendezett tömb:");
        for (int i = 0; i < 5; i++)
            System.out.println(tomb[i]);

        String beadottErtek;
        int kulonbseg;
        int szam;
        int index;
        do {
            System.out.print("Adj be egy értéket: ");
            beadottErtek = sc.nextLine();
            if (((beadottErtek.compareTo("0") < 0) || (beadottErtek.compareTo("9") > 0)) && (beadottErtek.compareTo("*") != 0))
                System.out.println("Csak számokat vagy *-ot fogadunk el.");
            else if (beadottErtek.compareTo("*") == 0)
                continue;
            else {
                szam = Integer.parseInt(beadottErtek);
                kulonbseg = abs(szam - tomb[0]);
                index = 0;
                for (int i = 1; i < 5; i++) {
                    if (kulonbseg > abs(szam - tomb[i])) {
                        kulonbseg = abs(szam - tomb[i]);
                        index = i;
                    }
                }
                tomb[index] = szam;
                Arrays.sort(tomb);
                System.out.println("A rendezett tömb:");
                for (int i = 0; i < 5; i++)
                    System.out.println(tomb[i]);
            }
        } while (beadottErtek.compareTo("*") != 0);
    }
}