import java.util.Scanner;

/*
Írj programot ami beolvas egy egész, majd eldönti, hogy az alábbi esetek közül melyik áll fenn:
a. a szám páratlan
b. a szám páros és osztható néggyel, de nem osztható nyolccal
c. a szám páros és osztható nyolccal és néggyel is
d. a szám páros de nem osztható sem nyolccal sem néggyel
e. valami más féle szám
*/

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Add meg az egész számot: ");
        int iNum = sc.nextInt();
        sc.nextLine();
        if ((iNum % 2 == 1) || (iNum % 2 == -1))
            System.out.println("A megadott szám páratlan.");
        else if ((iNum % 4 == 0) && (iNum % 8 != 0))
            System.out.println("A megadott szám páros és osztható néggyel, de nem osztható nyolccal.");
        else if ((iNum % 4 == 0) && (iNum % 8 == 0))
            System.out.println("A megadott szám páros és osztható nyolccal és néggyel is.");
        else if ((iNum % 4 != 0) && (iNum % 8 != 0))
            System.out.println("A megadott szám páros de nem osztható sem nyolccal sem néggyel.");
        else System.out.println("A megadott szám valami más féle szám.");
    }
}
// Megjegyzés: Az e. irreleváns.