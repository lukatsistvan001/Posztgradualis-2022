import java.awt.*;
import java.awt.event.*;

public class MouseExample extends Frame {
    private Panel panel;
    private Label label;

    public MouseExample() {
        panel = new Panel();
        label = new Label();
        add(panel, BorderLayout.CENTER);
        add(label, BorderLayout.SOUTH);
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                label.setText("Mouse clicked coordiantets: " + e.getX() + ", " + e.getY());
            }
        });
        panel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                label.setText("Mouse moved coordiantets: " + e.getX() + ", " + e.getY());
            }
        });
    }

    public static void main(String[] args) {
        MouseExample mouseExample = new MouseExample();
        mouseExample.setBounds(10, 10, 400, 400);
        mouseExample.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        mouseExample.setVisible(true);
    }
}
