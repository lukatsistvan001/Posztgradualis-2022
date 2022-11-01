import java.util.Scanner;

// Olvass be két 8 bites egész számot és írd ki, egész mondatban, hogy melyik a nagyobb.
// (pl.: 5 és 6 esetén: 6 nagyobb mint 5. 5 és 5 esetén: 5 egyenlő 5.)

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Add meg az első egész számot: ");
        int iNum1 = sc.nextInt();
        sc.nextLine();
        System.out.print("Add meg a második egész számot: ");
        int iNum2 = sc.nextInt();
        sc.nextLine();

        if (iNum1 > iNum2){
            System.out.println(iNum1 + " nagyobb, mint " + iNum2 + ".");
        }
        if (iNum1 < iNum2){
            System.out.println(iNum2 + " nagyobb, mint " + iNum1 + ".");
        }
        if (iNum1 == iNum2){
            System.out.println(iNum1 + " egyenlő " + iNum2 + ".");
        }
    }
}