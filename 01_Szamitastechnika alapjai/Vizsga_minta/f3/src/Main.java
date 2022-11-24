/*
Hozzon létre egy új projektet, majd az e-mailben kapott megkever.java tartalmát
másolja be a Main.java fájlba.
A kód egy pakli kártyát generál, vagy kisebb tesztadatot, ha az a szükséges. Egészítse ki a main
függvényt a következő lépésekkel
    a. (5 pont) Egy véletlenszerűen kiválasztott lapnál vágja ketté a paklit. Ennek
        eredményeként két olyan string tömbje kell előálljon amelyek együtes hossza
        megegyezik a pakli méretével, a pakli első fele az egyik tömbben, a másik fele a másik
        tömbben van.
    b. (10 pont) Írjon egy függvényt ami a két félpaklit összeteszi oly módon, hogy
        véletlenszerűen választ a nemüres félpaklik kizül, hogy melyikből veszi a következő
        lapot.
*/

//package com.company;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        // false esetén kis tesztadatot ad a függvény
        String[] cards = generateDeck(true);

        Random r = new Random();
        int veletlenSzam = r.nextInt(0, cards.length);
        String[] elsoPakli = new String[veletlenSzam];
        String[] masodikPakli = new String[cards.length - veletlenSzam];
        for (int i = 0; i < veletlenSzam; i++) {
            elsoPakli[i] = cards[i];
        }
        for (int i = veletlenSzam; i < cards.length; i++) {
            masodikPakli[i - veletlenSzam] = cards[i];
        }

        String[] kozosPakli = paklikOsszekeverese(elsoPakli, masodikPakli);
        System.out.println("Az új pakli:");
        for (int i = 0; i < kozosPakli.length; i++) {
            System.out.println(kozosPakli[i]);
        }
    }

    private static String[] paklikOsszekeverese(String[] elsoPakli, String[] masodikPakli) {
        Random r = new Random();
        int elsoPakliLapjainakSzama = elsoPakli.length;
        int masodikPakliLapjainakSzama = masodikPakli.length;
        int kozosPakliLapjainakSzama = 0;
        String[] kozosPakli = new String[elsoPakli.length + masodikPakli.length];
        int melyikPakli = r.nextInt(0, 2);
        while ((elsoPakliLapjainakSzama != 0) && (masodikPakliLapjainakSzama != 0)) {
            if ((melyikPakli == 0) && (elsoPakliLapjainakSzama != 0)) {
                kozosPakli[kozosPakliLapjainakSzama] = elsoPakli[0];
                kozosPakliLapjainakSzama++;
                for (int i = 0; i < elsoPakliLapjainakSzama - 1; i++) {
                    elsoPakli[i] = elsoPakli[i + 1];
                }
                elsoPakliLapjainakSzama--;
            } else if (masodikPakliLapjainakSzama != 0) {
                kozosPakli[kozosPakliLapjainakSzama] = masodikPakli[0];
                kozosPakliLapjainakSzama++;
                for (int i = 0; i < masodikPakliLapjainakSzama - 1; i++) {
                    masodikPakli[i] = masodikPakli[i + 1];
                }
                masodikPakliLapjainakSzama--;
            }
            melyikPakli = r.nextInt(0, 2);
        }
        if (elsoPakliLapjainakSzama != 0) {
            for (int i = 0; i < elsoPakliLapjainakSzama; i++) {
                kozosPakli[kozosPakliLapjainakSzama] = elsoPakli[i];
                kozosPakliLapjainakSzama++;
            }
        } else {
            for (int i = 0; i < masodikPakliLapjainakSzama; i++) {
                kozosPakli[kozosPakliLapjainakSzama] = masodikPakli[i];
                kozosPakliLapjainakSzama++;
            }
        }
        return kozosPakli;
    }

    /**
     * Generál egy pakli rendezett kártyát.
     *
     * @param fullDeck Ha igaz, akkor a teljes paklit generálja, hanem csak teszadatokat.
     * @return
     */
    private static String[] generateDeck(boolean fullDeck) {
        if (fullDeck) {
            String[] deck = new String[32];
            for (int i = 0; i < deck.length; i++) {
                deck[i] = getColor(i / 8) + " " + getCardId(i % 8);
            }
            return deck;
        } else {
            // valami tesztadat fejelsztéshez
            String[] dummyDeck = new String[5];
            for (int i = 0; i < dummyDeck.length; i++) {
                dummyDeck[i] = String.valueOf(i);
            }
            return dummyDeck;
        }
    }

    private static String getColor(int colorCode) {
        switch (colorCode) {
            case 0:
                return "piros";
            case 1:
                return "tök";
            case 2:
                return "makk";
            case 3:
                return "zöld";
        }
        // ez nem szép, de most ne foglalkozzunk vele
        return "";
    }

    private static String getCardId(int cardCode) {
        switch (cardCode) {
            case 0:
                return "alsó";
            case 1:
                return "felső";
            case 2:
                return "király";
            case 3:
                return "VII";
            case 4:
                return "VIII";
            case 5:
                return "IX";
            case 6:
                return "X";
            case 7:
                return "ász";
        }
        // ez nem szép, de most ne foglalkozzunk vele
        return "";
    }
}