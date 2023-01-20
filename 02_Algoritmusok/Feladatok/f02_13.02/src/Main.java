/*
Írjon egy programo ami a 14.9 feladat rendezetlen adatbázisából készít egy rendezett verziót.
14.9: Egy irodaházpark irodái egy olyan címmel azonosítódnak, amiben az épület azonsóítója
(karakterlánc), azon belül az emelet (szám) és azon belül az iroda száma (szám) van pontokkal
elválasztva. Az irodák adatbázisa egy fájl, aminek első sora tartalmazza az adatbázisban lévő sorok
számát, utána pedig soronként tartalmaz egy címet és az ott székelő cég nevét.
Pl.: Neumann.12.321.ACME
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

        //tömb feltöltése fájlból
        int tombHossz = sc.nextInt();
        sc.nextLine();
        String[][] irodak = new String[tombHossz][4];
        for (int i = 0; i < tombHossz; i++) {
            String karakterlanc = sc.nextLine();
            irodak[i][0] = karakterlanc.substring(0, karakterlanc.indexOf('.'));
            karakterlanc = karakterlanc.substring(karakterlanc.indexOf('.') + 1);
            irodak[i][1] = karakterlanc.substring(0, karakterlanc.indexOf('.'));
            karakterlanc = karakterlanc.substring(karakterlanc.indexOf('.') + 1);
            irodak[i][2] = karakterlanc.substring(0, karakterlanc.indexOf('.'));
            irodak[i][3] = karakterlanc.substring(karakterlanc.indexOf('.') + 1);
        }

        for (int i = 0; i < irodak.length - 1; i++) {
            int legkisebbPozicioja = i;
            for (int j = i + 1; j < irodak.length; j++) {
                if (irodak[j][0].compareTo(irodak[legkisebbPozicioja][0]) < 0) {
                    legkisebbPozicioja = j;
                } else if (irodak[j][0].compareTo(irodak[legkisebbPozicioja][0]) == 0) {
                    if (irodak[j][1].compareTo(irodak[legkisebbPozicioja][1]) < 0) {
                        legkisebbPozicioja = j;
                    } else if (irodak[j][1].compareTo(irodak[legkisebbPozicioja][1]) == 0) {
                        if (irodak[j][2].compareTo(irodak[legkisebbPozicioja][2]) < 0) {
                            legkisebbPozicioja = j;
                        } else if (irodak[j][2].compareTo(irodak[legkisebbPozicioja][2]) == 0) {
                            if (irodak[j][3].compareTo(irodak[legkisebbPozicioja][3]) < 0) {
                                legkisebbPozicioja = j;
                            }
                        }
                    }
                }
            }
            if (legkisebbPozicioja != i) {
                for (int j = 0; j < 4; j++) {
                    String temp = irodak[i][j];
                    irodak[i][j] = irodak[legkisebbPozicioja][j];
                    irodak[legkisebbPozicioja][j] = temp;
                }
            }
        }
    }
}
