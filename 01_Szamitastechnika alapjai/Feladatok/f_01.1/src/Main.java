import java.util.Scanner;

// Olvass be két egész számot és írd ki a
//  a. összegüket
//  b. különbségüket
//  c. szorzatukat
//  d. maradékos osztási hányadosukat
//  e. osztási maradékukat

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Add meg az első egész számot:");
        int iNum1 = sc.nextInt();
        System.out.print("Add meg a második egész számot:");
        int iNum2 = sc.nextInt();

        System.out.println("A két szám összege: " + (iNum1 + iNum2));
        System.out.println("A két szám különbsége: " + (iNum1 - iNum2));
        System.out.println("A két szám szorzata: " + (iNum1 * iNum2));
        System.out.println("A két szám maradékos osztási hányadosa: " + (iNum1 / iNum2));
        System.out.println("A két szám osztási maradéka: " + (iNum1 % iNum2));

    }
}