/*
Fogalmazd át a következő igaz-hamis feltételt a lehető legrövidebb alakba:
if ((!a && b) || (!b && c) || (a && c) || (!a && !c) || (b && c)){...}
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

        String[][] tomb = new String[2][3];
        tomb[0][0] = "a";
        tomb[0][1] = "b";
        tomb[0][2] = "c";
        tomb[1][0] = "A";
        tomb[1][1] = "B";


    }

    private static boolean feltetelFeladatban(boolean a, boolean b, boolean c) {
        if ((!a && b) || (!b && c) || (a && c) || (!a && !c) || (b && c))
            return true;
        else
            return false;
    }

    private static boolean atalakitasHelyes(boolean a, boolean b, boolean c) {
        return feltetelFeladatban(a, b, c) == feltetelAtalakitva(a, b, c);
    }

    private static boolean feltetelAtalakitva(boolean a, boolean b, boolean c) {
        return c || (!a && !c);
    }
}

/*
((!a && b) || (!b && c) || (a && c) || (!a && !c) || (b && c))
Igazságtábla:   Karnaugh tábla:     Megoldás: (!a && !b && c) || (a && !b && c) = !b && c
a b c f                                       (!a && b && c) || (a && b && c) = b && c
0 0 0 1            1 1 1 1                    (!b && c) || (b && c) || (!a && !b && !c) || (!a && b && !c)
0 0 1 1         a| 0 1 1 0                    =>
0 1 0 1                -b-                    c || (!a && !b && !c) || (!a && b && !c)
0 1 1 1              -c-                      => c || (!a && !c)
1 0 0 0
1 0 1 1
1 1 0 0
1 1 1 1
*/
