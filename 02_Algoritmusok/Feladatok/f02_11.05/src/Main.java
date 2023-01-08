/*
Írjon egy programot, ami a parancssori argumentumaként kapott fájlból feltölt egy egész
számokból álló tömböt. A fájl első sorában a feltöltendő tömb hossza van. Utána egy üress sor.
Majd a tömb elemei szóközzel elválasztva.
*/

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        File bemenetiAdatok = Paths.get(args[0]).toFile();
        FileReader fr = new FileReader(bemenetiAdatok);
        Scanner sc = new Scanner(bemenetiAdatok);

        int[] tomb = new int[sc.nextInt()];
        sc.nextLine();
        for (int i = 0; i < tomb.length; i++) {
            tomb[i] = sc.nextInt();
        }

        for (int i = 0; i < tomb.length; i++) {
            if (i < tomb.length - 1)
                System.out.print(tomb[i] + ", ");
            else
                System.out.print(tomb[i]);
        }
        fr.close();
    }
}