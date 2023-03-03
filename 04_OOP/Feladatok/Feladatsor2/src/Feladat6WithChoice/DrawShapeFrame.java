/*
Egy kereten belül helyezzünk el egy rajzvásznat (egy saját osztályt hozunk létre a Canvas osztályból
származtatva), két Choice komponenst, egy jelölőnégyzetet (Checkbox), és egy gombot. A felhasználó
a két Choice komponens segítségével kiválaszthat egy adott alakzattípust (pl. kör, négyzet stb.) és egy
adott színt (pl. kék, piros stb.). A gomb lenyomásának hatására a vászonra kirajzoljuk a kiválasztott
alakzatot a kiválasztott színnel. Amennyiben a jelölőnégyzet be van jelölve, az alakzat felületét is
kitöltjük az illető színnel.
A programnak elkészíthetjük egy olyan változatát is, amelynek esetében nem szükséges a gomb
lenyomása: bármelyik másik komponens állapotának változásakor frissítjük a rajzot. Ezen kívül a szín
kiválasztására alkalmas Choice komponenst helyettesíthetjük olyan módon, hogy a felhasználó
tetszőleges R, G, B értékeket meg tudjon határozni (pl. három szövegmező segítségével).
*/

package Feladat6WithChoice;

import java.awt.*;
import java.awt.event.*;

public class DrawShapeFrame extends Frame {

    private Choice shapeChoice;
    private Choice colorChoice;
    private Checkbox filledCheckbox;
    private Button drawButton;
    private DrawShapeCanvas shapeCanvas;
    private Panel panel;

    public DrawShapeFrame() {
        shapeChoice = new Choice();
        shapeChoice.addItem("circle");
        shapeChoice.addItem("square");
        colorChoice = new Choice();
        colorChoice.addItem("blue");
        colorChoice.addItem("red");
        filledCheckbox = new Checkbox("Fill");
        drawButton = new Button("Draw");
        shapeCanvas = new DrawShapeCanvas();
        panel = new Panel();
        panel.add(shapeChoice);
        panel.add(colorChoice);
        panel.add(filledCheckbox);
        panel.add(drawButton);
        this.add(panel, BorderLayout.NORTH);
        this.add(shapeCanvas, BorderLayout.CENTER);

        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shapeCanvas.refresh(shapeChoice.getSelectedItem(), colorChoice.getSelectedItem(), filledCheckbox.getState());
            }
        });

        shapeChoice.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                shapeCanvas.refresh(shapeChoice.getSelectedItem(), colorChoice.getSelectedItem(), filledCheckbox.getState());
            }
        });

        colorChoice.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                shapeCanvas.refresh(shapeChoice.getSelectedItem(), colorChoice.getSelectedItem(), filledCheckbox.getState());
            }
        });

        filledCheckbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                shapeCanvas.refresh(shapeChoice.getSelectedItem(), colorChoice.getSelectedItem(), filledCheckbox.getState());
            }
        });
    }

    public static void main(String[] args) {
        DrawShapeFrame shapeFrame = new DrawShapeFrame();
        shapeFrame.setBounds(10, 10, 1024, 768);
        shapeFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        shapeFrame.setVisible(true);
    }
}