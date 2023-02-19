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
        textArea.setEditable(false);
        textField = new TextField();
        button = new Button("Send");
        add(textField, BorderLayout.NORTH);
        add(textArea, BorderLayout.CENTER);
        add(button, BorderLayout.SOUTH);
        button.addActionListener(this);
        textField.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        textArea.append(textField.getText() + "\n");
        textField.setText("");
        textField.requestFocus();
    }

    public static void main(String[] args) {
        ChatWindow chatWindow = new ChatWindow();
        chatWindow.setBounds(10, 10, 400, 400);
        chatWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        chatWindow.setVisible(true);
    }
}