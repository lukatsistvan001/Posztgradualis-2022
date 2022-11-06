import java.util.Scanner;

/*
Írj egy programot, ami beolvas 3 keresztnevet (feltételezhető, hogy amit beír a felhasználó, az
keresztnév), majd ezeket fordított sorrendben kiírja.
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

        System.out.println("A keresztnevek fordított sorrendben: " + nevek[2] + " " + nevek[1] + " " + nevek[0] + " ");
    }
}