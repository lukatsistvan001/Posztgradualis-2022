import java.util.Scanner;

/*
Írj egy programot ami *-ig kér be neveket. Utána újra *-ig kér be karaktereket. Minden karakter
bekérése után kiírja azokat a neveket, amelyek tartalmazzák a megadott karaktert. Ez után
felajánlja az új karakter megadási lehetőségét.
*/

public class Main {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        String[] nevek = nevekBeolvasasa();

        karakterekBeolvasasaEsEllenorzese(nevek);
    }

    private static void karakterekBeolvasasaEsEllenorzese(String[] nevek) {
        String karakter = "";
        String valasz = "";
        do {
            System.out.print("Adj meg egy karaktert: ");
            karakter = sc.nextLine();
            if (karakter.length() > 1) {
                System.out.println("EGY karaktert.");
                continue;
            }
            if (karakter.equals("*"))
                continue;
            for (int i = 0; i < nevek.length; i++) {
                if (nevek[i].contains(karakter))
                    System.out.println(nevek[i]);
            }
            do {
                System.out.println("Szeretnél még beolvasni más karaktert? [Igen / Nem]");
                valasz = sc.nextLine();
                if (!valasz.equals("Igen") && !valasz.equals("Nem") && (!valasz.equals("*"))) {
                    System.out.println("\"Igen\" vagy \"Nem\"");
                }
            } while (!valasz.equals("Igen") && !valasz.equals("Nem"));
        } while (!karakter.equals("*") && !valasz.equals("Nem"));
    }

    private static String[] nevekBeolvasasa() {
        String adatok = "";
        String adatokHosszusagai = "";
        int adatokSzama = 0;
        String adat = "";
        do {
            System.out.print("Add meg a nevet: ");
            adat = sc.nextLine();
            if (!adat.equals("*")) {
                adatok = adatok + adat;
                adatokSzama++;
                adatokHosszusagai = adatokHosszusagai + "+" + adat.length();
            }
        } while (!adat.equals("*"));

        String[] nevekTomb = new String[adatokSzama];
        int nevEleje = 0;
        int nevVege = 0;
        int hossz = 0;
        int hosszPozicio = 1;
        for (int i = 0; i < adatokSzama; i++) {
            if (adatokHosszusagai.indexOf("+", hosszPozicio) != -1) {
                hossz = Integer.parseInt(adatokHosszusagai.substring(hosszPozicio, adatokHosszusagai.indexOf("+", hosszPozicio)));
                hosszPozicio = adatokHosszusagai.indexOf("+", hosszPozicio) + 1;
            } else
                hossz = Integer.parseInt(adatokHosszusagai.substring(hosszPozicio, adatokHosszusagai.length()));
            nevVege = nevEleje + hossz;
            nevekTomb[i] = adatok.substring(nevEleje, nevVege);
            nevEleje = adatok.indexOf(nevekTomb[i], nevEleje) + hossz;
        }
        return nevekTomb;
    }
}