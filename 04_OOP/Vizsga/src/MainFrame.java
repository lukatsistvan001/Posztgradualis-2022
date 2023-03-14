import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MainFrame extends JFrame implements ActionListener {

    private JPanel contentPanel;
    private JPanel controlPanel;
    private JButton slowerButton;
    private JButton fasterButton;
    private JLabel textLabel;
    private ControlThread controlThread;

    public MainFrame(File file) {
        contentPanel = new JPanel();
        controlPanel = new JPanel();
        slowerButton = new JButton("Slower");
        fasterButton = new JButton("Faster");
        textLabel = new JLabel();

        controlPanel.add(slowerButton);
        controlPanel.add(fasterButton);

        this.setContentPane(contentPanel);
        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(textLabel, BorderLayout.NORTH);
        contentPanel.add(controlPanel, BorderLayout.SOUTH);

        slowerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlThread.slower();
            }
        });

        fasterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlThread.faster();
            }
        });

        controlThread = new ControlThread(file, this);
        controlThread.start();
    }

    public void setLabelText(String text) {
        textLabel.setText(text);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        textLabel.setText("");
    }
}
