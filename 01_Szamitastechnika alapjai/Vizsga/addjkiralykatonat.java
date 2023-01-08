package com.company;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        // Adj, király, katonát!
        // generálunk annyi játékost, ahánnyal játszani szeretnénk
        String[] playerNames = generatePlayers(13);

        // Valasszuk sét a diákokat két csapatra (A és B).
        String[] teamA = {};
        String[] teamB = {};

        // véletlen generátor a játékhoz
        Random r = new Random();
        // annak a valószínűsége, hogy az A csapat játékosa átszakítja a B csapat láncát
        double probabilityOfBreakingThrouh = 0.6;

        // a játék addig megy, amíg mindkét csapatban van még két játékos (van kötés amit el lehet szakítani)

    }

    /**
     * Hozzáad egy játékost egy csapathoz.
     * @param team A kiegészítendő csapat, amiben nincs benne az új tag.
     * @param newPlayer Az új tag neve.
     * @return A kiegészített csapat.
     */
    private static String[] addPlayerToTeam(String[] team, String newPlayer) {
        String[] ret = new String[team.length +1];
        for (int i = 0; i < team.length; i++) {
            ret[i] = team[i];
        }
        ret[ret.length-1] = newPlayer;
        return  ret;
    }

    /**
     * Kivesz egy játékost az adott csapatból.
     * @param team A fogyó csapat.
     * @param playerPosToRemove A kivett játékos pozíciója a csapatban.
     * @return Az egyel kisebb csapat.
     */
    private static String[] removePlayerFromTeam(String[] team, int playerPosToRemove) {
        String[] ret = new String[0];

        return  ret;
    }

    /**
     *     Készít egy játékoslistát egyedi játékosnevekkel.
     * @param playerCount
     * @return
     */
    private static String[] generatePlayers(int playerCount) {
        String[] players = new String[playerCount];

        int padLength = Integer.toString (playerCount-1).length();

        for (int i = 0; i < players.length; i++) {
            players[i] = "";
            for (int k = 0; k < padLength - Integer.toString(i).length(); k++) {
                players[i] += ' ';
            }
            players[i] +=  Integer.toString(i);
        }

        return  players;
    }
}
