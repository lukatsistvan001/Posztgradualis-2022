/*
Írjunk programot, amely kiszámolja a parancssor argumentumainak összegét,
csak az egész számokat véve figyelembe (kivételkezelést alkalmazzunk). A
programot egészítsük ki olyan módon, hogy külön számolja ki a páratlan,
illetve páros argumentumok összegeit.
*/

package Feladat1;

public class Main {
    public static void main(String[] args) {

        int evenSum = 0;
        int oddSum = 0;
        int number = 0;

        for (int i = 0; i < args.length; i++) {
            try {
                number = Integer.parseInt(args[i]);
                System.out.print("Argument: " + number);
                if (number % 2 == 0) {
                    System.out.println(" even.");
                    evenSum += number;
                } else {
                    System.out.println(" odd.");
                    oddSum += number;
                }
            } catch (NumberFormatException exception) {
            }
        }

        System.out.println("Odd arguments sum: " + oddSum);
        System.out.println("Even arguments sum: " + evenSum);
    }
}