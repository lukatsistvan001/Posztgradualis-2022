import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

public class CatchMe extends Frame {
    private Button button;
    private Random random;

    public CatchMe() {
        button = new Button("Catch me!");
        random = new Random();
        setLayout(null);
        setBounds(10, 10, 1024, 768);
        button.setBounds(random.nextInt(getWidth()), random.nextInt(getHeight()), 70, 30);
        add(button);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBounds(random.nextInt(getWidth() - Math.abs(70)), random.nextInt(getHeight() - Math.abs(30)), 70, 30);
            }
        });
    }

    public static void main(String[] args) {
        CatchMe catchMe = new CatchMe();
        catchMe.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        catchMe.setVisible(true);
    }
}
