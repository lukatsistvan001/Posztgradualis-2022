import java.util.Random;

public class Main {
    private static char[] tomb = new char[10];

    public static void main(String[] args) {
        feltoltes(tomb);
        kiiras(tomb);
        csere(tomb);
        System.out.println();
        kiiras(tomb);
    }

    private static void feltoltes(char[] tomb) {
        Random r = new Random();
        int szam;
        for (int i = 0; i < tomb.length; i++) {
            do {
                szam = r.nextInt(48, 122);
            } while ((szam < 48 || szam > 57) &&
                    (szam < 65 || szam > 90) &&
                    (szam < 97 || szam > 122));
            tomb[i] = (char) szam;
        }
    }

    private static void csere(char[] tomb) {
        char temp;
        for (int i = 0; i < tomb.length / 2; i++) {
            temp = tomb[i];
            tomb[i] = tomb[tomb.length - i - 1];
            tomb[tomb.length - i - 1] = temp;
        }
    }

    private static void kiiras(char[] tomb) {
        for (int i = 0; i < tomb.length; i++) {
            System.out.print(tomb[i]);
        }
    }
}