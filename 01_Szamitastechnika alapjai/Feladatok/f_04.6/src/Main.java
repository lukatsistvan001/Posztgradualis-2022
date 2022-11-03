import java.util.Random;
import java.util.Scanner;

/*
Írj egy programot ami véletlenszerűen egyenletesen választ egy hossz értéket 5 és 10 között (5 és
10 is lehet), majd generál egy olyan véletlen karakterláncot, aminek hossza ez a szám. A karakterei
pedig kizárólag kis betűk, nagy betűk, vagy számok. Pl: hossz 7, karakterlánc: aFst5Gw.
*/

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Random r = new Random();
        int iSzamokSzama = '9' - '0' + 1;
        int iNagybetukSzama = 'Z' - 'A' + 1;
        int iKisbetukSzama = 'z' - 'a' + 1;
        String sKarakterlanc = "";
        int iKarakterlancHossza = r.nextInt(5, 11);
        int iVeletlenSzam;
        char cVeletlenKarakter;

        switch (iKarakterlancHossza) {
            case 10:
                iVeletlenSzam = r.nextInt(iSzamokSzama + iNagybetukSzama + iKisbetukSzama);
                if (iVeletlenSzam < iSzamokSzama)
                    cVeletlenKarakter = (char) ('0' + iVeletlenSzam);
                else if (iVeletlenSzam < iSzamokSzama + iNagybetukSzama)
                    cVeletlenKarakter = (char) ('A' + iVeletlenSzam - iSzamokSzama);
                else
                    cVeletlenKarakter = (char) ('a' + iVeletlenSzam - iSzamokSzama - iNagybetukSzama);
                sKarakterlanc = sKarakterlanc + cVeletlenKarakter;
            case 9:
                iVeletlenSzam = r.nextInt(iSzamokSzama + iNagybetukSzama + iKisbetukSzama);
                if (iVeletlenSzam < iSzamokSzama)
                    cVeletlenKarakter = (char) ('0' + iVeletlenSzam);
                else if (iVeletlenSzam < iSzamokSzama + iNagybetukSzama)
                    cVeletlenKarakter = (char) ('A' + iVeletlenSzam - iSzamokSzama);
                else
                    cVeletlenKarakter = (char) ('a' + iVeletlenSzam - iSzamokSzama - iNagybetukSzama);
                sKarakterlanc = sKarakterlanc + cVeletlenKarakter;
            case 8:
                iVeletlenSzam = r.nextInt(iSzamokSzama + iNagybetukSzama + iKisbetukSzama);
                if (iVeletlenSzam < iSzamokSzama)
                    cVeletlenKarakter = (char) ('0' + iVeletlenSzam);
                else if (iVeletlenSzam < iSzamokSzama + iNagybetukSzama)
                    cVeletlenKarakter = (char) ('A' + iVeletlenSzam - iSzamokSzama);
                else
                    cVeletlenKarakter = (char) ('a' + iVeletlenSzam - iSzamokSzama - iNagybetukSzama);
                sKarakterlanc = sKarakterlanc + cVeletlenKarakter;
            case 7:
                iVeletlenSzam = r.nextInt(iSzamokSzama + iNagybetukSzama + iKisbetukSzama);
                if (iVeletlenSzam < iSzamokSzama)
                    cVeletlenKarakter = (char) ('0' + iVeletlenSzam);
                else if (iVeletlenSzam < iSzamokSzama + iNagybetukSzama)
                    cVeletlenKarakter = (char) ('A' + iVeletlenSzam - iSzamokSzama);
                else
                    cVeletlenKarakter = (char) ('a' + iVeletlenSzam - iSzamokSzama - iNagybetukSzama);
                sKarakterlanc = sKarakterlanc + cVeletlenKarakter;
            case 6:
                iVeletlenSzam = r.nextInt(iSzamokSzama + iNagybetukSzama + iKisbetukSzama);
                if (iVeletlenSzam < iSzamokSzama)
                    cVeletlenKarakter = (char) ('0' + iVeletlenSzam);
                else if (iVeletlenSzam < iSzamokSzama + iNagybetukSzama)
                    cVeletlenKarakter = (char) ('A' + iVeletlenSzam - iSzamokSzama);
                else
                    cVeletlenKarakter = (char) ('a' + iVeletlenSzam - iSzamokSzama - iNagybetukSzama);
                sKarakterlanc = sKarakterlanc + cVeletlenKarakter;
            case 5:
                iVeletlenSzam = r.nextInt(iSzamokSzama + iNagybetukSzama + iKisbetukSzama);
                if (iVeletlenSzam < iSzamokSzama)
                    cVeletlenKarakter = (char) ('0' + iVeletlenSzam);
                else if (iVeletlenSzam < iSzamokSzama + iNagybetukSzama)
                    cVeletlenKarakter = (char) ('A' + iVeletlenSzam - iSzamokSzama);
                else
                    cVeletlenKarakter = (char) ('a' + iVeletlenSzam - iSzamokSzama - iNagybetukSzama);
                sKarakterlanc = sKarakterlanc + cVeletlenKarakter;
            case 4:
                iVeletlenSzam = r.nextInt(iSzamokSzama + iNagybetukSzama + iKisbetukSzama);
                if (iVeletlenSzam < iSzamokSzama)
                    cVeletlenKarakter = (char) ('0' + iVeletlenSzam);
                else if (iVeletlenSzam < iSzamokSzama + iNagybetukSzama)
                    cVeletlenKarakter = (char) ('A' + iVeletlenSzam - iSzamokSzama);
                else
                    cVeletlenKarakter = (char) ('a' + iVeletlenSzam - iSzamokSzama - iNagybetukSzama);
                sKarakterlanc = sKarakterlanc + cVeletlenKarakter;
            case 3:
                iVeletlenSzam = r.nextInt(iSzamokSzama + iNagybetukSzama + iKisbetukSzama);
                if (iVeletlenSzam < iSzamokSzama)
                    cVeletlenKarakter = (char) ('0' + iVeletlenSzam);
                else if (iVeletlenSzam < iSzamokSzama + iNagybetukSzama)
                    cVeletlenKarakter = (char) ('A' + iVeletlenSzam - iSzamokSzama);
                else
                    cVeletlenKarakter = (char) ('a' + iVeletlenSzam - iSzamokSzama - iNagybetukSzama);
                sKarakterlanc = sKarakterlanc + cVeletlenKarakter;
            case 2:
                iVeletlenSzam = r.nextInt(iSzamokSzama + iNagybetukSzama + iKisbetukSzama);
                if (iVeletlenSzam < iSzamokSzama)
                    cVeletlenKarakter = (char) ('0' + iVeletlenSzam);
                else if (iVeletlenSzam < iSzamokSzama + iNagybetukSzama)
                    cVeletlenKarakter = (char) ('A' + iVeletlenSzam - iSzamokSzama);
                else
                    cVeletlenKarakter = (char) ('a' + iVeletlenSzam - iSzamokSzama - iNagybetukSzama);
                sKarakterlanc = sKarakterlanc + cVeletlenKarakter;
            case 1:
                iVeletlenSzam = r.nextInt(iSzamokSzama + iNagybetukSzama + iKisbetukSzama);
                if (iVeletlenSzam < iSzamokSzama)
                    cVeletlenKarakter = (char) ('0' + iVeletlenSzam);
                else if (iVeletlenSzam < iSzamokSzama + iNagybetukSzama)
                    cVeletlenKarakter = (char) ('A' + iVeletlenSzam - iSzamokSzama);
                else
                    cVeletlenKarakter = (char) ('a' + iVeletlenSzam - iSzamokSzama - iNagybetukSzama);
                sKarakterlanc = sKarakterlanc + cVeletlenKarakter;
        }
        System.out.println("A véletlen karakterlánc: " + sKarakterlanc);
    }
}