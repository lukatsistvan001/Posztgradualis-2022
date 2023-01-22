/*
Írjon egy programot ami két parancssori argumentumot vár. Az első egy könyvtár elérési útja, a
második egy fájl neve. Egy rekurzív függvény segítségével döntse el, hogy van-e olyan fájl a
könyvtárban (vagy bármely abban lévő könyvtárban tetszőleges mélységben) bárhol olyan nevű
fájl. Ha vannak ilyenek, ezeknek a teljes elérési útját írja ki a consolere.
*/

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        Path aktualisKonyvtar = Paths.get(args[0]);
        File fajl = new File(args[1]);
        keres(aktualisKonyvtar, fajl);
    }

    private static void keres(Path aktualisKonyvtar, File fajl) {
        File[] konyvtarTartalma = aktualisKonyvtar.toFile().listFiles();
        for (int i = 0; i < konyvtarTartalma.length; i++) {
            if (konyvtarTartalma[i].isFile() && konyvtarTartalma[i].getName().compareTo(fajl.getName()) == 0)
                System.out.println(aktualisKonyvtar.toAbsolutePath());
            else if (konyvtarTartalma[i].isDirectory()) {
                keres(konyvtarTartalma[i].toPath(), fajl);
            }
        }
    }
}