/*
Írjon egy programot ami az aktuális könyvtárban lévő bemenet.txt fájlból felölt egy karakterlánc
tömböt. A fájl első sorában a későbbi sorok száma van, utána soronként egy-egy karakterlánc.
a. Rendezze a tömböt buborék rendezéssel.
b. Rendezze a tömböt kiválasztásos rendezéssel.
c. Rendezze a tömböt beszúrásos rendezéssel.
*/

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
        beszurasosRendezes(tomb);
    }

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

    private static void beszurasosRendezes(String[] tomb) {
        for (int i = 0; i < tomb.length - 1; i++) {
            for (int j = i + 1; j < tomb.length; j++) {
                if (tomb[j].compareTo(tomb[i]) < 0) {
                    String temp = tomb[j];
                    for (int k = j; k > i; k--) {
                        tomb[k] = tomb[k-1];
                    }
                    tomb[i] = temp;
                }
            }
        }
    }
}