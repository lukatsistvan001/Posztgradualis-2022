/*
Hozzunk létre egy keretet (Frame), és ezen belül helyezzünk el egy panelt, valamint egy címkét (Label).
Ha a felhasználó a panelre kattint az egérrel, a címkén jelenítsük meg az egérkattintás koordinátáit. A
komponensek elhelyezésére használjunk egy megfelelő LayoutManager példányt, ne rögzítsük a
pozíciókat és méreteket (ez a javaslat a legutolsó kivételével a következő feladatokra is érvényes).
Egészítsük ki a programot, olyan módon, hogy ne csak az egérkattintást figyeljük, hanem az
egérmutató mozgását is. A címkére az aktuális esemény típusát is írjuk ki a koordináták mellé:
amennyiben a felhasználó kattintott a „clicked” üzenet, amennyiben csak elmozdította a pointert a
„moved” üzenet, amennyiben lenyomott gombbal mozdította a pointert a „dragged” üzenet jelenjen
meg.
*/

package Feladat1;

import java.awt.*;
import java.awt.event.*;

public class MyFrame extends Frame {

    private Panel panel;
    private Label label;

    public MyFrame() {
        panel = new Panel();
        label = new Label("label");
        add(label, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                label.setText("Mouse clicked coordinates: " + e.getX() + ", " + e.getY());
            }
        });
        panel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                label.setText("Mouse moved coordinates: " + e.getX() + ", " + e.getY());
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                label.setText("Mouse dragged coordinates: " + e.getX() + ", " + e.getY());
            }
        });
    }

    public static void main(String[] args) {
        MyFrame f = new MyFrame();
        f.setBounds(10, 10, 1024, 768);
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        f.setVisible(true);
    }
}