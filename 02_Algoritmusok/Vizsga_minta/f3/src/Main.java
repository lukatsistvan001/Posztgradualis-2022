/*
Írjon egy programot, ami egy rekurzív algoritmussal kiszámolja egy tömb elemeinek a
háromszög összegét.
Egy tömb elemeinek a háromszögösszegét úgy kapjuk meg, hogy a tömb egymás mellett lévő
elemeit addig adogatjuk össze, amíg már csak egy elem marad.
Pl.: A {1, 2, 3, 4, 5} tömb háromszögösszege 48, mert:
[1, 2, 3, 4, 5] -> [3, 5, 7, 9]-> [8, 12, 16]-> [20, 28]->48
*/

public class Main {
    public static void main(String[] args) {
        int[] tomb = {1, 2, 3, 4, 5};
        System.out.println(haromszogOsszeg(tomb));
    }

    private static int haromszogOsszeg(int[] tomb) {
        if (tomb.length == 1)
            return tomb[0];
        else {
            int[] ujTomb = new int[tomb.length - 1];
            for (int i = 0; i < ujTomb.length; i++) {
                ujTomb[i] = tomb[i] + tomb[i + 1];
            }
            return haromszogOsszeg(ujTomb);
        }
    }
}