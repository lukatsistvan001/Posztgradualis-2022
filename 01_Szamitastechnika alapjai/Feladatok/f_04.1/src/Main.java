import java.util.Scanner;

/*
Olvass be egy karakterláncot, majd írd ki
    a. a hosszát
    b. nagybetűsített alakájt (keress beépített függvényt erre)
    c. kisbetüsített alakját(keress beépített függvényt erre)
*/

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Adj meg egy karakterláncot: ");
        String s = sc.nextLine();

        System.out.println("A karakterlánc hossza: " + s.length());
        System.out.println("A karakterlánc nagybetűsített alakja: " + s.toUpperCase());
        System.out.println("A karakterlánc kisbetűsített alakja: " + s.toLowerCase());
    }
}