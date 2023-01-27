/*
Írjon egy programot ami az aktuális könyvtárban lévő bemenet.txt fájlból felölt egy karakterlánc
tömböt. A fájl első sorában a későbbi sorok száma van, utána soronként egy-egy karakterlánc.
a. Rendezze a tömböt buborék rendezéssel.
b. Rendezze a tömböt kiválasztásos rendezéssel.
c. Rendezze a tömböt beszúrásos rendezéssel.
*/

import com.sun.jdi.event.StepEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File bemenet = Paths.get("", "bemenet.txt").toFile();
        FileReader fr = new FileReader(bemenet);
        Scanner sc = new Scanner(fr);
        int hossz = sc.nextInt();
        sc.nextLine();
        String[] tomb = new String[hossz];
        for (int i = 0; i < hossz; i++) {
            tomb[i] = sc.nextLine();
        }

        //buborekosRendezes(tomb);
        //kivalasztoRendezes(tomb);
        //beszurasosRendezes(tomb);
        osszefesulesesRendezes(tomb);
    }

    // időkomplexitás: O(n2)
    // tárkomplexitás: bemenet + O(1)
    // stabil
    private static void buborekosRendezes(String[] tomb) {
        boolean voltRendezes;
        int rendezettVegHossza = 0;
        do {
            voltRendezes = false;
            for (int i = 0; i < tomb.length - 1 - rendezettVegHossza; i++) {
                if (tomb[i].compareTo(tomb[i + 1]) > 0) {
                    String temp = tomb[i];
                    tomb[i] = tomb[i + 1];
                    tomb[i + 1] = temp;
                    voltRendezes = true;
                }
            }
            rendezettVegHossza++;
        } while (voltRendezes);
    }

    // időkomplexitás: O(n2)
    // tárkomplexitás: bemenet + O(1)
    // stabil
    private static void kivalasztoRendezes(String[] tomb) {
        for (int i = 0; i < tomb.length - 1; i++) {
            int legkisebbElemHelye = i;
            for (int j = i + 1; j < tomb.length; j++) {
                if (tomb[legkisebbElemHelye].compareTo(tomb[j]) > 0) {
                    legkisebbElemHelye = j;
                }
            }
            if (legkisebbElemHelye != i) {
                String temp = tomb[legkisebbElemHelye];
                tomb[legkisebbElemHelye] = tomb[i];
                tomb[i] = temp;
            }
        }
    }

    // időkomplexitás: O(n2)
    // tárkomplexitás: bemenet + O(1)
    // "<"-el stabil, "<="-vel nem stabil
    private static void beszurasosRendezes(String[] tomb) {
        for (int i = 0; i < tomb.length - 1; i++) {
            for (int j = i + 1; j < tomb.length; j++) {
                if (tomb[j].compareTo(tomb[i]) < 0) {
                    String temp = tomb[j];
                    for (int k = j; k > i; k--) {
                        tomb[k] = tomb[k - 1];
                    }
                    tomb[i] = temp;
                }
            }
        }
    }

    // időkomplexitás O(nlogn)
    // tárhelykomplexitás O(n)
    private static void osszefesulesesRendezes(String[] tomb) {
        if (tomb.length <= 1)
            return;
        else {
            int felezo = tomb.length / 2;
            String[] alsoResz = new String[tomb.length / 2];
            String[] felsoResz = new String[tomb.length - alsoResz.length];
            for (int i = 0; i < felezo; i++) {
                alsoResz[i] = tomb[i];
            }
            for (int i = felezo; i < tomb.length; i++) {
                felsoResz[i - felezo] = tomb[i];
            }
            osszefesulesesRendezes(alsoResz);
            osszefesulesesRendezes(felsoResz);
            String[] rendezett = ketRendezettTombOsszefesulese(alsoResz, felsoResz);
            for (int i = 0; i < tomb.length; i++) {
                tomb[i] = rendezett[i];
            }
        }
        return;
    }

    private static String[] ketRendezettTombOsszefesulese(String[] alsoResz, String[] felsoResz) {
        String[] tomb = new String[alsoResz.length + felsoResz.length];
        int alsoReszIndex = 0;
        int felsoReszIndex = 0;
        int tombIndex = 0;
        while (alsoReszIndex < alsoResz.length && felsoReszIndex < felsoResz.length) {
            if (alsoResz[alsoReszIndex].compareTo(felsoResz[felsoReszIndex]) < 0) {
                tomb[tombIndex] = alsoResz[alsoReszIndex];
                tombIndex++;
                alsoReszIndex++;
            } else {
                tomb[tombIndex] = felsoResz[felsoReszIndex];
                tombIndex++;
                felsoReszIndex++;
            }
        }
        while (alsoReszIndex < alsoResz.length) {
            tomb[tombIndex] = alsoResz[alsoReszIndex];
            tombIndex++;
            alsoReszIndex++;
        }
        while (felsoReszIndex < felsoResz.length) {
            tomb[tombIndex] = felsoResz[felsoReszIndex];
            tombIndex++;
            felsoReszIndex++;
        }
        return tomb;
    }
}