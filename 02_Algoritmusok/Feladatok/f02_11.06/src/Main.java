/*
Írjon egy programot, ami a parancssori argumentumaként kapott fájlból feltölt egy
karakterláncokból álló tömböt. A fájl első sorában a feltöltendő tömb hossza van. Utána egy üress
sor. Majd a tömb elemei soronként.
*/

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        File bemenetiAdatok = Paths.get("", args[0]).toFile();
        FileReader fr = new FileReader(bemenetiAdatok);
        Scanner sc = new Scanner(fr);

        String[] tomb = new String[sc.nextInt()];
        sc.nextLine();
        sc.nextLine();
        for (int i = 0; i < tomb.length; i++) {
            tomb[i] = sc.nextLine();
        }

        for (int i = 0; i < tomb.length; i++) {
            if (i < tomb.length - 1)
                System.out.print(tomb[i] + " ");
            else
                System.out.println(tomb[i]);
        }
        fr.close();
    }
}