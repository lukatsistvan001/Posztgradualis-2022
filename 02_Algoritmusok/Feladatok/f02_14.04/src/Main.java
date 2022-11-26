/*
Egy 𝑛 egész szám esetén 𝑛! jelöli 1-től 𝑛-ig a számok szorzatát. 5! = 1 ⋅ 2 ⋅ 3 ⋅ 4 ⋅ 5. Írjon rekurzív
algoritmust amely kiszámolja 𝑛! értékét.
*/

public class Main {
    public static void main(String[] args) {
        int eredmeny = faktor(-3);
        System.out.println(eredmeny);
    }

    private static int faktor(int szam) {
        if (szam == 0)
            return 1;
        if (szam > 0)
            return faktorNemNegativSzamra(szam);
        else
            return faktorNegativSzamra(szam);
    }

    private static int faktorNemNegativSzamra(int szam) {
        if (szam == 0)
            return 1;
        return faktorNemNegativSzamra(szam - 1) * szam;
    }

    private static int faktorNegativSzamra(int szam) {
        if (szam == -1)
            return szam;
        return faktorNegativSzamra(szam + 1) * szam;
    }
}