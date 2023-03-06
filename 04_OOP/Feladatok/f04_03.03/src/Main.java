import javax.swing.*;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        File file;
        if (args.length != 1) {
            file = new File("data.txt");
        } else {
            file = new File(args[0]);
        }

        if (!file.exists() || !file.canRead()) {
            System.out.println("IO ERROR");
            System.exit(1);
        }
        MainFrame mf = new MainFrame(file);
        mf.setBounds(10, 10, 800, 600);
        mf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mf.setVisible(true);
    }
}