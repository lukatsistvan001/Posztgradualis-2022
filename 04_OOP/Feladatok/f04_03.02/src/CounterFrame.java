import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CounterFrame extends JFrame implements CounterListener {
    private JButton startButton;
    private JButton stopButton;
    private JButton suspendButton;
    private JButton resumeButton;
    private JLabel counterLabel;
    private JPanel contentPane;
    private Counter counter;

    public CounterFrame() {
        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        suspendButton = new JButton("Suspend");
        resumeButton = new JButton("Resume");
        counterLabel = new JLabel("0");
        contentPane = new JPanel();
        contentPane.add(startButton);
        contentPane.add(stopButton);
        contentPane.add(suspendButton);
        contentPane.add(resumeButton);
        contentPane.add(counterLabel);
        setContentPane(contentPane);
        this.enableButtons(true, false, false, false);

        counter = new Counter();
        counter.addCounterListener(this);
        counter.addCounterListener(new CounterListener() {
            @Override
            public void counterValueChange(CounterEvent counterEvent) {
                System.out.println(counterEvent.getValue());
            }
        });

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                counterLabel.setText("0");
                counter.start();
                enableButtons(false, true, true, false);
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                counter.stop();
                enableButtons(true, false, false, false);
            }
        });

        suspendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                counter.suspend();
                enableButtons(true, true, false, true);
            }
        });

        resumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                counter.resume();
                enableButtons(false, true, true, false);

            }
        });

        setVisible(true);
    }

    public void updateCounterLabel(int value) {
        counterLabel.setText("" + value);
    }

    private void enableButtons(boolean start, boolean stop, boolean suspend, boolean resume) {
        startButton.setEnabled(start);
        stopButton.setEnabled(stop);
        suspendButton.setEnabled(suspend);
        resumeButton.setEnabled(resume);
    }

    @Override
    public void counterValueChange(CounterEvent counterEvent) {
        updateCounterLabel(counterEvent.getValue());
    }
}
