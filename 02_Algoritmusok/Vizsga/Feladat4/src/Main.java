public class Main {
    public static void main(String[] args) {
        String karakterlanc = "abcab";
        int eredmeny = algoritmus(karakterlanc);
        System.out.println(eredmeny);
    }

    private static int algoritmus(String karakterlanc) {
        if (karakterlanc.length() == 1)
            return 1;
        else return algoritmus(0, karakterlanc);
    }

    private static int algoritmus(int szam, String karakterlanc) {
        if (karakterlanc.length() == 1)
            return szam + 1;
        else {
            char elsoKarakter = karakterlanc.charAt(0);
            if (karakterlanc.indexOf(elsoKarakter, 1) != -1) {
                String ujKarakterlanc = karakterlanc.substring(1, karakterlanc.indexOf(elsoKarakter, 1));
                return szam + algoritmus(szam + 1, ujKarakterlanc);
            } else {
                String ujKarakterlanc = karakterlanc.substring(1);
                return szam + algoritmus(szam + 1, ujKarakterlanc);
            }
        }
    }
}