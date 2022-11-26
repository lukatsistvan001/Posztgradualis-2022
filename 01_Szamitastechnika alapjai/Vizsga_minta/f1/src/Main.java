/*
Írjon egy programot amiben deklarál egy globális dupla pontos változót, aminek kezdeti
értéke 1. Ezen kívül írjon egy függvényt ami egy parancsot és egy számot vár paraméterként és
ezek függvényében értelemszerűen frissíti a globális változó értékét. Az érvényes parancsok:
szoroz, oszt, osszead. A main függvényben hívja is meg a függvényt néhány tetszőleges
paraméterezéssel.
*/

import java.util.Scanner;

public class Main {
    private static double valtozo = 1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Add meg a parancsot (szoroz / oszt / osszead): ");
        String parancs = sc.nextLine();
        while ((!parancs.equals("szoroz")) && (!parancs.equals("oszt")) && (!parancs.equals("osszead"))) {
            System.out.print("Érvénytelen parancs. Add meg újra (szoroz / oszt / osszead): ");
            parancs = sc.nextLine();
        }
        System.out.print("Add meg a számot: ");
        int szam = sc.nextInt();

        fuggveny(parancs, szam);
        System.out.println("Az eredmény: " + valtozo);
    }

    private static void fuggveny(String parancs, int szam) {
        switch (parancs) {
            case "szoroz":
                valtozo = valtozo * szam;
                break;
            case "oszt":
                valtozo = valtozo / szam;
                break;
            case "osszead":
                valtozo = valtozo + szam;
                break;
        }
    }
}