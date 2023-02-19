import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyCanvas extends Canvas {

    private int x = 0;
    private int y = 0;

    public MyCanvas() {
        setBackground(Color.CYAN);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                x = e.getX();
                y = e.getY();
            }
        });
    }

    public void paint(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x - 10, y - 10, 20, 20);
    }

    public void update(Graphics g){
        paint(g);
    }

    public static void main(String[] args) {
        Frame frame = new Frame();
        frame.setBounds(10, 10, 800, 600);
        frame.add(new MyCanvas(), BorderLayout.CENTER);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        frame.setVisible(true);
    }
}