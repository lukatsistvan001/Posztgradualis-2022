// HF > úgy oldjuk meg az újraméretezés problémát, hogy nem blokkoljuk le a resizable-t false-ra

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyCanvas2 extends Canvas {

    private Image image;
    private Graphics graphics;

    public MyCanvas2() {
        setBackground(Color.CYAN);
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                graphics.fillOval(e.getX() - 10, e.getY() - 10, 20, 20);
                repaint();
            }
        });
    }

    public void paint(Graphics g) {
        if (image == null) {
            image = createImage(getWidth(), getHeight());
            graphics = image.getGraphics();
            graphics.setColor(Color.RED);
        }
        g.drawImage(image, 0, 0, null);
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    public static void main(String[] args) {
        Frame frame = new Frame("Paint");
        frame.setBounds(10, 10, 800, 600);
        frame.add(new MyCanvas2(), BorderLayout.CENTER);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        frame.setResizable(false);
        frame.setVisible(true);
    }
}