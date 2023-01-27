/*
Írjon ki a képernyőre n darab csillagot. Oldja meg kétféleképpen is. Iteratív módon és rekurzíó
felhasználásával.
*/

public class Main {
    public static void main(String[] args) {
        int[] v = {1, 5, 3, 6, 4};
        sort(v);
    }

    private static void sort(int[] v) {
        for (int i = 0; i < v.length - 1; ++i) {
            int max = i;
            for (int j = i + 1; j < v.length; j++) {
                if (v[j] > v[max])
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