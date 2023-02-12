/*
Írjunk programot, amely kiszámolja a parancssor argumentumainak összegét,
csak az egész számokat véve figyelembe (kivételkezelést alkalmazzunk). A
programot egészítsük ki olyan módon, hogy külön számolja ki a páratlan,
illetve páros argumentumok összegeit.
*/

public class FirstExample {
    public static void main(String[] args) {
        int evenSum = 0;
        int oddSum = 0;
        int number = 0;
        for (int i = 0; i < args.length; i++) {
            try {
                number = Integer.parseInt(args[i]);
                if (i % 2 == 0)
                    evenSum += number;
                else
                    oddSum += number;
            } catch (NumberFormatException ex) {
            }
        }
        System.out.println("Even arguments sum: " + evenSum);
        System.out.println("Odd arguments sum: " + oddSum);
    }
}
