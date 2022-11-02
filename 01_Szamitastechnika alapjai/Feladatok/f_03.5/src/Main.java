import java.util.Scanner;

/*Kérjen be egy karaktert és döntse el, hogy az kisbetű, nagybetű vagy szám (ne használj beépített
függvényeket, hanem a karakterek kódja alapján programozz).*/

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Adj meg egy karaktert: ");
        char cKarakter = sc.next().charAt(0);

        if ((cKarakter >= 65) && (cKarakter <= 90))
            System.out.println("Nagybetű.");
        if ((cKarakter >= 97) && (cKarakter <= 122))
            System.out.println("Kisbetű.");
        if ((cKarakter >= 48) && (cKarakter <= 57))
            System.out.println("Szám.");
    }
}