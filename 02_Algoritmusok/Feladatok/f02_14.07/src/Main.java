/*
Egy olyan világban mozgunk, ahol a koordináták egész számok. És utazni csak egy fura szabály
szerint lehet. Egy (𝑥, 𝑦) koordinátájú pontból csak az (𝑥, 𝑥 + 𝑦) és az (𝑥 + 𝑦, 𝑥) koordinátákra
lehet mozogni.
Írjon egy függvényt, ami eldönti, egy indulási és egy érkezési koordinátapárról, hogy az érkezési
pont elérhető-e az indulási helyről.
Pl.: indulási hely (2,10), érkezési hely (26,12) esetén (2, 10) → 2, 12) → (14, 12) → (26, 12)
egy megengedett útvonal. Indulási hely (2,10), érkezési hely (15,16) esetén nincs ilyen útvonal.
(Bónusz: írja is ki a lépések sorozatát. Visszafelé egyszerűbb, előre kicsit bonyolultabb.)
*/

public class Main {
    private static boolean talalat = false;
    private static String utvonal = "";
    private static String megoldas;

    public static void main(String[] args) {
        int indulasiX = 8;
        int indulasiY = 8;
        int erkezesiX = 1024;
        int erkezesiY = 1256;

        if (indulasiX <= erkezesiX && indulasiY <= erkezesiY)
            utvonal = utvonal + "(" + indulasiX + "," + indulasiY + ")→";
        if (indulasiX == erkezesiX && indulasiY == erkezesiY) {
            System.out.println("A két koordináta azonos.");
            return;
        }
        ellenorzes(indulasiX, indulasiY, erkezesiX, erkezesiY);
        if (talalat) {
            System.out.println("Megengedett útvonal.");
            System.out.println(megoldas);
        } else
            System.out.println("Nincs ilyen útvonal.");
    }

    private static boolean ellenorzes(int indulasiX, int indulasiY, int erkezesiX, int erkezesiY) {
        boolean zsakutca = true;
        if (indulasiX + indulasiY <= erkezesiX) {
            utvonal = utvonal + "(" + (indulasiX + indulasiY) + "," + indulasiY + ")→";
            zsakutca = ellenorzes(indulasiX + indulasiY, indulasiY, erkezesiX, erkezesiY);
        }
        if (indulasiX + indulasiY <= erkezesiY) {
            utvonal = utvonal + "(" + indulasiX + "," + (indulasiX + indulasiY) + ")→";
            zsakutca = ellenorzes(indulasiX, indulasiX + indulasiY, erkezesiX, erkezesiY);
        }
        if (indulasiX == erkezesiX && indulasiY == erkezesiY) {
            talalat = true;
            megoldas = utvonal.substring(0,utvonal.length()-1);
            return false;
        }
        if (zsakutca) {
            utvonal = utvonal.replace("(" + indulasiX + "," + indulasiY + ")→", "");
            return true;
        } else return false;
    }
}