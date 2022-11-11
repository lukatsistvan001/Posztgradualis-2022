/*
Fogalmazd át az:
if (!b && a) {…}
feltételt óly módon, hogy ne használjon && kapcsolatot. (Tipp: !!x = x.)
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
        if (!b && a)
            return true;
        else
            return false;
    }

    private static boolean atalakitasHelyes(boolean a, boolean b){
        return feltetelFeladatban(a,b) == feltetelAtalakitva(a,b);
    }

    private static boolean feltetelAtalakitva(boolean a, boolean b){
        return !(b || !a);
    }
}

/*
(!b && a) = !(b || !a)
*/
