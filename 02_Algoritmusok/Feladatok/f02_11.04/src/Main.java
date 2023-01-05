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
        Scanner sc = new Scanner(System.in);
        Path aktualisKonyvtar = Paths.get("").toAbsolutePath();
        String ujKonyvtarNev;
        do {
            System.out.print("Add meg az új könvytárnevet: ");
            ujKonyvtarNev = sc.nextLine();
            if (!ujKonyvtarNev.contains("."))
                aktualisKonyvtar = Files.createDirectory(aktualisKonyvtar.resolve(ujKonyvtarNev));
            else
                Files.createFile(aktualisKonyvtar.resolve(ujKonyvtarNev));
        } while (!ujKonyvtarNev.contains("."));
    }
}