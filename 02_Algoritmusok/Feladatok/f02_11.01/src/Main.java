import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
Írja ki a program aktuális könyvtárában lévő összes könyvtár és fájl nevét. Egyszer a könyvtrárakat,
utána a fájlokat.
*/

public class Main {
    public static void main(String[] args) {
        Path aktualisKonyvtar = Paths.get("").toAbsolutePath().normalize();
        File aktualisKonyvtarInfo = aktualisKonyvtar.toFile();

        File[] informaciosTomb = aktualisKonyvtarInfo.listFiles();

        System.out.println("Aktuális könyvtár:");
        System.out.println(aktualisKonyvtar);

        System.out.println("Aktuális könyvtárban levő könyvtárak:");
        for (int i = 0; i < informaciosTomb.length; i++) {
            if (informaciosTomb[i].isDirectory()) {
                System.out.println(informaciosTomb[i]);
            }
        }

        System.out.println("Aktuális könyvtárban levő fájlok:");
        for (int i = 0; i < informaciosTomb.length; i++) {
            if (informaciosTomb[i].isFile()) {
                System.out.println(informaciosTomb[i]);
            }
        }
    }
}