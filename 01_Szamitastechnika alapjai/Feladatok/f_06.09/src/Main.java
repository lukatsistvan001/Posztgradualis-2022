import java.util.Scanner;

/*
Írj egy programot ami bekér egy karakterláncot, majd törli minden páros helyen álló karakterét és
kiírja a képernyőre.
*/


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Add meg a karakterláncot: ");
        String sKarakterlanc = sc.nextLine();

        String sUjKarakterlanc = "";
        for (int i = 0; i <= sKarakterlanc.length() - 1; i = i + 2)
            sUjKarakterlanc = sUjKarakterlanc + sKarakterlanc.charAt(i);

        System.out.println("Az új karakterlánc: " + sUjKarakterlanc);
    }
}