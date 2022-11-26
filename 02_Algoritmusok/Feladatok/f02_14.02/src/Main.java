/*
Írjon ki a képernyőre n darab csillagot. Oldja meg kétféleképpen is. Iteratív módon és rekurzíó
felhasználásával.
*/

public class Main {
    public static void main(String[] args) {
        System.out.println("\r\nMegoldás rekurzívan: ");
        csillagKiirasaRek(5);
        System.out.println("\r\nMegoldás iteratívan: ");
        csillagKiirasaIt(5);
    }

    private static void csillagKiirasaRek(int n) {
        if (n == 1) {
            System.out.print("*");
            return;
        }
        System.out.print("*");
        csillagKiirasaRek(n - 1);
    }

    private static void csillagKiirasaIt(int n) {
        for (int i = 0; i < n; i++) {
            System.out.print("*");
        }
    }
}