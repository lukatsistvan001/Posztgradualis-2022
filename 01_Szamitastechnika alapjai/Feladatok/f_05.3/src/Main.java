import java.util.Scanner;

/*
Írj programot, ami be kér egy számot 1 és 5 között, majd pontosan ennyi keresztnevet kér be és
ezeket kiírja fordított sorrendben.
*/

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Adj meg egy számot 1 és 5 között: ");
        int iSzam = sc.nextInt();
        sc.nextLine();

        String[] nevek = new String[iSzam];
        switch (iSzam) {
            case 5:
                System.out.println("Add meg a keresztnevet: ");
                nevek[iSzam - 5] = sc.nextLine();
            case 4:
                System.out.println("Add meg a keresztnevet: ");
                nevek[iSzam - 4] = sc.nextLine();
            case 3:
                System.out.println("Add meg a keresztnevet: ");
                nevek[iSzam - 3] = sc.nextLine();
            case 2:
                System.out.println("Add meg a keresztnevet: ");
                nevek[iSzam - 2] = sc.nextLine();
            case 1:
                System.out.println("Add meg a keresztnevet: ");
                nevek[iSzam - 1] = sc.nextLine();
        }

        System.out.println("A keresztnevek fordított sorrendben:");
        switch (iSzam){
            case 5:
                System.out.println(nevek[4]);
            case 4:
                System.out.println(nevek[3]);
            case 3:
                System.out.println(nevek[2]);
            case 2:
                System.out.println(nevek[1]);
            case 1:
                System.out.println(nevek[0]);
        }
    }
}