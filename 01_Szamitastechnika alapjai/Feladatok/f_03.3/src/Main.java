import java.util.Scanner;

/*
Írj programot amiben a felhasnzálónak lehetősége van megadni, hogy egy kör sugarát vagy
átmérőjét szeretné megadni. Ezek után kérd be a választott adatot, majd számold ki a kör területét
és írd ki.
*/

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Ha a kör sugarát szeretnéd megadni - press 1 ");
        System.out.println("Ha a kör átmérőjét szeretnéd megadni - press 2 ");
        int iSugarVagyAtmero = sc.nextInt();
        sc.nextLine();

        while ((iSugarVagyAtmero != 1) && (iSugarVagyAtmero != 2)) {
            System.out.print("1 vagy 2? ");
            iSugarVagyAtmero = sc.nextInt();
            sc.nextLine();
        }

        if (iSugarVagyAtmero == 1) {
            System.out.print("Add meg a kör sugarát: ");
            int iSugar = sc.nextInt();
            sc.nextLine();
            System.out.print("A kör területe: " + iSugar * iSugar * Math.PI);
        } else {
            System.out.print("Add meg a kör átmérőjét: ");
            int iAtmero = sc.nextInt();
            sc.nextLine();
            System.out.print("A kör területe: " + (iAtmero / 2) * (iAtmero / 2) * Math.PI);
        }
    }
}