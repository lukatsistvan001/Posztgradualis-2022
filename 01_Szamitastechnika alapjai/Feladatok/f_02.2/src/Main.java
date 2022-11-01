import java.util.Scanner;

/*
Írj egy programot, ami beolvas egy egész számot, binárisan összevagyolja 512-vel, majd az
eredményt kíjra.
Ennek a programnak a segítségével adj meg 1-1 olyan különböző számot ami:
    a. Kisebb mint 512 és a program kimenete 548.
    b. Nagyobb mint 512 és a program kimenete 1536.
*/

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Adj meg egy egész számot: ");
        int iNum = sc.nextInt();
        sc.nextLine();

        System.out.println("Az 512-vel való összevagyolás utáni eredmény: " + (iNum | 512));

/*Megoldás:
       0..0010 0000 0000 = 512
    a. 0..0010 0010 0100 = 548
       0..0000 0010 0100 = 36
    b. 0..0110 0000 0000 = 1536
       0..0100 0000 0000 = 1024
*/
    }
}