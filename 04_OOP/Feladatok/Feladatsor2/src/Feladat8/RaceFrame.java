package Feladat8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RaceFrame extends JFrame {

    private JButton startButton;
    private JTextField numberOfCarsField;
    private RaceTrack raceTrack;
    private RaceController raceController;
    private JPanel controlPanel;
    private JPanel contentPanel;

    public RaceFrame(RaceTrack raceTrack, RaceController raceController) {
        this.raceTrack = raceTrack;
        this.raceController = raceController;
        this.startButton = new JButton("Start");
        this.numberOfCarsField = new JTextField();
        this.controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(1, 2));
        controlPanel.add(startButton);
        controlPanel.add(numberOfCarsField);
        this.contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(controlPanel, BorderLayout.NORTH);
        contentPanel.add(raceTrack, BorderLayout.CENTER);

        this.setContentPane(contentPanel);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                raceController.finished = false;
                raceController.initRace(Integer.parseInt(numberOfCarsField.getText()));
                contentPanel.revalidate();
                raceController.startRace();
            }
        });
    }
}