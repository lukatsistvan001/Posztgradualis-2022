//package com.company;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        // Adj, király, katonát!
        // generálunk annyi játékost, ahánnyal játszani szeretnénk
        String[] playerNames = generatePlayers(4);

        // Valasszuk sét a diákokat két csapatra (A és B).
        String[] teamA = new String[playerNames.length / 2];
        for (int i = 0; i < playerNames.length / 2; i++) {
            teamA[i] = playerNames[i];
        }
        String[] teamB = new String[playerNames.length - playerNames.length / 2];
        for (int i = playerNames.length / 2; i < playerNames.length; i++) {
            teamB[i - playerNames.length / 2] = playerNames[i];
        }

        // véletlen generátor a játékhoz
        Random r = new Random();
        // annak a valószínűsége, hogy az A csapat játékosa átszakítja a B csapat láncát
        double probabilityOfBreakingThrouh = 0.6;

        // a játék addig megy, amíg mindkét csapatban van még két játékos (van kötés amit el lehet szakítani)
        while (teamA.length >= 2 && teamB.length >= 2) {
            int tamado = r.nextInt(0, teamA.length);
            int vedo = r.nextInt(0, teamB.length - 1);
            double eredmeny = r.nextDouble(0, 1);
            if (eredmeny < probabilityOfBreakingThrouh) {
                int kiAllAt = r.nextInt(0, 2);
                if (kiAllAt == 0) {
                    teamA = addPlayerToTeam(teamA, teamB[vedo]);
                    teamB = removePlayerFromTeam(teamB, vedo);
                } else {
                    teamA = addPlayerToTeam(teamA, teamB[vedo + 1]);
                    teamB = removePlayerFromTeam(teamB, vedo + 1);
                }
            } else {
                teamB = addPlayerToTeam(teamB, teamA[tamado]);
                teamA = removePlayerFromTeam(teamA, tamado);
            }

        }
        if (playerNames.length < 4)
            System.out.println("Nincs elég játékos.");
        else if (teamA.length > 2)
            System.out.println("Az A csapat nyert.");
        else
            System.out.println("A B csapat nyert.");
    }

    /**
     * Hozzáad egy játékost egy csapathoz.
     *
     * @param team      A kiegészítendő csapat, amiben nincs benne az új tag.
     * @param newPlayer Az új tag neve.
     * @return A kiegészített csapat.
     */
    private static String[] addPlayerToTeam(String[] team, String newPlayer) {
        String[] ret = new String[team.length + 1];
        for (int i = 0; i < team.length; i++) {
            ret[i] = team[i];
        }
        ret[ret.length - 1] = newPlayer;
        return ret;
    }

    /**
     * Kivesz egy játékost az adott csapatból.
     *
     * @param team              A fogyó csapat.
     * @param playerPosToRemove A kivett játékos pozíciója a csapatban.
     * @return Az egyel kisebb csapat.
     */
    private static String[] removePlayerFromTeam(String[] team, int playerPosToRemove) {
        String[] ret = new String[team.length - 1];
        for (int i = 0; i < team.length - 1; i++) {
            if (i < playerPosToRemove)
                ret[i] = team[i];
            else
                ret[i] = team[i + 1];
        }
        return ret;
    }

    /**
     * Készít egy játékoslistát egyedi játékosnevekkel.
     *
     * @param playerCount
     * @return
     */
    private static String[] generatePlayers(int playerCount) {
        String[] players = new String[playerCount];

        int padLength = Integer.toString(playerCount - 1).length();

        for (int i = 0; i < players.length; i++) {
            players[i] = "";
            for (int k = 0; k < padLength - Integer.toString(i).length(); k++) {
                players[i] += ' ';
            }
            players[i] += Integer.toString(i);
        }

        return players;
    }
}
