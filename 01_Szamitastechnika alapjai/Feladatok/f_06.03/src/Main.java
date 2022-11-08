import java.util.Scanner;

/*
Egyszerű palindrom teszt: Írjon programot amely beolvas egy karakterláncot és arról eldönti, hogy
az visszafelé olvasva tökéletesen megegyezik-e önmagával.
*/

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Add meg a karakterláncot: ");
        String sKarakterlanc = sc.nextLine();

        boolean bPalindrom = true;
        int iAktualisPozicio = 0;
        while ((bPalindrom) && (iAktualisPozicio < sKarakterlanc.length() / 2)) {
            if (sKarakterlanc.charAt(iAktualisPozicio) != sKarakterlanc.charAt(sKarakterlanc.length() - 1 - iAktualisPozicio))
                bPalindrom = false;
            iAktualisPozicio++;
        }

        if (bPalindrom)
            System.out.println("Palindrom.");
        else
            System.out.println("Nem palindrom.");
    }
}