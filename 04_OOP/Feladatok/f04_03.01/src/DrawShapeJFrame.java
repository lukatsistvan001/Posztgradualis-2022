
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class DrawShapeJFrame extends JFrame {

    private JPanel controlPanel;
    private JPanel contentPanel;
    private JComboBox shapeComboBox;
    private JSlider zoomSlider;
    private JCheckBox fillCheckBox;
    private JButton drawButton;
    private DrawShapeJCanvas canvas;
    private JColorChooser colorChooser;
    private JScrollPane scrollPane;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem loadItem;
    private JMenuItem saveItem;

    public DrawShapeJFrame() {
        controlPanel = new JPanel();
        contentPanel = new JPanel();
        shapeComboBox = new JComboBox();
        shapeComboBox.addItem("circle");
        shapeComboBox.addItem("square");
        zoomSlider = new JSlider();
        fillCheckBox = new JCheckBox("Fill");
        drawButton = new JButton("Draw");
        canvas = new DrawShapeJCanvas();
        colorChooser = new JColorChooser();
        scrollPane = new JScrollPane(canvas);

        loadItem = new JMenuItem("Load...");
        saveItem = new JMenuItem("Save...");
        fileMenu = new JMenu("File");
        fileMenu.add(loadItem);
        fileMenu.add(saveItem);
        menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        this.setJMenuBar(menuBar);

        loadItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.showOpenDialog(DrawShapeJFrame.this);
                loadImage(fileChooser.getSelectedFile());
            }
        });

        saveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.showSaveDialog(DrawShapeJFrame.this);
                saveImage(fileChooser.getSelectedFile());
            }
        });

        controlPanel.add(shapeComboBox);
        controlPanel.add(zoomSlider);
        controlPanel.add(fillCheckBox);
        controlPanel.add(drawButton);

        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(controlPanel, BorderLayout.NORTH);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        contentPanel.add(colorChooser, BorderLayout.SOUTH);

        this.setContentPane(contentPanel);

        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.refresh((String) shapeComboBox.getSelectedItem(), zoomSlider.getValue(), fillCheckBox.isSelected(), colorChooser.getColor());
                scrollPane.revalidate();
            }
        });
    }

    private void loadImage(File file) {
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            shapeComboBox.setSelectedItem(br.readLine());
            colorChooser.setColor(new Color(Integer.parseInt(br.readLine()),
                    (Integer.parseInt(br.readLine())),
                    (Integer.parseInt(br.readLine()))));
            fillCheckBox.setSelected(Boolean.parseBoolean((br.readLine())));
            zoomSlider.setValue(Integer.parseInt(br.readLine()));
            canvas.refresh((String) shapeComboBox.getSelectedItem(), zoomSlider.getValue(), fillCheckBox.isSelected(), colorChooser.getColor());
            scrollPane.revalidate();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,
                    "Load operation failed",
                    "IO Error",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private void saveImage(File file) {
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            bw.write(shapeComboBox.getSelectedItem() + "\n");
            bw.write(colorChooser.getColor().getRed() + "\n");
            bw.write(colorChooser.getColor().getGreen() + "\n");
            bw.write(colorChooser.getColor().getBlue() + "\n");
            bw.write(fillCheckBox.isSelected() + "\n");
            bw.write(zoomSlider.getValue() + "\n");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,
                    "Save operation failed",
                    "IO Error",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void main(String[] args) {
        DrawShapeJFrame frame = new DrawShapeJFrame();
        frame.setBounds(10, 10, 1024, 768);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}