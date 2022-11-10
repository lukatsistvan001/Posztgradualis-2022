import java.util.Scanner;

/*
Írj egy programot ami karakterláncokat kér be addig amígy *-ot nem kap, majd kiírja a leghoszabb
megadott karakterlánc hosszát.
*/

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String sKarakterlanc = "";
        int iLeghosszabbHossz = 0;
        while (sKarakterlanc.compareTo("*") != 0) {
            System.out.print("Add meg a karakterláncot: ");
            sKarakterlanc = sc.nextLine();
            if (sKarakterlanc.length() > iLeghosszabbHossz)
                iLeghosszabbHossz = sKarakterlanc.length();
        }

        System.out.println("A leghosszabb megadott karakterlánc hossza: " + iLeghosszabbHossz);
    }
}