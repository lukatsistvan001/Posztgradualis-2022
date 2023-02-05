import java.io.*;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        File bemenet = Paths.get(args[0]).toFile();
        FileInputStream fis = new FileInputStream(bemenet);
        ObjectInputStream ois = new ObjectInputStream(fis);
        int tombMeret = ois.readInt();
        double[][] tomb = new double[tombMeret][];
        for (int i = 0; i < tombMeret; i++) {
            for (int j = 0; j < tomb.length; j++) {
                tomb[i][j] = ois.readDouble();
            }
        }
        String karakterlanc = ois.readUTF();

        File kimenet = Paths.get("", karakterlanc).toFile();
        FileWriter fw = new FileWriter(kimenet);
        fw.write(tombMeret);
        for (int i = 0; i < tombMeret; i++) {
            for (int j = 0; j < tomb[i].length; j++) {
                fw.write((char) tomb[i][j]);
            }
        }

        ois.close();
        fis.close();
    }
}