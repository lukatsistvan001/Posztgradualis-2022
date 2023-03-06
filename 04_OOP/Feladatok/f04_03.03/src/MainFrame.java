import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MainFrame extends JFrame implements ActionListener {

    private JButton button;
    private JLabel label;
    private JLabel goodLabel;
    private JLabel badLabel;
    //private ControlThread ct;
    private JTextField answerField;
    private JPanel contentPanel;
    private int goodAnswers;
    private int badAnswers;

    private ControlThread control;

    public MainFrame(File file) {
        button = new JButton("OK");
        label = new JLabel("Vagy bármi");
        goodLabel = new JLabel("0");
        goodLabel.setForeground(Color.GREEN);
        badLabel = new JLabel("0");
        badLabel.setForeground(Color.RED);
        answerField = new JTextField();
        contentPanel = new JPanel();

        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(label, BorderLayout.NORTH);
        contentPanel.add(button, BorderLayout.SOUTH);
        contentPanel.add(goodLabel, BorderLayout.WEST);
        contentPanel.add(answerField, BorderLayout.CENTER);
        contentPanel.add(badLabel, BorderLayout.EAST);

        this.setContentPane(contentPanel);

        button.addActionListener(this);
        answerField.addActionListener(this);

        control = new ControlThread(file, this);
        control.start();
    }

    public int getGoodAnswers() {
        return goodAnswers;
    }

    public void setLabelText(String text){
        label.setText(text);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (label.getText().equals(answerField.getText())) {
            goodLabel.setText(++goodAnswers + "");
        } else {
            badLabel.setText(++badAnswers + "");
        }

        if (goodAnswers >= 5){
            control.interrupt();
            answerField.setEnabled(false);
            label.setText("Game Over");
        }
        answerField.setText("");
        answerField.requestFocus();
    }
}
