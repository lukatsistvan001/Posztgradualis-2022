import java.util.Scanner;

/*Kérjen be egy számot 1 és 10 között és írja ki, hogy helló annyiszor. Nem megfelelő szám esetén
írjuk ki, hogy ejnye bejnye.*/

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Adj meg egy 1 és 10 közötti számot: ");
        int iSzam = sc.nextInt();
        sc.nextLine();

        switch (iSzam) {
            case 1:
                System.out.println("Hello.");
                break;
            case 2:
                System.out.println("Hello, hello.");
                break;
            case 3:
                System.out.println("Hello, hello, hello.");
                break;
            case 4:
                System.out.println("Hello, hello, hello, hello.");
                break;
            case 5:
                System.out.println("Hello, hello, hello, hello, hello.");
                break;
            case 6:
                System.out.println("Hello, hello, hello, hello, hello, hello.");
                break;
            case 7:
                System.out.println("Hello, hello, hello, hello, hello, hello, hello.");
                break;
            case 8:
                System.out.println("Hello, hello, hello, hello, hello, hello, hello, hello.");
                break;
            case 9:
                System.out.println("Hello, hello, hello, hello, hello, hello, hello, hello, hello.");
                break;
            case 10:
                System.out.println("Hello, hello, hello, hello, hello, hello, hello, hello, hello, hello.");
                break;
            default:
                System.out.println("Ejnye bejnye.");
                break;
        }
    }
}