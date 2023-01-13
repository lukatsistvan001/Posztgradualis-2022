/*
Írjon programot, ami a prancssori argumentumként kapott fájlból felolvas egy labirintust. A
labirintus egy kétdimenziós karaktertömbben tárolható. A fájl első sorában két szám szerepel
szóközzel elválasztva, ami a labirintus méretét adja meg (sorok és oszlopok száma). Ez után a
sorokban x jelöli a falat és szóköz a szabad járható részeket.
(Értelmes labirintust generálni nehezebb mint felolvasni, vagy akár kitalálni belőle, azt majd egy
hónap mulva.)
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

        int oszlopokSzama = sc.nextInt();
        int sorokSzama = sc.nextInt();
        sc.nextLine();
        String[][] labirintus = new String[sorokSzama][oszlopokSzama];

        String s;
        for (int i = 0; i < sorokSzama; i++) {
            s = sc.nextLine();
            for (int j = 0; j < s.length(); j++) {
                labirintus[i][j] = s.substring(j, j + 1);
            }
        }
        fr.close();

        for (int i = 0; i < sorokSzama; i++) {
            for (int j = 0; j < oszlopokSzama; j++) {
                System.out.print(labirintus[i][j]);
            }
            System.out.println();
        }
    }
}