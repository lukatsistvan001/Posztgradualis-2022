import java.util.Scanner;

/*
Írj egy programot ami bekér egy karakterláncot és az első betű összes előfordulását kicseréli !-re,
kétféle képpen. Egyik esetben karakterláncfeldolgozó műveletekkel, a másik esetben egy Java
beépített metódus használatával (google a barátod). Ha jól dolgozol, a két megoldás azonos kell
legyen.
*/

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Add meg a karakterláncot: ");
        String sEredetiKarakterlanc = sc.nextLine();

        //Első megoldás:
        String sKarakterlanc = sEredetiKarakterlanc;
        for (int i = 1; i <= sKarakterlanc.length() - 2; i++) {
            if (sKarakterlanc.charAt(i) == sKarakterlanc.charAt(0))
                sKarakterlanc = sKarakterlanc.substring(0, i) + '!' + sKarakterlanc.substring(i + 1);
        }
        if (sKarakterlanc.charAt(sKarakterlanc.length() - 1) == sKarakterlanc.charAt(0))
            sKarakterlanc = sKarakterlanc.substring(0, sKarakterlanc.length() - 2) + '!';
        sKarakterlanc = '!' + sKarakterlanc.substring(1);

        System.out.println("Első megoldással az új karakterlánc: " + sKarakterlanc);

        //Második megoldás:
        StringBuilder sUjKarakterlanc = new StringBuilder(sEredetiKarakterlanc);
        char cElsoKarakter = sEredetiKarakterlanc.charAt(0);
        for (int i = 0; i <= sUjKarakterlanc.length() - 1; i++) {
            if (sUjKarakterlanc.charAt(i) == cElsoKarakter)
                sUjKarakterlanc.setCharAt(i, '!');
        }

        System.out.println("Második megoldással az új karakterlánc: " + sUjKarakterlanc);
    }
}