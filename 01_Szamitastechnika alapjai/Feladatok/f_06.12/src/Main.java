import java.util.Random;

/*
Írj egy programot amely feltölt egy 10 elemű tömböt véletlen tört számokkal, kiírja a tömb
elemeit, megcseréli a legnagyobb és a legkisebb számot, majd újra kiírja a tömböt.
*/

public class Main {
    public static void main(String[] args) {

        double[] tomb = new double[10];
        Random r = new Random();

        for (int i = 0; i < 10; i++)
            tomb[i] = r.nextDouble();

        System.out.println("A tömb elemei: ");
        for (int i = 0; i < 10; i++)
            System.out.println(tomb[i]);

        double legnagyobb;
        double legkisebb;
        int legkisebbIndex = 0;
        int legnagyobbIndex = 0;

        legkisebb = tomb[0];
        legnagyobb = tomb[0];
        for (int i = 1; i < 10; i++) {
            if (legkisebb > tomb[i]) {
                legkisebbIndex = i;
                legkisebb = tomb[i];
            }
            if (legnagyobb<tomb[i]) {
                legnagyobbIndex= i;
                legnagyobb = tomb[i];
            }
        }

        double temp;
        temp = tomb[legkisebbIndex];
        tomb[legkisebbIndex] = tomb[legnagyobbIndex];
        tomb[legnagyobbIndex] = temp;
        System.out.println("A tömb elemei a csere után: ");
        for (int i = 0; i < 10; i++)
            System.out.println(tomb[i]);
    }
}