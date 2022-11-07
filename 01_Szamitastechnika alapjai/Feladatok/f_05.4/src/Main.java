import java.util.Scanner;

/*
Írj egy programot ami bekeri négy autó adatait (márka, szín, rendszám). Feltételezhetted, hogy a
megadott adatok mindig értelmesek, ezt nem kelle ellenőrizni. Tárold az adatokat tömbök
tömbjeként, úgy, hogy az első dimenzióban az autókat indexeljük, a másodikban pedig az
adataikat. Beolvasás után írd is ki őket rendszám szerinti ABC sorrendben.
*/

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[][] autok = new String[4][3];

        System.out.println("Add meg az első autó márkáját: ");
        autok[0][0] = sc.nextLine();
        System.out.println("Add meg az első autó színét: ");
        autok[0][1] = sc.nextLine();
        System.out.println("Add meg az első autó rendszámát: ");
        autok[0][2] = sc.nextLine();

        System.out.println("Add meg a második autó márkáját: ");
        autok[1][0] = sc.nextLine();
        System.out.println("Add meg a második autó színét: ");
        autok[1][1] = sc.nextLine();
        System.out.println("Add meg a második autó rendszámát: ");
        autok[1][2] = sc.nextLine();

        System.out.println("Add meg a harmadik autó márkáját: ");
        autok[2][0] = sc.nextLine();
        System.out.println("Add meg a harmadik autó színét: ");
        autok[2][1] = sc.nextLine();
        System.out.println("Add meg a harmadik autó rendszámát: ");
        autok[2][2] = sc.nextLine();

        System.out.println("Rendszám szerinti sorrend:");
        if (autok[0][2].compareTo(autok[1][2]) < 1 && (autok[0][2].compareTo(autok[2][2]) < 1)) {
            System.out.println("Márka: " + autok[0][0] + " Szín: " + autok[0][1] + " Rendszám: " + autok[0][2]);
            if (autok[1][2].compareTo(autok[2][2]) < 1) {
                System.out.println("Márka: " + autok[1][0] + " Szín: " + autok[1][1] + " Rendszám: " + autok[1][2]);
                System.out.println("Márka: " + autok[2][0] + " Szín: " + autok[2][1] + " Rendszám: " + autok[2][2]);
            } else {
                System.out.println("Márka: " + autok[2][0] + " Szín: " + autok[2][1] + " Rendszám: " + autok[2][2]);
                System.out.println("Márka: " + autok[1][0] + " Szín: " + autok[1][1] + " Rendszám: " + autok[2][2]);
            }
        } else if (autok[1][2].compareTo(autok[0][2]) < 1 && (autok[1][2].compareTo(autok[2][2]) < 1)) {
            System.out.println("Márka: " + autok[1][0] + " Szín: " + autok[1][1] + " Rendszám: " + autok[1][2]);
            if (autok[0][2].compareTo(autok[2][2]) < 1) {
                System.out.println("Márka: " + autok[0][0] + " Szín: " + autok[0][1] + " Rendszám: " + autok[0][2]);
                System.out.println("Márka: " + autok[2][0] + " Szín: " + autok[2][1] + " Rendszám: " + autok[2][2]);
            } else {
                System.out.println("Márka: " + autok[2][0] + " Szín: " + autok[2][1] + " Rendszám: " + autok[2][2]);
                System.out.println("Márka: " + autok[0][0] + " Szín: " + autok[0][1] + " Rendszám: " + autok[0][2]);
            }
        } else {
            System.out.println("Márka: " + autok[2][0] + " Szín: " + autok[2][1] + " Rendszám: " + autok[2][2]);
            if (autok[0][2].compareTo(autok[1][2]) < 1) {
                System.out.println("Márka: " + autok[0][0] + " Szín: " + autok[0][1] + " Rendszám: " + autok[0][2]);
                System.out.println("Márka: " + autok[1][0] + " Szín: " + autok[1][1] + " Rendszám: " + autok[1][2]);
            } else {
                System.out.println("Márka: " + autok[1][0] + " Szín: " + autok[1][1] + " Rendszám: " + autok[1][2]);
                System.out.println("Márka: " + autok[0][0] + " Szín: " + autok[0][1] + " Rendszám: " + autok[0][2]);
            }
        }
    }
}