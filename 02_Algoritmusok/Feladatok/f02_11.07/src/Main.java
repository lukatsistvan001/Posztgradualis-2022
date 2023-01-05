/*
Írjon egy programot, ami a parancssori argumentumaként kapott fájlból feltölt egy kétdimenziós
törtszám tömböt, aminek a második dimenziói változnak. A fájl első sora azt adja meg, hogy a
tömb első dimenziója mentén hány elem van. Ez után minden sor egy számmal kezdődik, ami azt
mutatja meg, hogy a második dimenzióban a tömb hány elemet tartalmaz abban az elemben.
Majd ezek a számok következnek szóközökkel elválasztva.
Pl.:
2
5 1 2 3 4 5
3 1 2 3
azzal egyenértékű, hogy {{1, 2,3, 4 ,5}, {1, 2, 3}}.
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File bemenetiAdatok = Paths.get("", args[0]).toFile();
        FileReader fr = new FileReader(bemenetiAdatok);
        Scanner sc = new Scanner(fr);

        int sorokSzama = sc.nextInt();
        double[][] tomb = new double[sorokSzama][];
        for (int i = 0; i < sorokSzama; i++) {
            int elemekSzama = sc.nextInt();
            tomb[i] = new double[elemekSzama];
            for (int j = 0; j < elemekSzama; j++) {
                tomb[i][j] = sc.nextInt();
                System.out.print(tomb[i][j] + " ");
            }
            System.out.println();
        }
    }
}