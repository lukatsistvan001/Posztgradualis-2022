package Feladat6WithTextFields;

import java.awt.*;
import java.awt.event.*;

public class DrawShapeFrame extends Frame {

    private Choice shapeChoice;
    private TextField redTextField;
    private TextField greenTextField;
    private TextField blueTextField;
    private Checkbox filledCheckbox;
    private Button drawButton;
    private DrawShapeCanvas shapeCanvas;
    private Panel panel;

    public DrawShapeFrame() {
        shapeChoice = new Choice();
        shapeChoice.addItem("circle");
        shapeChoice.addItem("square");
        redTextField = new TextField("0");
        greenTextField = new TextField("0");
        blueTextField = new TextField("0");
        filledCheckbox = new Checkbox("Fill");
        drawButton = new Button("Draw");
        shapeCanvas = new DrawShapeCanvas();
        panel = new Panel();
        panel.add(shapeChoice);
        panel.add(redTextField);
        panel.add(greenTextField);
        panel.add(blueTextField);
        panel.add(filledCheckbox);
        panel.add(drawButton);
        this.add(panel, BorderLayout.NORTH);
        this.add(shapeCanvas, BorderLayout.CENTER);

        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shapeCanvas.refresh(shapeChoice.getSelectedItem(),
                        Integer.parseInt(redTextField.getText()),
                        Integer.parseInt(greenTextField.getText()),
                        Integer.parseInt(blueTextField.getText()),
                        filledCheckbox.getState());
            }
        });

        shapeChoice.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                shapeCanvas.refresh(shapeChoice.getSelectedItem(),
                        Integer.parseInt(redTextField.getText()),
                        Integer.parseInt(greenTextField.getText()),
                        Integer.parseInt(blueTextField.getText()),
                        filledCheckbox.getState());
            }
        });

        filledCheckbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                shapeCanvas.refresh(shapeChoice.getSelectedItem(),
                        Integer.parseInt(redTextField.getText()),
                        Integer.parseInt(greenTextField.getText()),
                        Integer.parseInt(blueTextField.getText()),
                        filledCheckbox.getState());
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