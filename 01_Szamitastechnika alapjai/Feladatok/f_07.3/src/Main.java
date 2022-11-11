/*
A HBC parkolója akkor nyit(na) sorompót, ha ismeri a telefonszámunkat, vagy felismeri az autó
rendszámát. Felhasználva a
boolean telefonszamIsmert = ....;
boolean rendszamIsmert = …;
változókat, fogalmazd meg a sorompónyitási feltételt két féle képpen.
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

    private static boolean feltetelFeladatban(boolean telefonszamIsmert, boolean rendszamIsmert){
        if (telefonszamIsmert || rendszamIsmert)
            return true;
        else
            return false;
    }

    private static boolean atalakitasHelyes(boolean telefonszamIsmert, boolean rendszamIsmert){
        return feltetelFeladatban(telefonszamIsmert,rendszamIsmert) == feltetelAtalakitva(telefonszamIsmert,rendszamIsmert);
    }

    private static boolean feltetelAtalakitva(boolean telefonszamIsmert, boolean rendszamIsmert){
        return !(!telefonszamIsmert && !rendszamIsmert);
    }
}

// (a || b) = !(!a && !b)