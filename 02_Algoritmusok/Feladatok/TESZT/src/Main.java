/*
Írjon ki a képernyőre n darab csillagot. Oldja meg kétféleképpen is. Iteratív módon és rekurzíó
felhasználásával.
*/

public class Main {
    public static void main(String[] args) {
        kiiras(4);
    }

    public static void kiiras(int hanyszor) {
        if (hanyszor >= 1) {
            System.out.println('*');
            kiiras(hanyszor - 1);
        } else {
            return;
        }
    }
}