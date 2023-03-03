import java.awt.Frame;
import java.awt.Button;
import java.awt.Label;
import java.awt.BorderLayout;
import java.awt.event.*;

public class MyFrame extends Frame implements ActionListener {
    private Button button1;
    private Button button2;
    private Label label;

    public MyFrame() {
        // gombok létrehozása
        button1 = new Button("Button 1");
        add(button1, BorderLayout.NORTH);
        button2 = new Button("Button 2");
        add(button2, BorderLayout.SOUTH);
        // a címke létrehozása
        label = new Label("Label");
        add(label, BorderLayout.CENTER);
        button1.addActionListener(this);
        button2.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        label.setText(e.getActionCommand());
    }


}