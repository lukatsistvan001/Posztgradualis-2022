import java.util.Scanner;

/*
Írj egy programot ami bekér két karakterláncot, majd kiírja, hogy a második karakterlánc hánszor
szerepel az elsőben. Ezt kétféleképpen is számolja meg:
    i. Átfedésekkel: pl: a „abrabrabra”-ban 3-szor szerepel az „abra”, ha átfedéseket
        megengedünk.
    ii. Átfedések nélkül: pl: a „abrabrabra”-ban 2-szor szerepel az „abra”, ha átfedéseket nem
        engedünk meg.
*/

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Add meg az első karakterláncot: ");
        String sElso = sc.nextLine();
        System.out.print("Add meg a második karakterláncot: ");
        String sMasodik = sc.nextLine();

        //i. Átfedésekkel:
        int iHanyszor = 0;
        for (int i = 0; i < sElso.length() - 1; i++) {
            if (sElso.indexOf(sMasodik, i) > -1) {
                iHanyszor++;
                i = sElso.indexOf(sMasodik, i);
            }
        }
        System.out.println("A második karakterlánc átfedéssel " + iHanyszor + " szerepel.");

        //ii. Átfedések nélkül:
        iHanyszor = 0;
        for (int i = 0; i < sElso.length() - 1; i++) {
            if (sElso.indexOf(sMasodik, i) > -1) {
                iHanyszor++;
                i = sElso.indexOf(sMasodik, i) + sMasodik.length() - 1;
            }
        }
        System.out.println("A második karakterlánc átfedés nélkül " + iHanyszor + " szerepel.");
    }
}