package Feladat3;

/*
Készítsünk egy programot, ami egy kereten belül megjelenít egy gombot, egy
szövegmezőt és három címkét. A program soronként beolvassa egy szöveges
állomány tartalmát. Az állomány nevét a parancssor argumentumaként adjuk meg
(amennyiben nem adunk meg állománynevet, vagy nem sikerül megnyitni az illető
állományt, a program figyelmezteti a felhasználót, és a továbbiakban egy
alapértelmezett állománynevet használ).
Az állományból kiolvasott sorokat egy végrehajtási szál sorban kiírja az egyik címkére
rövid (4-5 másodperces) időközönként frissítve azt.
A felhasználónak lehetősége van szöveget beírni a szövegmezőbe, és amennyiben a
beírás után ENTER-t nyom, vagy a gombra kattint, a program ellenőrzi, hogy a
mezőbe beírt szöveg megegyezik-e a címke aktuális tartalmával. A címke és a
szövegmező tartalmának összehasonlítása alapján a program változtatja a „jó” és
„rossz” válaszoknak megfelelő számlálókat, és azok értékei megjelennek a második,
illetve a harmadik címkében. Minden ellenőrzés után töröljük a szövegmező
tartalmát.
A program egy adott megállási feltételig fut (például addig, amíg a jó válaszok száma
meghaladja a 10-et), ezért, amennyiben ez szükséges, a végrehajtási szál elölről
kezdheti a szövegek kiírását.
*/

import javax.swing.*;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        File file;
        if (args.length != 1) {
            file = new File("data.txt");
        } else {
            file = new File(args[0]);
        }

        if (!file.canRead() || !file.exists()) {
            System.out.println("IO ERROR");
            System.exit(0);
        }

        MainFrame mf = new MainFrame(file);
        mf.setBounds(10,10,800,600);
        mf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mf.setVisible(true);
    }
}