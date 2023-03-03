/*
Egy kereten belül helyezzünk el több különböző színű panelt, a színeket véletlenszerűen generálva.
Ha az egérmutató belépik egy adott panel fölé, az illető panel véletlenszerűen színt vált.
*/

package Feladat4;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

public class RandomColoredPanels extends Frame {

    private Panel[] panels;
    private Random random;

    public RandomColoredPanels(int n) {
        panels = new Panel[n];
        random = new Random();
        setLayout(new GridLayout(n, 1));
        for (int i = 0; i < n; i++) {
            panels[i] = new Panel();
            panels[i].setBackground(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            panels[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    ((Panel) e.getSource()).setBackground(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
                }
            });
            add(panels[i]);
        }
    }

    public static void main(String[] args) {
        RandomColoredPanels panel = new RandomColoredPanels(50);
        panel.setBounds(10, 10, 1024, 768);
        panel.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        panel.setVisible(true);
    }
}
