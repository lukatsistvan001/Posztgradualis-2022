import java.util.Scanner;

/*
Írj egy programot ami bekér egy karakterláncot, majd kiírja ezt úgy, hogy az első és utolsó
karaktert felcseréli. Figyeljünk, hogy minden hosszúságú karakterláncra jól működjön a program.
*/


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Add meg a karakterláncot: ");
        String s = sc.nextLine();
        if (s.length() > 1)
            System.out.println(s.charAt(s.length() - 1) + s.substring(1, s.length() - 1) + s.charAt(0));
        else
            System.out.println(s);
    }
}