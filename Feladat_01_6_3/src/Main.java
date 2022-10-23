// 6.3
// Egyszerű palindrom teszt: Írjon programot amely beolvas egy karakterláncot és arról eldönti, hogy
// az visszafelé olvasva tökéletesen megegyezik-e önmagával.

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Add be az ellenőrzendő karakterláncot:");
        String szoveg = sc.next();
        boolean pallindrom = true;
        for (int i=0; i<szoveg.length()/2 && pallindrom; i++) {
            if (szoveg.charAt(i) != szoveg.charAt(szoveg.length() - 1 - i)) {
                pallindrom = false;
            }
        }
        if (pallindrom){
            System.out.print("Pallindrom.");
        }
        else{
            System.out.print("Nem pallindrom.");
        }
    }
}