import java.util.Scanner;

// 6.2
// Kérjen be egy karakterláncot. Majd ahány karakter van ebben (pl. alma esetén 4, kiskutya esetén
// 8), annyiszor írja ki az első karaktert (pl. alma esetén aaaa, kiskutya esetén kkkkkkkk).

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Add meg a karakterláncot:");
        String szoveg = sc.next();

        for (int i=0; i < szoveg.length(); i++){
            System.out.print(szoveg.charAt(0));
        }
    }
}