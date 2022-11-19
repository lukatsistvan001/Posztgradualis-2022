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
        System.out.print("Add meg a fájl nevét: ");
        String ujFaljNev = sc.nextLine();
        while (!ujFaljNev.equals("*")) {
            Files.createFile(aktualisKonyvtar.resolve(ujFaljNev));
            System.out.print("Add meg a fájl nevét: ");
            ujFaljNev = sc.nextLine();
        }
    }

    private static Path ujKonyvtarLetrehozasa(Path aktualisKonyvtar, String ujKonyvtarNev) throws IOException {
        Files.createDirectory(aktualisKonyvtar.resolve(ujKonyvtarNev));
        aktualisKonyvtar = aktualisKonyvtar.resolve(ujKonyvtarNev);
        return aktualisKonyvtar;
    }
}