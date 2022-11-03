import java.util.Scanner;

/*
Írj programot ami beolvas két karakterláncot (vezetéknévnek és keresztnévnek), majd ebből kiír
egy szépen formázott nevet. A szépen formázott azt jelenti, hogy a vezetéknév és a keresztnév
első betűje nagy betű, a többi mind kicsi.
*/

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Add meg az első karakterláncot (vezetéknév): ");
        String sVezeteknev = sc.nextLine();
        System.out.print("Add meg a második karakterláncot (keresztnév): ");
        String sKeresztnev = sc.nextLine();

        System.out.println("A szépen formázott név: "
                + sVezeteknev.toUpperCase().charAt(0) + sVezeteknev.toLowerCase().substring(1) + " "
                + sKeresztnev.toUpperCase().charAt(0) + sKeresztnev.toLowerCase().substring(1));
    }
}