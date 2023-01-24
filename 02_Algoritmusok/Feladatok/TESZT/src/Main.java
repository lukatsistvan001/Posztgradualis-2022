/*
Írjon ki a képernyőre n darab csillagot. Oldja meg kétféleképpen is. Iteratív módon és rekurzíó
felhasználásával.
*/

public class Main {
    public static void main(String[] args) {
        int[] v = {5, 1, 3, 2, 4};
        sort(v);
    }

    private static void sort(int[] v) {
        for (int i = 0; i < v.length - 1; i++) {
            int max = i;
            for (int j = i + 1; j < v.length; j++) {
                if (v[j] > v[i])
                    max = j;
            }
            if (max != i) {
                int temp = v[max];
                v[max] = v[i];
                v[i] = temp;
            }
        }
    }
}