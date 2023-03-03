/*
Hozzunk létre egy keretet, és ezen belül helyezzünk el egy többsoros szöveg megjelenítésére alkalmas
komponenst (TextArea), egy egysoros szöveg bevitelére alkalmas szövegmezőt (TextField), valamint
egy gombot. Ha a felhasználó a gombra kattint, vagy a szövegmezőn belül lenyomja az enter billentyűt,
a szövegmező tartalmát hozzáadjuk a TextArea tartalmához, majd töröljük a szövegmezőből
(lehetőséget adva egy új szöveg beírására).
*/

package Feladat2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ChatWindow extends Frame implements ActionListener {

    private TextArea textArea;
    private TextField textField;
    private Button button;

    public ChatWindow() {
        textArea = new TextArea();
        textField = new TextField();
        button = new Button("Send");
        add(textField, BorderLayout.NORTH);
        add(textArea, BorderLayout.CENTER);
        add(button, BorderLayout.SOUTH);

        textField.addActionListener(this);
        button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        textArea.append(textField.getText() + "\n");
        textField.setText("");
        textField.requestFocus();
    }

    public static void main(String[] args) {
        ChatWindow chatWindow = new ChatWindow();
        chatWindow.setBounds(10, 10, 1024, 768);
        chatWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        chatWindow.setVisible(true);
    }
}