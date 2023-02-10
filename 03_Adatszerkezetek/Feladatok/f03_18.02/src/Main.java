/*
Implementáljon gráf adattípust éllisták segítségével. A mellékelt bemeneti fájlból tölts fel egy
mátrixot ami diákok azt a viszonyát hivatott reprezentálni, hogy űltek-e egy padsorban. (Irányítitt
vagy irányítatlan gráf szükséges?)
A gráf feltöltése után a program *-ig kérjen be neveket. Ha a megadott név szerepel a gráfban,
akkor írja ki a vele egy padsorban valaha ült diákok neveit.
A bemeneti fájl formátuma:
első sorban egy szám 𝑛, ami a diákok számát adja meg
utána soronként az 𝑛 diák nevei
utána egy sorban egy 𝑚 szám, ami a valaha volt összes padsor kombinációk számát jelöli
utána soronként ,-vel elválasztva az adott padsorkombinációban lévő diákok nevei
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    private static String[] nevek;
    private static ellistasGraf padsorKapcsolatokMatrix;
    private static HashMap<String, Integer> nevekIndexei = new HashMap<>();

    public static void main(String[] args) throws FileNotFoundException {
        File bemenetiFajl = Paths.get(args[0]).toFile();
        Scanner scBemenet = new Scanner(bemenetiFajl);
        int diakokSzama = scBemenet.nextInt();
        scBemenet.nextLine();

        nevek = new String[diakokSzama];
        for (int i = 0; i < diakokSzama; i++) {
            nevek[i] = scBemenet.nextLine();
            nevekIndexei.put(nevek[i], i);
        }

        padsorKapcsolatokMatrix = new ellistasGraf(diakokSzama);
        int padsorKombinaciokSzama = scBemenet.nextInt();
        scBemenet.nextLine();
        for (int i = 0; i < padsorKombinaciokSzama; i++) {
            String padsorKombinacio = scBemenet.nextLine();
            String[] nevekAPadsorKombinacioban = padsorKombinacio.split(", ");
            for (int j = 0; j < nevekAPadsorKombinacioban.length; j++) {
                String nev1 = nevekAPadsorKombinacioban[j];
                for (int k = j + 1; k < nevekAPadsorKombinacioban.length; k++) {
                    String nev2 = nevekAPadsorKombinacioban[k];
                    padsorKapcsolatokMatrix.elHozzaadas(nevekIndexei.get(nev1), nevekIndexei.get(nev2));
                }
            }
        }

        Scanner sc = new Scanner(System.in);
        String nev;
        do {
            System.out.print("Adj be egy nevet: ");
            nev = sc.nextLine();
            int[] padtarsakIndexei;
            if (nevekIndexei.containsKey(nev)) {
                System.out.print(nev + " padtársai voltak: ");
                padtarsakIndexei = padsorKapcsolatokMatrix.szomszedok(nevekIndexei.get(nev));
                for (int i = 0; i < padtarsakIndexei.length; i++) {
                    System.out.print(nevek[padtarsakIndexei[i]]);
                    if (i < padtarsakIndexei.length - 1)
                        System.out.print(", ");
                }
                System.out.println();
            } else System.out.println("Hogy ki?");
        } while (!nev.equals("*"));
    }

}

class ellistasGraf {
    csucs[] csucsok;

    public ellistasGraf(int n) {
        csucsok = new csucs[n];
        for (int i = 0; i < csucsok.length; i++) {
            csucsok[i] = new csucs(i);
        }
    }

    public void elHozzaadas(int i, int j) {
        csucsok[i].szomszedHozzaadasa(j);
        csucsok[j].szomszedHozzaadasa(i);
    }

    public int[] szomszedok(int i) {
        return csucsok[i].szomszedok();
    }
}

class csucs {
    private int id;

    private ArrayList<elleiras> elek;

    public csucs(int id) {
        this.id = id;
        this.elek = new ArrayList<>();
    }

    public void szomszedHozzaadasa(int ujSzomszedID) {
        for (int i = 0; i < this.elek.size(); i++) {
            if (elek.get(i).getCelCsucs() == ujSzomszedID)
                return;
        }
        elek.add(new elleiras(ujSzomszedID));
    }

    public int szomszedokSzama() {
        return this.elek.size();
    }

    public int[] szomszedok() {
        int[] szomszedok = new int[this.elek.size()];
        for (int i = 0; i < this.elek.size(); i++) {
            szomszedok[i] = this.elek.get(i).getCelCsucs();
        }
        return szomszedok;
    }

}

class elleiras {
    private int celCsucs;

    public elleiras(int celCsucs) {
        this.celCsucs = celCsucs;
    }

    public int getCelCsucs() {
        return this.celCsucs;
    }
}