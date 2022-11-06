import java.util.Scanner;

/*
Írj egy programot, ami beolvas 3 keresztnevet (feltételezhető, hogy amit beír a felhasználó, az
keresztnév), majd ezeket ábécé sorrendben kiírja. Ne használj beépített rendező függvényt.
*/

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] nevek = new String[3];
        System.out.print("Add be az első keresztnevet: ");
        nevek[0] = sc.nextLine();
        System.out.print("Add be a második keresztnevet: ");
        nevek[1] = sc.nextLine();
        System.out.print("Add be a harmadik keresztnevet: ");
        nevek[2] = sc.nextLine();

        System.out.println("A keresztnevek ábécé sorrendben: ");
        if (nevek[0].compareTo(nevek[1]) < 0 && nevek[0].compareTo(nevek[2]) < 0) {
            System.out.println(nevek[0]);
            if (nevek[1].compareTo(nevek[2]) < 0) {
                System.out.println(nevek[1]);
                System.out.println(nevek[2]);
            } else {
                System.out.println(nevek[2]);
                System.out.println(nevek[1]);
            }
        } else if (nevek[1].compareTo(nevek[0]) < 0 && nevek[1].compareTo(nevek[2]) < 0) {
            System.out.println(nevek[1]);
            if (nevek[0].compareTo(nevek[2]) < 0) {
                System.out.println(nevek[0]);
                System.out.println(nevek[2]);
            } else {
                System.out.println(nevek[2]);
                System.out.println(nevek[0]);
            }
        } else {
            System.out.println(nevek[2]);
            if (nevek[0].compareTo(nevek[1]) < 0) {
                System.out.println(nevek[0]);
                System.out.println(nevek[1]);
            } else {
                System.out.println(nevek[1]);
                System.out.println(nevek[0]);
            }
        }

    }
}