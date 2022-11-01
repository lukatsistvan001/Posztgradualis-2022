import java.util.Scanner;

/*
Írj egy programot ami beolvas egy tetszőleges egész számot, majd egy 0 és 4 közötti egész számot.
Ha a második szám nem 0 és négy közötti, dorongold le a felhasználót.
Ezek után írd ki az első szám másodikkal binárisan eltolt értékeit mind a három tanult bináris
eltolás operátorral (<<, >>, >>>)
*/

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Adj meg egy egész számot: ");
        int iNum = sc.nextInt();
        sc.nextLine();
        System.out.print("Adj meg egy 0 és 4 közötti egész számot: ");
        int iStep = sc.nextInt();
        sc.nextLine();
        if ((iStep < 0) || (iStep > 4)) {
            System.out.println("0 és 4 közötti legyen!");
            iStep = sc.nextInt();
            sc.nextLine();
        }

        System.out.println("A << operátorral az eredmény: " + (iNum << iStep));
        System.out.println("A >> operátorral az eredmény: " + (iNum >> iStep));
        System.out.println("A >>> operátorral az eredmény: " + (iNum >>> iStep));
    }
}