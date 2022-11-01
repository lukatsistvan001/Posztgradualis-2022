import java.util.Scanner;

/*
Írj egy programot, ami beolvas egy egész számot, binárisan összekizáróvagyolja (XOR-olja) 512-
vel, majd az eredményt kíjra.
Ennek a programnak a segítségével adj meg olyan számokat ami:
    a. Kisebb mint 512 és a program kimenete 578.
    b. Nagyobb mint 512 és a program kimenete 1030.
    c. Nagyobb mint 512 és a program kimenete 1534
*/

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Adj meg egy egész számot: ");
        int iNum = sc.nextInt();
        sc.nextLine();

        System.out.println("Az eredmény az 512-vel való XOR után: " + (iNum ^ 512));

/*
Megoldás:
       0..0010 0000 0000 = 512
    a. 0..0010 0100 0010 = 578
       0..0000 0100 0010 = 66
    b. 0..0100 0000 0110 = 1030
       0..0110 0000 0110 = 1542
    c. 0..0101 1111 1110 = 1534
       0..0111 1111 1110 = 2046
*/
    }
}