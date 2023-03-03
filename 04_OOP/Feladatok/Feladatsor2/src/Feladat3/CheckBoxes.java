/*
Egy kereten belül egy címke szövegét változtassuk jelölőnégyzetek (Checkbox) segítségével: a címkén
mindig az aktuálisan kijelölt jelölőnégyzeteknek megfelelő címkék szövegét jelenítsük meg. Alakítsuk
át a programot, olyan módon, hogy egyszerre csak egy jelölőnégyzet legyen kiválasztható (a
jelölőnégyzet komponensek „radio button” komponensekbe történő alakítása, a CheckboxGroup
osztály segítségével).
*/

package Feladat3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CheckBoxes extends Frame implements ActionListener, ItemListener {

    private JPanel panel;
    private JTextField textField;
    private CheckboxGroup checkboxGroup;
    private Checkbox checkbox1;
    private Checkbox checkbox2;
    private Checkbox checkbox3;

    public CheckBoxes() {
        panel = new JPanel();
        textField = new JTextField();
        checkboxGroup = new CheckboxGroup();
        checkbox1 = new Checkbox("Button 1", checkboxGroup, false);
        checkbox2 = new Checkbox("Button 2", checkboxGroup, false);
        checkbox3 = new Checkbox("Button 3", checkboxGroup, false);

        panel.add(checkbox1);
        panel.add(checkbox2);
        panel.add(checkbox3);
        add(panel, BorderLayout.NORTH);
        add(textField, BorderLayout.CENTER);

        textField.addActionListener(this);
        checkbox1.addItemListener(this);
        checkbox2.addItemListener(this);
        checkbox3.addItemListener(this);
    }

    public static void main(String[] args) {
        CheckBoxes frame = new CheckBoxes();
        frame.setBounds(10, 10, 640, 480);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        textField.setText(checkboxGroup.getSelectedCheckbox().getLabel());
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        textField.setText(checkboxGroup.getSelectedCheckbox().getLabel());
    }
}
