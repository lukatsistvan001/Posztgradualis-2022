/*
Hozzunk létre egy tömbökből álló tömböt, amelynek első sora 1, második
sora 2, n-edik sora n elemet tartalmaz. Az elemek egész számok 1-től
n*(n+1)/2-ig, ahol n, a sorok száma, a parancssor argumentuma. Amennyiben
nem adunk meg argumentumot (vagy az nem egy numerikus érték), a sorok
alapértelmezett száma legyen 10. Figyeljünk arra, hogy minden tömb
esetében csak annyi elemnek foglaljunk helyet, amennyire valóban szükség
van. A tömb elemeit írassuk ki a konzolra az alábbi példához hasonlóan:
1
2 3
4 5 6
...
*/

public class ArrayExample {
    public static void main(String[] args) {
        int rowNumber = 10;
        try {
            rowNumber = Integer.parseInt(args[0]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
        }
        int[][] array = new int[rowNumber][];
        int number = 0;
        for (int i = 0; i < array.length; i++) {
            array[i] = new int[i + 1];
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = ++number;
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }
}
