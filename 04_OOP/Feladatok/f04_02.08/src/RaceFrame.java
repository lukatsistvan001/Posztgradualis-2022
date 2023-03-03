import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RaceFrame extends JFrame {
    private JButton startButton;
    private RaceTrack raceTrack;
    private RaceController raceController;
    private JPanel contentPanel;
    private JTextField numberOfCarsField;
    private JPanel controlPanel;

    public RaceFrame(RaceTrack raceTrack, RaceController raceController) {
        this.raceTrack = raceTrack;
        this.raceController = raceController;
        this.startButton = new JButton("Start");
        controlPanel = new JPanel();
        numberOfCarsField = new JTextField();
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        controlPanel.setLayout(new GridLayout(1, 2));
        controlPanel.add(numberOfCarsField);
        controlPanel.add(startButton);
        contentPanel.add(controlPanel, BorderLayout.NORTH);
        contentPanel.add(raceTrack, BorderLayout.CENTER);
        this.setContentPane(contentPanel);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RaceController.finished = false;
                raceController.initRace(Integer.parseInt(numberOfCarsField.getText()));
                contentPanel.revalidate();
                raceController.startRace();
            }
        });
    }
}
