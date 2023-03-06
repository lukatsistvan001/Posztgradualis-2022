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
        time = 4000;
        loadData();
    }

    public void loadData() {
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);

            String s;
            while ((s = br.readLine()) != null) {
                texts.add(s);
            }
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
        while (mainFrame.getGoodAnswers() < 5){
            for (String s: texts){
                mainFrame.setLabelText(s);
                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }
}
