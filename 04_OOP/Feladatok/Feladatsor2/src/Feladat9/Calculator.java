package Feladat9;

import javax.swing.*;
import java.awt.*;

public class Calculator extends JFrame {

    private JPanel displayPanel;
    private JTextField displayField;
    private JPanel controlPanel;
    private JButton clearButton;
    private JButton plusMinusButton;
    private JButton persentageButton;
    private JButton zeroButton;
    private JButton oneButton;
    private JButton twoButton;
    private JButton threeButton;
    private JButton fourButton;
    private JButton fiveButton;
    private JButton sixButton;
    private JButton sevenButton;
    private JButton eightButton;
    private JButton nineButton;
    private JButton addButton;
    private JButton minusButton;
    private JButton multiplyButton;
    private JButton divideButton;
    private JButton equalButton;
    private JButton dotButton;

    public Calculator() {
        displayPanel = new JPanel();
        displayField = new JTextField();
        controlPanel = new JPanel();
        clearButton = new JButton("C");
        plusMinusButton = new JButton("+/-");
        persentageButton = new JButton("%");
        zeroButton = new JButton("0");
        oneButton = new JButton("1");
        twoButton = new JButton("2");
        threeButton = new JButton("3");
        fourButton = new JButton("4");
        fiveButton = new JButton("5");
        sixButton = new JButton("6");
        sevenButton = new JButton("7");
        eightButton = new JButton("8");
        nineButton = new JButton("9");
        addButton = new JButton("+");
        minusButton = new JButton("-");
        multiplyButton = new JButton("*");
        divideButton = new JButton("/");
        equalButton = new JButton("=");
        dotButton = new JButton(".");

        displayPanel.add(displayField);

        controlPanel.setLayout(new GridLayout(5, 4));
        controlPanel.add(clearButton);
        controlPanel.add(plusMinusButton);
        controlPanel.add(persentageButton);
        controlPanel.add(zeroButton);
        controlPanel.add(oneButton);
        controlPanel.add(twoButton);
        controlPanel.add(threeButton);
        controlPanel.add(fourButton);
        controlPanel.add(fiveButton);
        controlPanel.add(sixButton);
        controlPanel.add(sevenButton);
        controlPanel.add(eightButton);
        controlPanel.add(nineButton);
        controlPanel.add(addButton);
        controlPanel.add(minusButton);
        controlPanel.add(multiplyButton);
        controlPanel.add(divideButton);
        controlPanel.add(equalButton);
        controlPanel.add(dotButton);

        setLayout(new GridLayout(2,1));
        add(displayField);
        add(controlPanel);
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.setBounds(10, 10, 300, 400);
        calculator.setDefaultCloseOperation(EXIT_ON_CLOSE);
        calculator.setVisible(true);
    }
}