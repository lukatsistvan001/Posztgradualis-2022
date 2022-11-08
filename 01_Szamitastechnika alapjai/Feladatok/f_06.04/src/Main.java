import java.util.Scanner;

/*
Rendes palindrom teszt: Írjon programot amely beolvas egy karakterláncot és arról eldönti, hogy
palindróm-e (egy karakterlánc akkor palindróm, ha visszafelé olvasva is ugyan az mint előre, pl:
Rád rohan a hordár). A kis- és nagybetűk ne jelentsenek különbséget, valamint a szóközök se
számítsanak.
Olyan megoldást adjon, amiben nem használ csak egy karakterláncot, az eredeti bemenetet
(újabb karakterláncokba való átalakítás nélkül oldja meg a feladatot).
*/

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Add be a karakterláncot: ");
        String sKarakterlanc = sc.nextLine();

        boolean bPalindrom = true;
        int iElsoPozicio = 0;
        int iHatsoPozicio = sKarakterlanc.length() - 1;

        while ((bPalindrom) && (iElsoPozicio < iHatsoPozicio)) {
            if (sKarakterlanc.charAt(iElsoPozicio) == ' ') {
                iElsoPozicio++;
                continue;
            }
            if (sKarakterlanc.charAt(iHatsoPozicio) == ' ') {
                iHatsoPozicio--;
                continue;
            }
            if (sKarakterlanc.toUpperCase().charAt(iElsoPozicio) != sKarakterlanc.toUpperCase().charAt(iHatsoPozicio))
                bPalindrom = false;
            iElsoPozicio++;
            iHatsoPozicio--;
        }

        if (bPalindrom)
            System.out.println("Palindrom");
        else
            System.out.println("Newm palindrom.");
    }
}