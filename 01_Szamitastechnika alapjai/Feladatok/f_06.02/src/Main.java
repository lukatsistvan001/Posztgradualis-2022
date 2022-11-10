import java.util.Scanner;

/*
Kérjen be egy karakterláncot. Majd ahány karakter van ebben (pl. alma esetén 4, kiskutya esetén
8), annyiszor írja ki az első karaktert (pl. alma esetén aaaa, kiskutya esetén kkkkkkkk).
*/

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Adj be egy karakterláncot: ");
        String sKarakterlanc = sc.nextLine();

        for (int i = 0; i < sKarakterlanc.length(); i++) {
            System.out.print(sKarakterlanc.charAt(0));
        }
    }
}