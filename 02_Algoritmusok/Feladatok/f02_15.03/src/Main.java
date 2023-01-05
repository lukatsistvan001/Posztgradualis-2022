/*
Írjuk ki egy 𝑛 pozitív egész szám összes összegfelbontását, vagyis az összes olyan felbontást,
amiben pozitív egész számok összegeként előállítható.
Pl.: 4= 1 + 1 + 1 + 1 = 1 + 1 + 2 = 1 + 3 = 2 + 2
(a 3+1, 1+2+1 és hasonló kombinációkat ne írjuk ki)
*/


public class Main {

    private static int[] aktualisReszmegoldas;
    private static int n = 4;

    public static void main(String[] args) {

        int helyesMegoldasokSzama = 0;
        KezdetiReszmegoldasBeallitasa(n);

        // backtracking iterativan
        int aktualisSzint = 0;
        while (aktualisSzint >= 0) {
            boolean azonosSzintenVoltUjKezdemeny = false;
            boolean ujKezdemenyEldobando = false;
            do {
                azonosSzintenVoltUjKezdemeny = SzintenBelulUjReszmegoldas(aktualisSzint);
                if (azonosSzintenVoltUjKezdemeny)
                    ujKezdemenyEldobando = EldobandoReszmegoldas(aktualisSzint);
            } while (azonosSzintenVoltUjKezdemeny && ujKezdemenyEldobando);

            if (!ujKezdemenyEldobando && azonosSzintenVoltUjKezdemeny) {
                if (TeljesMegoldas(aktualisSzint)) {
                    // dolgozzuk
                    ++helyesMegoldasokSzama;
                    MegoldasKiirasa(helyesMegoldasokSzama, aktualisReszmegoldas);
                } else {
                    // szintlepes
                    ++aktualisSzint;
                    // reszmegoldas specifikalasa
                }
            } else {
                // backtrack
                // szint lepes lefele
                --aktualisSzint;
                aktualisReszmegoldas[aktualisSzint + 1] = -1;
            }
        }
    }

    private static void KezdetiReszmegoldasBeallitasa(int n) {
        aktualisReszmegoldas = new int[n];
        for (int i = 0; i < n; i++) {
            aktualisReszmegoldas[i] = 0;
        }
    }

    private static boolean TeljesMegoldas(int aktualisSzint) {
        return aktualisSzint == aktualisReszmegoldas.length - 1;
    }

    private static boolean EldobandoReszmegoldas(int aktualisSzint) {
        if (aktualisSzint > 1)
            for (int i = 1; i < aktualisSzint; i++) {
                if (aktualisReszmegoldas[i] < aktualisReszmegoldas[i - 1])
                    return true;
            }
        if (osszeg(aktualisReszmegoldas) > n)
            return true;
        return false;
    }

    private static boolean SzintenBelulUjReszmegoldas(int aktualisSzint) {
        if (!(aktualisReszmegoldas[aktualisSzint] < aktualisReszmegoldas.length - 1))
            return false;
        return true;
    }

    private static void MegoldasKiirasa(long megoldasiTippekSzama, int[] megoldasTipp) {
        System.out.print("#" + megoldasiTippekSzama + ": ");

    }

    private static int osszeg(int[] aktualisReszmegoldas) {
        int sum = 0;
        for (int i = 0; i < aktualisReszmegoldas.length; i++) {
            sum = sum + aktualisReszmegoldas[i];
        }
        return sum;
    }
}