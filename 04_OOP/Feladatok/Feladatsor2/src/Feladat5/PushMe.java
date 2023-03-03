/*
Egy kereten belül helyezzünk el egy gombot, véletlenszerűen generált koordinátákra, a „Push me!”
felirattal. Amikor a felhasználó megpróbál a gombra kattintani (az egérmutató a gomb fölé kerül), a
gomb elmozdul (véletlenszerűen újrageneráljuk a koordinátákat, és áthelyezzük a gombot az új
koordinátákra). A feladatnak elkészíthetjük egy olyan változatát is, amikor tényleg nem lehetséges a
gomb lenyomása: az új koordináták generálásánál kiszűrjük annak a lehetőségét, hogy a gomb újra a
mutató alá kerüljön.
*/

package Feladat5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

public class PushMe extends Frame {

    private JButton button;
    private Random random;

    public PushMe() {
        button = new JButton("Push me!");
        random = new Random();
        setLayout(null);
        setBounds(10, 10, 1024, 768);
        button.setBounds(random.nextInt(Math.abs(getWidth() - 70)), random.nextInt(Math.abs(getHeight() - 30)), 100, 30);
        add(button);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                int newX = 0;
                int newY = 0;
                do {
                    newX = random.nextInt(Math.abs(getWidth() - 100));
                } while (newX == button.getX());
                do {
                    newY = random.nextInt(Math.abs(getHeight() - 30));
                } while (newY == button.getY());
                button.setBounds(newX, newY, button.getWidth(), button.getHeight());
            }
        });
    }

    public static void main(String[] args) {
        PushMe pushMe = new PushMe();
        pushMe.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        pushMe.setVisible(true);
    }
}
