package Feladat3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MainFrame extends JFrame implements ActionListener {

    private JPanel contentPanel;
    private JButton okButton;
    private JTextField answerField;
    private JLabel textLabel;
    private JLabel goodLabel;
    private JLabel badLabel;
    private int goodAnswers;
    private int badAnswers;
    private ControlThread controlThread;

    public MainFrame(File file) {
        contentPanel = new JPanel();
        okButton = new JButton("OK");
        answerField = new JTextField();
        textLabel = new JLabel("Lásd");
        goodLabel = new JLabel("0");
        goodLabel.setForeground(Color.GREEN);
        badLabel = new JLabel("0");
        badLabel.setForeground(Color.RED);

        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(okButton, BorderLayout.SOUTH);
        contentPanel.add(answerField, BorderLayout.CENTER);
        contentPanel.add(textLabel, BorderLayout.NORTH);
        contentPanel.add(goodLabel, BorderLayout.WEST);
        contentPanel.add(badLabel, BorderLayout.EAST);

        this.setContentPane(contentPanel);

        okButton.addActionListener(this);
        answerField.addActionListener(this);

        controlThread = new ControlThread(file, this);
        controlThread.start();
    }

    public int getGoodAnswers() {
        return goodAnswers;
    }

    public void setTextLabel(String text) {
        textLabel.setText(text);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (textLabel.getText().equals(answerField.getText())) {
            goodLabel.setText(++goodAnswers + "");
        } else {
            badLabel.setText(++badAnswers + "");
        }

        if (goodAnswers >= 5) {
            controlThread.interrupt();
            answerField.setEnabled(false);
            textLabel.setText("Game Over");
        }

        answerField.setText("");
        answerField.requestFocus();
    }
}