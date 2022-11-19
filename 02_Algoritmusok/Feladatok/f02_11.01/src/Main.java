import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
Írja ki a program aktuális könyvtárában lévő összes könyvtár és fájl nevét. Egyszer a könyvtrárakat,
utána a fájlokat.
*/

public class Main {
    public static void main(String[] args) {
        konyvtarak(Paths.get("").toAbsolutePath());
        fajlok(Paths.get("").toAbsolutePath());
    }

    private static void konyvtarak(Path aktualisKonyvtar) {
        File[] informaciosTomb = aktualisKonyvtar.toFile().listFiles();
        System.out.println("Aktuális könyvtárban levő könyvtárak:");
        for (int i = 0; i < informaciosTomb.length; i++) {
            if (informaciosTomb[i].isDirectory()) {
                System.out.println(informaciosTomb[i].getName());
            }
        }
    }

    private static void fajlok(Path aktualisKonyvtar) {
        File[] informaciosTomb = aktualisKonyvtar.toFile().listFiles();
        System.out.println("Aktuális könyvtárban levő fajlok:");
        for (int i = 0; i < informaciosTomb.length; i++) {
            if (informaciosTomb[i].isFile()) {
                System.out.println(informaciosTomb[i].getName());
            }
        }
    }
}