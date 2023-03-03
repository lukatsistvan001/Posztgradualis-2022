import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JShapeFrame extends JFrame {
    private JPanel contentPanel;
    private JPanel controlPanel;
    private JComboBox shapeComboBox;
    private JComboBox colorComboBox;
    private JSlider zoomSlider;
    private JCheckBox filledCheckBox;
    private JButton drawButton;
    private JShapeCanvas shapeCanvas;
    private JScrollPane scrollPane;
    private JColorChooser colorChooser;

    public JShapeFrame() {
        contentPanel = new JPanel();
        controlPanel = new JPanel();
        shapeComboBox = new JComboBox();
        shapeComboBox.addItem("circle");
        shapeComboBox.addItem("square");
        colorChooser = new JColorChooser();
        colorComboBox = new JComboBox();
        zoomSlider = new JSlider();
        filledCheckBox = new JCheckBox("Filled");
        drawButton = new JButton("Draw");
        shapeCanvas = new JShapeCanvas();
        scrollPane = new JScrollPane(shapeCanvas);
        colorChooser = new JColorChooser();

        controlPanel.add(shapeComboBox);
        controlPanel.add(zoomSlider);
        controlPanel.add(filledCheckBox);
        controlPanel.add(drawButton);

        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(controlPanel, BorderLayout.NORTH);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        contentPanel.add(colorChooser, BorderLayout.SOUTH);

        this.setContentPane(contentPanel);

        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shapeCanvas.refresh((String) shapeComboBox.getSelectedItem(), colorChooser.getColor(), filledCheckBox.isSelected(), zoomSlider.getValue());
                scrollPane.revalidate();
            }
        });
    }
    public static void main(String[] args){
        JShapeFrame jShapeFrame = new JShapeFrame();
        jShapeFrame.setBounds(10,10,1024,768);
        jShapeFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jShapeFrame.setVisible(true);
    }
}
