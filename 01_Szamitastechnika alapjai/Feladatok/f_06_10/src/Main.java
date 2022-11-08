import java.util.Scanner;

/*
Írj egy programot ami bekér egy szöveget. Ezt a szöveget vesszők mentén feldarabolja, majd kiírja
az összes előforduló szót, de csak egyszer. Pl.: „alma, körte, alma, kukorica” bemenetre „alma,
körte, kukorica” íródik a képernyőre.
*/

/*
A feladat szövege illetve a példa nem pontos. A vesszővel való darabolásnál a szöközöket tárgyaljuk vagy sem?
Például: "alma" != " alma"
*/

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Add meg a szöveget: ");
        String sSzoveg = sc.nextLine();

        int iKovetkezoVesszoPozicio = 0;
        String sSzo;
        String sUjSzoveg = "";

        for (int i = 0; i <= sSzoveg.length() - 1; i++) {
            iKovetkezoVesszoPozicio = sSzoveg.indexOf(',', i);
            if (iKovetkezoVesszoPozicio > -1) {
                sSzo = sSzoveg.substring(i, iKovetkezoVesszoPozicio);
                if (!sUjSzoveg.contains(sSzo))
                    if (sUjSzoveg.length() == 0)
                        sUjSzoveg = sSzo;
                    else
                        sUjSzoveg = sUjSzoveg + "," + sSzo;
                i = iKovetkezoVesszoPozicio;
            } else {
                sSzo = sSzoveg.substring(i, sSzoveg.length());
                if (!sUjSzoveg.contains(sSzo))
                    if (sUjSzoveg.length() == 0)
                        sUjSzoveg = sSzo;
                    else
                        sUjSzoveg = sUjSzoveg + "," + sSzo;
                i = sSzoveg.length() - 1;
            }
        }

        System.out.println("Az új karakterlánc: " + sUjSzoveg);
    }
}