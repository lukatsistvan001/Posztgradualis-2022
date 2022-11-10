import java.util.Scanner;

/*
Írjon egy programot ami bekér egy számot, majd létrehoz egy ekkora tömböt amit megtölt
véletlen számokkal. Ezek után egy másik tömbbe számoljuk meg, hogy az adott pozícióban lévő
elemhez képest az eredeti tömbben hány nagyobb elem volt.
A tömbhossz megadása utáni futási időt mérd meg és az algoritmus futása után írd ki mp-ben.
Példa: első tömb: {0.7, 0.6, 0.3, 0.9, 0.2, 0.8}
második tömb: { 2, 3, 4, 0, 5, 1}
*/

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Add meg a tömb nagyságát: ");
        int tombNagysaga = sc.nextInt();
        sc.nextLine();

        double[] veletlenSzamok = new double[tombNagysaga];
        System.out.print("{");
        for (int i = 0; i < tombNagysaga; i++) {
            veletlenSzamok[i] = Math.random();
            if (i < tombNagysaga - 1)
                System.out.print(veletlenSzamok[i] + ", ");
            else
                System.out.println(veletlenSzamok[i] + "}");
        }

        int[] hanyNagyobbElem = new int[tombNagysaga];
        for (int i = 0; i < tombNagysaga; i++) {
            for (int j = 0; j < tombNagysaga; j++) {
                if (veletlenSzamok[i] < veletlenSzamok[j])
                    hanyNagyobbElem[i]++;
            }
        }

        System.out.print("{");
        for (int i = 0; i < tombNagysaga; i++) {
            if (i < tombNagysaga - 1)
                System.out.print(hanyNagyobbElem[i] + ", ");
            else
                System.out.println(hanyNagyobbElem[i] + "}");
        }
    }
}