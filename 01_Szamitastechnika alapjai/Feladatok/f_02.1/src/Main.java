import java.util.Scanner;

/*
Írj egy programot, ami beolvas egy egész számot, binárisan összeéseli 512-vel, majd az eredményt
        kíjra.
        Ennek a programnak a segítségével adj meg 3-3 olyan különböző számot ami:
        a. Kisebb mint 512 és a program kimenete 0.
        b. Nagyobb mint 512 és a program kimenete 512.
        c. Nagyobb mint 512 és a program kimenete 0.
*/

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Adj meg egy egész számot: ");
        int iNum = sc.nextInt();
        sc.nextLine();

        System.out.println("Az 512-vel való összeéselés után: " + (iNum & 512));

/*
Megoldás:
       0..0010 0000 0000 = 512
    a. 0..0000 0000 0000 = 0
       0..0000 0000 0001 = 1
       0..0000 0000 0010 = 2
    b. 0..0010 0000 0001 = 513
       0..0010 0000 0010 = 514
       0..0010 0000 0011 = 515
    c. 0..0100 0000 0000 = 1024
       0..0100 0000 0001 = 1025
       0..0100 0000 0011 = 1026
*/
    }
}