/*
Írjunk egy programot ami az aktuális könyvtárban létrehoz egy _DataFiles könyvtárat, majd a
consoleról *-ig beolvasott nevű fájlokat hoz létre ebben a könyvtárban.
*/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Path aktualisKonyvtar = Paths.get("").toAbsolutePath();

        aktualisKonyvtar = ujKonyvtarLetrehozasa(aktualisKonyvtar, "_DataFiles");

        fajlokLetrehozasa(aktualisKonyvtar);
    }

    private static void fajlokLetrehozasa(Path aktualisKonyvtar) throws IOException {
        Scanner sc = new Scanner(System.in);
        String ujFajlNev;
        do {
            System.out.print("Add meg az új fájlnevet: ");
            ujFajlNev = sc.nextLine();
            if (ujFajlNev.compareTo("*") != 0)
                Files.createFile(aktualisKonyvtar.resolve(ujFajlNev));
        } while (ujFajlNev.compareTo("*") != 0);
    }

    private static Path ujKonyvtarLetrehozasa(Path aktualisKonyvtar, String ujKonyvtarNev) throws IOException {
        Files.createDirectory(aktualisKonyvtar.resolve(ujKonyvtarNev));
        aktualisKonyvtar = aktualisKonyvtar.resolve(ujKonyvtarNev);
        return aktualisKonyvtar;
    }
}