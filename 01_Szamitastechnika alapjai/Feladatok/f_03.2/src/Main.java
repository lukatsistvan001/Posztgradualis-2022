import java.util.Scanner;

import static java.lang.Math.sqrt;

/*
Kérj be két számot, majd kérd be, hogy számtani vagy mértani középarányost szeretnénk
számolni. Számold és írd ki a kért művelet eredméyét.
*/

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Add meg a két számot: ");
        int iNum1 = sc.nextInt();
        int iNum2 = sc.nextInt();
        sc.nextLine();

        System.out.println("számtani középarányos kiszámítása - press 1.");
        System.out.println("mértani középarányos kiszámítása - press 2.");
        int iSzamtani = sc.nextInt();

        while ((iSzamtani != 1) && (iSzamtani != 2)) {
            System.out.println("1 vagy 2?");
            iSzamtani = sc.nextInt();
        }
        if (iSzamtani == 1)
            System.out.println("A " + iNum1 + " és " + iNum2 + " számtani középarányosa: " + (iNum1 + iNum2) / 2);
        else System.out.println("A " + iNum1 + " és " + iNum2 + " mértani középarányosa: " + sqrt(iNum1 * iNum2));
    }
}