import java.util.Scanner;

import static java.lang.Math.sqrt;

/*
Kérj be két számot, majd kérd be, hogy számtani vagy mértani középarányost szeretnénk
számolni. Számold és írd ki a kért művelet eredméyét.
*/

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Adj meg két számot: ");
        double dNum1 = sc.nextInt();
        double dNum2 = sc.nextInt();
        sc.nextLine();

        System.out.println("számtani középarányos kiszámítása - press 1.");
        System.out.println("mértani középarányos kiszámítása - press 2.");
        int iValasztas = sc.nextInt();

        switch (iValasztas){
            case 1:
                System.out.println("A " + dNum1 + " és " + dNum2 + " számtani középarányosa: " + (dNum1 + dNum2) / 2);
                break;
            case 2:
                System.out.println("A " + dNum1 + " és " + dNum2 + " mértani középarányosa: " + sqrt(dNum1 * dNum2));
                break;
            default:
                System.out.println("1 vagy 2 volt a lehetséges választás. Így jártál.");
        }
    }
}