/*
Írjon egy programot, ami a parancssori argumentumaként kapott fájlba kiír egy 100x100-as
tömböt
a. az előző feladat formátumában szöveges fájlba és
b. bináris fájlba, csak a számokat kiírva
c. majd írja meg a bináris formátumból való visszaovlasást is.
d. Ezen kívül írjon egy ellenörző függvényt, ami a bináris fájlból illetve a szövegesből
beolvasott tömböket összehasonlítja.
*/

import java.io.*;
import java.nio.file.Paths;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {
        Random r = new Random();
        File szovegesFajl = Paths.get("", args[0]).toFile();
        File binarisFajl = Paths.get("", args[1]).toFile();

        int[][] tomb = new int[100][100];
        for (int i = 0; i < tomb.length; i++) {
            for (int j = 0; j < tomb.length; j++) {
                tomb[i][j] = r.nextInt(0, 100);
            }
        }

        szovegesFajlIras(szovegesFajl, tomb);
        binarisFajlIras(binarisFajl, tomb);

        int[][] binTomb = new int[tomb.length][tomb.length];
        binTomb = binarisFajlOlvasas(binarisFajl, tomb.length);

        if (osszehasonlitas(tomb, binTomb))
            System.out.println("Egyenlő a két fájl tartalama.");
        else
            System.out.println("Nem egyenlő a két fájl tartalama.");
    }

    private static boolean osszehasonlitas(int[][] tomb, int[][] binTomb) {
        boolean egyenlo = true;
        for (int i = 0; i < tomb.length; i++) {
            for (int j = 0; j < tomb.length; j++) {
                if (tomb[i][j] != binTomb[i][j])
                    egyenlo = false;
            }
        }
        return egyenlo;
    }

    private static int[][] binarisFajlOlvasas(File binarisFajl, int tombHossza) throws IOException {
        FileInputStream fis = new FileInputStream(binarisFajl);
        ObjectInputStream ois = new ObjectInputStream(fis);
        int[][] binTomb = new int[tombHossza][tombHossza];
        for (int i = 0; i < tombHossza; i++) {
            for (int j = 0; j < tombHossza; j++) {
                binTomb[i][j] = ois.readInt();
            }
        }
        fis.close();
        ois.close();
        return binTomb;
    }

    private static void binarisFajlIras(File binarisFajl, int[][] tomb) throws IOException {
        FileOutputStream fos = new FileOutputStream(binarisFajl);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        for (int i = 0; i < tomb.length; i++) {
            for (int j = 0; j < tomb.length; j++) {
                oos.writeInt(tomb[i][j]);
            }
        }
        oos.close();
        fos.close();
        ;
    }

    private static void szovegesFajlIras(File szovegesFajl, int[][] tomb) throws IOException {
        FileWriter fw = new FileWriter(szovegesFajl);
        for (int i = 0; i < tomb.length; i++) {
            for (int j = 0; j < tomb.length; j++) {
                fw.write("" + tomb[i][j]);
            }
        }
        fw.close();
    }
}