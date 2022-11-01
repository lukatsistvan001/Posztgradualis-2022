import java.util.Scanner;

// Olvass be két tört számot és írd ki hogy egyenlőek-e vagy sem.

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Add meg az első tört számot: ");
        double dNum1 = sc.nextDouble();
        sc.nextLine();
        System.out.print("Add meg a második tört számot: ");
        double dNum2 = sc.nextDouble();
        sc.nextLine();

        if (dNum1 == dNum2) {
            System.out.print("A megadott két szám egyenlő.");
        } else {
            System.out.print("A megadott két szám nem egyenlő.");
        }
    }
}