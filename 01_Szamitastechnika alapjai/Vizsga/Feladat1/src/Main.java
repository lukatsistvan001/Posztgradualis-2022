public class Main {
    private static String karakterlanc = "Lukats Istvan";

    public static void main(String[] args) {
        kicserel(4, 's');
        System.out.println(karakterlanc);
    }

    private static void kicserel(int szam, char c) {
        karakterlanc = karakterlanc.substring(0, szam) + c +
                karakterlanc.substring(szam + 1, karakterlanc.length());
    }
}