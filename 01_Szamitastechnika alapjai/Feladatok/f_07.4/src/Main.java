/*
Fogalmazd át a következő igaz-hamis feltételt a lehető legrövidebb alakba:
if ((!a && !b && !c) || (a && b && !c) || (a && b && c) || (!a && b && !c) || (a && !b && !c) ||
(!a && b && c)){...}
*/


public class Main {
    public static void main(String[] args) {
        if (atalakitasHelyes(false, false, false)
                && atalakitasHelyes(false, false, true)
                && atalakitasHelyes(false, true, false)
                && atalakitasHelyes(false, true, true)
                && atalakitasHelyes(true, false, false)
                && atalakitasHelyes(true, false, true)
                && atalakitasHelyes(true, true, false)
                && atalakitasHelyes(true, true, true))
            System.out.println("Helyes az átalakítás.");
        else
            System.out.println("Nem helyes az átalakítás.");
    }

    private static boolean feltetelFeladatban(boolean a, boolean b, boolean c) {
        if ((!a && !b && !c) || (a && b && !c) || (a && b && c) || (!a && b && !c) || (a && !b && !c) ||
                (!a && b && c))
            return true;
        else
            return false;
    }

    private static boolean atalakitasHelyes(boolean a, boolean b, boolean c) {
        return feltetelFeladatban(a, b, c) == feltetelAtalakitva(a, b, c);
    }

    private static boolean feltetelAtalakitva(boolean a, boolean b, boolean c) {
        return b || !c;
    }
}

/*
(!a && !b && !c) || (a && b && !c) || (a && b && c) || (!a && b && !c) || (a && !b && !c) || (!a && b && c)
Igazságtábla:   Karnaugh tábla:     Megoldás: (!a && b && c) || (a && b && c) = (!a || a) && (b || b) && (c || c) = b && c
a b c f                                       (!a && b && !c) || (a && b && !c) = (!a || a) && (b || b) && (!c || !c) = b && !c
0 0 0 1            1 0 1 1                    (b && c) || (b && !c) = b
0 0 1 0         a| 1 0 1 1                    => b || !c
0 1 0 1                -b-
0 1 1 1              -c-
1 0 0 1
1 0 1 0
1 1 0 1
1 1 1 1
*/
