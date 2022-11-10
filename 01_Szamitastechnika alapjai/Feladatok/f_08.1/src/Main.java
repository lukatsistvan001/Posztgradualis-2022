import java.util.Scanner;

/*
(padding) Írj egy függvényt ami egy karakterláncot kiegészít adott hosszúságúra. A függvény
paraméterként várja a kiegészítendő karakterláncot, a kiegészítéshez használatos karaktert, az
elérni kívánt hosszt valamint azt, hogy a karakterlánc elejére vagy végére kerüljenek a kitöltő
karakterek.
*/

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Add meg a kiegészítendő karakterláncot: ");
        String karakterlanc = sc.nextLine();

        String karakter;
        do {
            System.out.print("Add meg a kiegészítéshez használatos karaktert: ");
            karakter = sc.nextLine();
            if (karakter.length() != 1)
                System.out.println("Egy darab karaktert adj meg.");
        } while (karakter.length() != 1);

        System.out.print("Milyen hosszú legyen a karakterlánc? ");
        int hossz = sc.nextInt();
        sc.nextLine();

        int hova;
        do {
            System.out.println("A karakterlánc elejére fűzzük a karaktert. - press 1");
            System.out.println("A karakterlánc végére fűzzük a karaktert. - press 2");
            hova = sc.nextInt();
        } while (hova != 1 && hova != 2);

        System.out.println(hozzafuz(karakterlanc, karakter, hossz, hova));
    }

    private static String hozzafuz(String karakterlanc, String karakter, int hossz, int hova) {
        while (karakterlanc.length() < hossz) {
            if (hova == 1)
                karakterlanc = karakter + karakterlanc;
            else
                karakterlanc = karakterlanc + karakter;
        }
        return karakterlanc;
    }
}