import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ShapeFrame extends Frame {

    private Button drawButton;
    private Choice shapeChoice;
    private Choice colorChoice;
    private Checkbox filledCheckbox;
    private ShapeCanvas shapeCanvas;
    private Panel panel;

    public ShapeFrame() {
        shapeChoice = new Choice();
        shapeChoice.addItem("circle");
        shapeChoice.addItem("square");
        colorChoice = new Choice();
        colorChoice.addItem("red");
        colorChoice.addItem("blue");
        filledCheckbox = new Checkbox("Filled");
        drawButton = new Button("Draw");
        shapeCanvas = new ShapeCanvas();
        panel = new Panel();
        panel.add(shapeChoice);
        panel.add(colorChoice);
        panel.add(filledCheckbox);
        panel.add(drawButton);
        this.add(panel, BorderLayout.NORTH);
        this.add(shapeCanvas, BorderLayout.CENTER);
        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shapeCanvas.refresh(shapeChoice.getSelectedItem(), colorChoice.getSelectedItem(), filledCheckbox.getState());
            }
        });
    }

    public static void main(String[] args) {
        ShapeFrame shapeFrame = new ShapeFrame();
        shapeFrame.setBounds(10, 10, 800, 600);
        shapeFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        shapeFrame.setVisible(true);
    }
}
