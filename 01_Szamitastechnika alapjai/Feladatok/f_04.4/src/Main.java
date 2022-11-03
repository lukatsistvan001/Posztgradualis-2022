import java.util.Scanner;

/*
Vizsgáld meg, hogy mi történik, ha a 20-at és a 30-at minden lehetséges típuskombinációban
összeadod (mindkettőt értelmezhetjük számként (int 20, double 20) vagy karakterláncként (“20”)).
Mely kombinációkat nem engedi a Java végrehajtani, mert nem tudja értelmezni? Amit enged,
azoknak mi az eredménye?
*/

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int iHusz = 20;
        int iHarmic = 30;
        double dHusz = 20;
        double dHarminc = 30;
        String sHusz = "20";
        String sHarminc = "30";

        System.out.println("Int + Int: " + (iHusz + iHarmic));
        System.out.println("Int + Double: " + (iHusz + dHarminc));
        System.out.println("Int + String: " + (iHusz + sHarminc));
        System.out.println("Double + Double: " + (dHusz + dHarminc));
        System.out.println("Double + String: " + (dHusz + sHarminc));
        System.out.println("String + String: " + (sHusz + sHarminc));
    }
}