import java.util.Scanner;

// 6.1
// Kérjen be egy számot és írja ki, hogy helló annyiszor. (Emlékezzünk a régi szenvedős megoldásra
// és értékeljük a haladást.)

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Adj meg egy számot:");
        int hanyszor = sc.nextInt();
        sc.nextLine();

        for (int i=0; i<hanyszor; i++){
            System.out.println("Hello.");
        }
    }
}
