import java.util.Scanner;

/*
Írjon egy kávé autómata tesztelését segítő alkalmazást. A program úgy működik, hogy a
képernyőre kiír egy 9 menüpontból álló programot:
    a. Espresso, rövid, cukorral – 1 lej
    b. Espresso, rövid, tejjel és cukorral – 1 lej
    c. Espresso, hosszu, cukorral – 1 lej
    d. Espresso, hosszu, tejjel és cukorral – 1 lej
    e. Cappuccino - 1 lej
    f. Olasz Cappuccino - 1 lej
    g. Moccaccino - 2 lej
    h. Forró csoki - 2 lej
    i. Kilépés
A menü kiírása után bekéri az opció menükódját, majd kiírja, hogy „Ön egy XXXX-t vett ZZZZ
lejért. Várja meg míg elkészül és fogyassza egészséggel.”
*/

/*
Egészítse ki a feladatot egy olyan adminisztrátor menüvel, amiben meg lehet változtatni az
italok árát. Ha ezt a menüt kéri a felhasználó, akkor újra írjuk ki a lehetséges italokat, amiből ki
lehet választani, hogy melyik ára változzon meg. Ez után meg lehet adni az új árat, majd
visszalépünk a főmenübe. Innen a változott árakkal íródik ki a jókívánság
*/

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static int menupontokSzama = 10;
    private static String fomenuMenupontok = "abcdefghij";
    private static String adminisztratoriMenupontok = "abcdefgh";
    private static String[][] menu = new String[menupontokSzama][3];

    public static void main(String[] args) {
        menu[0][0] = "a";
        menu[0][1] = "Espresso, rövid, cukorral";
        menu[0][2] = "1";
        menu[1][0] = "b";
        menu[1][1] = "Espresso, rövid, tejjel és cukorral";
        menu[1][2] = "1";
        menu[2][0] = "c";
        menu[2][1] = "Espresso, hosszu, cukorral";
        menu[2][2] = "1";
        menu[3][0] = "d";
        menu[3][1] = "Espresso, hosszu, tejjel és cukorral";
        menu[3][2] = "1";
        menu[4][0] = "e";
        menu[4][1] = "Cappuccino";
        menu[4][2] = "1";
        menu[5][0] = "f";
        menu[5][1] = "Olasz Cappuccino";
        menu[5][2] = "1";
        menu[6][0] = "g";
        menu[6][1] = "Moccaccino";
        menu[6][2] = "2";
        menu[7][0] = "h";
        menu[7][1] = "Forró csoki";
        menu[7][2] = "2";
        menu[8][0] = "i";
        menu[8][1] = "Adminisztrátori hozzáférés";
        menu[8][2] = "";
        menu[9][0] = "j";
        menu[9][1] = "Kilépés";
        menu[9][2] = "";

        int kivalasztottMenupont;
        do {
            System.out.println("Menü:");
            menuKiirasa(menupontokSzama);

            kivalasztottMenupont = menupontValasztasa();

            if (kivalasztottMenupont < menupontokSzama - 2) {
                System.out.println("Ön egy " + menu[kivalasztottMenupont][1] + "-t vett "
                        + menu[kivalasztottMenupont][2] + " lejért. Várja meg míg elkészül és fogyassza egészséggel.");
                break;
            }
            if (kivalasztottMenupont == 8) {
                System.out.println("Adminisztrátori menü:");
                adminisztratoriTeendok();
                menuKiirasa(menupontokSzama);
            }
        } while (kivalasztottMenupont != menupontokSzama-1);
    }

    private static void adminisztratoriTeendok() {
        int adminisztratoriMenupontokSzama = menupontokSzama - 2;
        menuKiirasa(adminisztratoriMenupontokSzama);
        System.out.print("Válazd ki annak az italnak a menüpontját, melynek az ára megváltozik: ");
        String valasztas = sc.nextLine();
        int kivalasztottItal = valasztasKiertekelese(valasztas, adminisztratoriMenupontok);
        System.out.print("A " + menu[kivalasztottItal][1] + " új ára: ");
        menu[kivalasztottItal][2] = sc.nextLine();
    }

    private static void menuKiirasa(int kiirandoMenupontokSzama) {
        for (int i = 0; i < kiirandoMenupontokSzama; i++) {
            if (kiirandoMenupontokSzama == menupontokSzama && i < menupontokSzama - 2)
                System.out.println(menu[i][0] + ". " + menu[i][1] + " - " + menu[i][2] + " lej");
            else if (kiirandoMenupontokSzama == menupontokSzama)
                System.out.println(menu[i][0] + ". " + menu[i][1]);
            else
                System.out.println(menu[i][0] + ". " + menu[i][1] + " - " + menu[i][2] + " lej");
        }
    }

    private static int menupontValasztasa() {
        System.out.print("Válassz menüpontot: ");
        String valasztas = sc.nextLine();
        return valasztasKiertekelese(valasztas, fomenuMenupontok);
    }

    private static int valasztasKiertekelese(String valasztas, String menupontok) {
        while (!menupontok.contains(valasztas)) {
            System.out.println("Nem létező menüpont, próbáld újra.");
            System.out.print("Válassz menüpontot: ");
            valasztas = sc.nextLine();
        }
        return menupontok.indexOf(valasztas);
    }
}