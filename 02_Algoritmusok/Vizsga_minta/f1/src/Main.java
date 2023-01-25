/*
Írjon egy programot ami az első parancssori argumentumaként kapott helyen lévő
szöveges fájlból beolvas egy duplapontos számokat tartalmazó kétdimenziós tömböt. Majd ezt a
tömböt oszloponként kiírja egy azonos elérési úton, de .bin-re cserélt kiterjesztésű fájlba.
A szöveges, olvasandó fájl formátuma: az első sorban szóközzel elválasztva szerpel a tömb
mérete. Utána soronként szóközzel elválasztva a tömb sorai.
A bináris, írandó fájl formátuma: az első bájtokon a tömb sorainak és oszlopainak száma
szerepel egész számként, utána a tömbe elemei oszloponként.
Pl.:
Szöveges fájl:              Bináris fájl (csak binárisan):
2 3                         23142.5536
1 2.5 3
4 5 6
*/

import java.io.*;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        File bemenet = Paths.get(args[0], "fajl.txt").toFile();
        FileReader fr = new FileReader(bemenet);
        Scanner sc = new Scanner(fr);
        int sor = sc.nextInt();
        int oszlop = sc.nextInt();
        double[][] tomb = new double[sor][oszlop];
        for (int i = 0; i < sor; i++) {
            for (int j = 0; j < oszlop; j++) {
                tomb[i][j] = sc.nextDouble();
            }
        }
        fr.close();

        File kimenet = Paths.get(args[0], "fajl.bin").toFile();
        FileOutputStream fos = new FileOutputStream(kimenet);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeByte(sor);
        oos.writeByte(oszlop);
        for (int i = 0; i < oszlop; i++) {
            for (int j = 0; j < sor; j++) {
                oos.writeDouble(tomb[j][i]);
            }
        }
        oos.close();
        fos.close();
    }
}