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

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static final int menupontokSzama = 9;
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
        menu[8][1] = "Kilépés";
        menu[8][2] = "";

        int kivalasztottMenupont = menuKiirasa();

        if (kivalasztottMenupont < menupontokSzama - 1)
            System.out.println("Ön egy " + menu[kivalasztottMenupont][1] + "-t vett "
                    + menu[kivalasztottMenupont][2] + " lejért. Várja meg míg elkészül és fogyassza egészséggel.");
    }

    private static int menuKiirasa() {
        System.out.println("Menü:");
        for (int i = 0; i < menupontokSzama; i++) {
            if (i != menupontokSzama - 1)
                System.out.println(menu[i][0] + ". " + menu[i][1] + " - " + menu[i][2] + " lej");
            else
                System.out.println(menu[i][0] + ". " + menu[i][1]);
        }
        System.out.print("Válassz menüpontot: ");
        String valasztas = sc.nextLine();

        while (!valasztas.equals("a") &&
                !valasztas.equals("b") &&
                !valasztas.equals("c") &&
                !valasztas.equals("d") &&
                !valasztas.equals("e") &&
                !valasztas.equals("f") &&
                !valasztas.equals("g") &&
                !valasztas.equals("h") &&
                !valasztas.equals("i")) {
            System.out.println("Nem létező menüpont, próbáld újra.");
            System.out.print("Válassz menüpontot: ");
            valasztas = sc.nextLine();
        }

        switch (valasztas) {
            case "a":
                return 0;
            case "b":
                return 1;
            case "c":
                return 2;
            case "d":
                return 3;
            case "e":
                return 4;
            case "f":
                return 5;
            case "g":
                return 6;
            case "h":
                return 7;
            default:
                return 8;
        }
    }
}