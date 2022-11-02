import java.util.Scanner;

/*Kéjen be három számot, ami évszámot, hónap számot és nap számot jelent. Döntse el, hogy a
megadott adatok a 20. század valamelyik napját jelentik-e vagy sem.*/

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Adj meg három számot (év, hónap, nap): ");
        int iEv = sc.nextInt();
        int iHonap = sc.nextInt();
        int iNap = sc.nextInt();
        sc.nextLine();

        if (iEv / 100 == 19)
            System.out.println("Huszadik század.");
        else System.out.println("Nem huszadik század.");
    }
}