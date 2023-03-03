/*
Az előbbi AWT grafikával kapcsolatos feladat (alakzatok kirajzolása) megoldása során elkészített
program grafikus felületét írjuk át SWING komponensek felhasználásával. A programot egészítsük ki
néhány új funkcionalitással. Egy JSlider segítségével legyen változtatható az alakzat mérete, olyan
módon, hogy az meg is haladhassa a vászon (esetünkben JPanel komponens) aktuális méretét.
Amennyiben az alakzat „kilóg” a vászonból, jelenjenek meg görgetősávok, amelyek segítségével
változtathatjuk az éppen látható felületet (JScrollPane komponenst alkalmazhatunk). A szín
kiválasztására ezúttal egy külön grafikus felületet is biztosítsunk, a JColorChooser komponens
felhasználásával.
*/

package Feladat7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DrawShapeJFrame extends JFrame {

    private JPanel controlPanel;
    private JPanel contentPanel;
    private JComboBox shapeComboBox;
    private JSlider zoomSlider;
    private JCheckBox fillCheckBox;
    private JButton drawButton;
    private DrawShapeJCanvas canvas;
    private JColorChooser colorChooser;
    private JScrollPane scrollPane;

    public DrawShapeJFrame() {
        controlPanel = new JPanel();
        contentPanel = new JPanel();
        shapeComboBox = new JComboBox();
        shapeComboBox.addItem("circle");
        shapeComboBox.addItem("square");
        zoomSlider = new JSlider();
        fillCheckBox = new JCheckBox("Fill");
        drawButton = new JButton("Draw");
        canvas = new DrawShapeJCanvas();
        colorChooser = new JColorChooser();
        scrollPane = new JScrollPane(canvas);

        controlPanel.add(shapeComboBox);
        controlPanel.add(zoomSlider);
        controlPanel.add(fillCheckBox);
        controlPanel.add(drawButton);

        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(controlPanel, BorderLayout.NORTH);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        contentPanel.add(colorChooser, BorderLayout.SOUTH);

        this.setContentPane(contentPanel);

        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.refresh((String) shapeComboBox.getSelectedItem(), zoomSlider.getValue(), fillCheckBox.isSelected(), colorChooser.getColor());
                scrollPane.revalidate();
            }
        });
    }

    public static void main(String[] args) {
        DrawShapeJFrame frame = new DrawShapeJFrame();
        frame.setBounds(10, 10, 1024, 768);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}