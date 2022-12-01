/*
Írj egy programot ami addig kér be könyvtárneveket, ameddig abban pont nem lesz (pl x, y, a.txt),
majd ez alapján létrehozza az aktuális könyvtárban az x/y/a.txt fájlt.
*/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Path aktualisKonyvtar = Paths.get("").toAbsolutePath();
        Scanner sc = new Scanner(System.in);
        String bemenetiAdat;
        do {
            System.out.print("Add meg a következő bemeneti adatot: ");
            bemenetiAdat = sc.nextLine();
            if (!bemenetiAdat.contains("."))
                aktualisKonyvtar = ujKonyvtarLetrehozasa(aktualisKonyvtar, bemenetiAdat);
            else
                fajlLetrehozasa(aktualisKonyvtar, bemenetiAdat);
        } while (!bemenetiAdat.contains("."));
    }

    private static void fajlLetrehozasa(Path aktualisKonyvtar, String ujFaljNev) throws IOException {
        Files.createFile(aktualisKonyvtar.resolve(ujFaljNev));
    }

    private static Path ujKonyvtarLetrehozasa(Path aktualisKonyvtar, String ujKonyvtarNev) throws IOException {
        Files.createDirectory(aktualisKonyvtar.resolve(ujKonyvtarNev));
        aktualisKonyvtar = aktualisKonyvtar.resolve(ujKonyvtarNev);
        return aktualisKonyvtar;
    }
}