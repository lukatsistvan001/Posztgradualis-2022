import java.util.Random;

/*
Írj egy programot amely felölt egy 10 elemű egész szám tömböt 2 és 15000 közötti véletlen egész
számokkal, kiírja a tömb elemeit sorrendben vesszővel elválasztva, megcseréli a legkisebb és a
legnagyobb element, majd újra kiírja a tömb elemeit.
Mivel a számok hossza eltérő, ezért a kiírás láthatóan igénytelennek hat. Gondoskodj róla, hogy a
két kiírás egymás alatti sorokban legyen, és a számokat elválasztó vesszők egymás fölé legyenek
igazítva. (Használd fel az előző feladat kódját.)
*/

public class Main {
    public static void main(String[] args) {
        Random r = new Random();
        int[] tomb = new int[10];

        for (int i = 0; i < 10; i++)
            tomb[i] = r.nextInt(2, 15000);

        for (int i = 0; i < 10; i++) {
            if (tomb[i] < 10)
                System.out.print("    ");
            if (tomb[i] >= 10 && tomb[i] < 100)
                System.out.print("   ");
            if (tomb[i] >= 100 && tomb[i] < 1000)
                System.out.print("  ");
            if (tomb[i] >= 1000 && tomb[i] < 10000)
                System.out.print(" ");
            if (i != 9)
                System.out.print(tomb[i] + ", ");
            else
                System.out.println(tomb[i]);
        }

        int legkisebbSzam = tomb[0];
        int legnagyobbSzam = tomb[0];
        int legkisebbPozicioja = 0;
        int legnagyobbPozicioja = 0;

        for (int i = 1; i < 10; i++) {
            if (legkisebbSzam > tomb[i]) {
                legkisebbSzam = tomb[i];
                legkisebbPozicioja = i;
            }
            if (legnagyobbSzam < tomb[i]) {
                legnagyobbSzam = tomb[i];
                legnagyobbPozicioja = i;
            }
        }

        int temp = tomb[legkisebbPozicioja];
        tomb[legkisebbPozicioja] = tomb[legnagyobbPozicioja];
        tomb[legnagyobbPozicioja] = temp;

        for (int i = 0; i < 10; i++) {
            if (tomb[i] < 10)
                System.out.print("    ");
            if (tomb[i] >= 10 && tomb[i] < 100)
                System.out.print("   ");
            if (tomb[i] >= 100 && tomb[i] < 1000)
                System.out.print("  ");
            if (tomb[i] >= 1000 && tomb[i] < 10000)
                System.out.print(" ");
            if (i != 9)
                System.out.print(tomb[i] + ", ");
            else
                System.out.println(tomb[i]);
        }
    }
}