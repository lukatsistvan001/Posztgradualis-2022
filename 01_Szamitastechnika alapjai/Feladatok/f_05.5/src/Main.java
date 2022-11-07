import java.util.Random;

/*
Írj egy programot amely feltölt egy 3 elemű tömböt véletlen számokkal, kiírja a tömb elemeit,
megcseréli az első és az utolsó számot, majd újra kiírja a tömböt.
*/

public class Main {
    public static void main(String[] args) {
        int tomb[] = new int[3];
        Random r = new Random();
        tomb[0] = r.nextInt();
        tomb[1] = r.nextInt();
        tomb[2] = r.nextInt();

        System.out.println("Első elem: " + tomb[0]);
        System.out.println("Második elem: " + tomb[1]);
        System.out.println("Harmadik elem: " + tomb[2]);

        int temp;
        temp = tomb[0];
        tomb[0] = tomb[2];
        tomb[2] = temp;

        System.out.println("\n\rA felcserélés után a tömb tartalma:");
        System.out.println("Első elem: " + tomb[0]);
        System.out.println("Második elem: " + tomb[1]);
        System.out.println("Harmadik elem: " + tomb[2]);

    }
}