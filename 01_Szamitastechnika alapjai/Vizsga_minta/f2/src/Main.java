/*
Írjon egy programot ami egy 100 elemű tömböt feltölt véletlen számokkal, majd az
utolsó 50 pozíción tárolt elemet egy pozícióval hátrébb tolja, és az utolsó elemet az első
eltolt elem eredeti helyére teszi.
*/

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int[] tomb = new int[100];
        Random r = new Random();
        for (int i = 0; i < tomb.length; i++) {
            tomb[i] = r.nextInt();
        }

        System.out.println("A tömb elemei:");
        tombKiirasa(tomb);

        int temp = tomb[tomb.length - 1];
        for (int i = tomb.length - 1; i > tomb.length / 2; i--) {
            tomb[i] = tomb[i - 1];
        }
        tomb[tomb.length / 2] = temp;

        System.out.println("\r\nA tologatás után a tömb tartalma:");
        tombKiirasa(tomb);
    }

    private static void tombKiirasa(int[] tomb) {
        for (int i = 0; i < tomb.length; i++) {
            System.out.print(tomb[i] + " ");
        }
    }
}