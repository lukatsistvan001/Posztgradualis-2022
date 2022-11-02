import java.util.Scanner;

/*
Kérjen be egy számot 1 és 7 közöt és írja ki, hogy az a szám a hét melyik napjának felel meg (hétfő,
kedd, ...). Ha nem megfelelő számot adott meg a felhasználó, írd ki, hogy ejnye bejnye.
*/

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Adj meg egy 1 és 7 közötti számot: ");
        int iSzam = sc.nextInt();
        sc.nextLine();

        switch (iSzam) {
            case 1:
                System.out.println("A megadott szám a hétfőnek felel meg.");
                break;
            case 2:
                System.out.println("A megadott szám a keddnek felel meg.");
                break;
            case 3:
                System.out.println("A megadott szám a szerdának felel meg.");
                break;
            case 4:
                System.out.println("A megadott szám a csütörtöknek felel meg.");
                break;
            case 5:
                System.out.println("A megadott szám a pénteknek felel meg.");
                break;
            case 6:
                System.out.println("A megadott szám a szombatnak felel meg.");
                break;
            case 7:
                System.out.println("A megadott szám a vasárnapnak felel meg.");
                break;
            default:
                System.out.println("Ejnye, bejnye.");
        }
    }
}