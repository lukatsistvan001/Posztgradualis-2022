import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ControlThread extends Thread {

    private MainFrame mainFrame;
    private File file;
    private List<String> texts;
    private int time;

    public ControlThread(File file, MainFrame mainFrame) {
        this.file = file;
        this.mainFrame = mainFrame;
        texts = new ArrayList<>();
        time = 1500;
        openFile();
    }

    private void openFile() {
        FileReader fr;
        BufferedReader br = null;
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);

            String s;
            while ((s = br.readLine()) != null) {
                texts.add(s);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
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

    @Override
    public void run() {
        for (String s : texts) {
            mainFrame.setLabelText(s);
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public void faster() {
        if (time > 500) {
            time = time - 500;
        }
    }

    public void slower() {
        if (time < 5000) {
            time = time + 500;
        }
    }
}
