import java.util.Scanner;

/*
Írjon programot ami addig olvas be karakterláncokat ameddig azt nem adjuk meg neki, hogy Exit.
Minden beolvasott karakterláncot írjon is ki azonnal fordítva, de az Exit-et már ne.
*/

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String sKarakterlanc;
        do {
            System.out.print("Add meg a karakterláncot: ");
            sKarakterlanc = sc.nextLine();
            if (sKarakterlanc.compareTo("Exit") != 0) {
                for (int i = sKarakterlanc.length() - 1; i >= 0; i--) {
                    System.out.print(sKarakterlanc.charAt(i));
                }
                System.out.println();
            }
        } while (sKarakterlanc.compareTo("Exit") != 0);
    }
}