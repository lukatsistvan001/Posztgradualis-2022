import java.util.Scanner;

/*Kérjen be egy karaktert és írja ki a nagybetűsített változatát (ne használj beépített függvényeket,
hanem a karakterek kódja alapján programozz).*/

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Adj meg egy karaktert: ");
        char cKarakter = sc.next().charAt(0);

        if ((cKarakter >= 97) && (cKarakter <= 122)) {
            System.out.println((char) (cKarakter - 32));
        }
    }
}