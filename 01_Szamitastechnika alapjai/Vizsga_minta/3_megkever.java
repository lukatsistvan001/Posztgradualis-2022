package com.company;

public class Main {

    public static void main(String[] args) {
        // false esetén kis tesztadatot ad a függvény
        String[] cards = generateDeck(true);


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