import java.util.Scanner;

/*
Kérjen be egy számot és írja ki, hogy helló annyiszor. (Emlékezzünk a régi szenvedős megoldásra
és értékeljük a haladást.)
*/

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Add meg a számot: ");
        int iHanyszor = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < iHanyszor; i++)
            System.out.println("Hello.");
    }
}