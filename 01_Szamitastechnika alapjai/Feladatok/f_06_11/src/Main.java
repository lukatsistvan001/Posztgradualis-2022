import java.util.Scanner;

/*
Írj egy programot, amely egy karakterláncot kér be. Majd minden olyan betüről, ami legalább
kétszer szerepel, kiírja, hogy az hányszor szerepelt. Pl: „thequickbrownfoxjumpsoverthelazydog”
esetén o 4, e 3, u 2, h 2, r 2, t 2. Bónuszpontért lehet előfordulási gyakoriság szerint csökkenő
sorrendben kiírni.
*/

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Add be a karakterláncot: ");
        String sKarakterlanc = sc.nextLine();

        char cVizsgaltBetu;
        int iBetuElofordulasa;
        String sMindenBetuCsakEgyszer = "";

        for (int i = 0; i <= sKarakterlanc.length() - 1; i++) {
            cVizsgaltBetu = sKarakterlanc.charAt(i);
            if ((sMindenBetuCsakEgyszer.indexOf(cVizsgaltBetu) < 0) &&
                    (sKarakterlanc.indexOf(cVizsgaltBetu, sKarakterlanc.indexOf(cVizsgaltBetu) + 1) > -1))
                sMindenBetuCsakEgyszer = sMindenBetuCsakEgyszer + cVizsgaltBetu;
        }

        int karakterekSzama = sMindenBetuCsakEgyszer.length();
        char[] karakterek = new char[karakterekSzama];
        int[] elofordulasok = new int[karakterekSzama];

        for (int i = 0; i <= karakterekSzama - 1; i++)
            karakterek[i] = sMindenBetuCsakEgyszer.charAt(i);

        for (int i = 0; i <= karakterekSzama - 1; i++) {
            iBetuElofordulasa = 0;
            for (int j = 0; j <= sKarakterlanc.length() - 1; j++) {
                if (sMindenBetuCsakEgyszer.charAt(i) == sKarakterlanc.charAt(j))
                    iBetuElofordulasa++;
            }
            elofordulasok[i] = iBetuElofordulasa;
        }

        for (int i = 0; i <= karakterekSzama - 1; i++)
            System.out.println(karakterek[i] + " " + elofordulasok[i]);

        //Bónuszpontért:
        System.out.println("Bónuszpontért:");
        int iLegTobb = 0;
        int iKiirandoKarakterSorszam = 0;
        String sMarKiirva = "";
        for (int i = 0; i <= karakterekSzama - 1; i++) {
            iLegTobb = 0;
            for (int j = 0; j <= karakterekSzama - 1; j++) {
                if ((elofordulasok[j] > iLegTobb) && (sMarKiirva.indexOf(karakterek[j]) == -1)) {
                    iLegTobb = elofordulasok[j];
                    iKiirandoKarakterSorszam = j;
                }
            }
            System.out.println(karakterek[iKiirandoKarakterSorszam] + " " + elofordulasok[iKiirandoKarakterSorszam]);
            sMarKiirva = sMarKiirva + karakterek[iKiirandoKarakterSorszam];
        }
    }
}