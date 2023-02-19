import java.awt.Frame;
import java.awt.Button;
import java.awt.Label;
import java.awt.BorderLayout;

public class MyFrame extends Frame {
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
    }

    public static void main(String[] args) {
        MyFrame f = new MyFrame();
        f.setBounds(10, 10, 300, 300);
        f.setVisible(true);
    }
}