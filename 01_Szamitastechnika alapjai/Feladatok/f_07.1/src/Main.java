/*
Fogalmazd át a következő igaz-hamis feltételt a lehető legrövidebb alakba:
if ((!a && !b) || (b && a) || (!b && a)){...}
*/

public class Main {
    public static void main(String[] args) {
        if (atalakitasHelyes(false, false)
            && atalakitasHelyes(false, true)
            && atalakitasHelyes(true, false)
            && atalakitasHelyes(true, true))
            System.out.println("Helyes az átalakítás.");
        else
            System.out.println("Nem helyes az átalakítás.");
    }

    private static boolean feltetelFeladatban(boolean a, boolean b){
        if ((!a && !b) || (b && a) || (!b && a))
            return true;
        else
            return false;
    }

    private static boolean atalakitasHelyes(boolean a, boolean b){
        return feltetelFeladatban(a,b) == feltetelAtalakitva(a,b);
    }

    private static boolean feltetelAtalakitva(boolean a, boolean b){
        return a || !b;
    }
}

/*
Igazságtábla:       Karnaugh tábla:     Összefüggések:
    a b f                 1 0           (!a && !b) || (a && !b) = !b
    0 0 1               a 1 1           !b || (a && b) = a || !b
    0 1 0                   b
    1 0 1
    1 1 1
*/
