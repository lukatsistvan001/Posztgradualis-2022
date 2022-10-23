import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Adj meg egy számot:");
        int hanyszor = sc.nextInt();
        sc.nextLine();
        
        for (int i=0; i<hanyszor; i++){
            System.out.println("Hello.");
        }
//        Scanner sc = new Scanner(System.in);

//        System.out.print("Adj be egy számot: ");
//        Integer hanyszor = sc.nextInt();
    }
}
