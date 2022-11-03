import java.util.Scanner;

/*
Írj programot mely beolvas két karakterláncot. Majd megnézi, hogy az egyik benne van-e a
másikban és ennek megfelelően kírja, hogy az első része a másiknak, a második az elsőnek,
egyenlőek, vagy egyik sem.
*/

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Add meg az első karakterláncot: ");
        String sElsoKarakterlanc = sc.nextLine();
        System.out.print("Add meg a második karakterláncot: ");
        String sMasodikKarakterlanc = sc.nextLine();

        if (sElsoKarakterlanc.equals(sMasodikKarakterlanc))
            System.out.println("A két karakterlánc egyenlő.");
        else if (sMasodikKarakterlanc.contains(sElsoKarakterlanc))
            System.out.println("Az első karakterlánc része a másodiknak.");
        else if (sElsoKarakterlanc.contains(sMasodikKarakterlanc))
            System.out.println("A második karakterlánc része az elsőnek.");
        else
            System.out.println("Nincs összefüggés a két karakterlánc között.");
    }
}