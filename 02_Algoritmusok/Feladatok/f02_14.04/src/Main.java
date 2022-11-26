/*
Egy 𝑛 egész szám esetén 𝑛! jelöli 1-től 𝑛-ig a számok szorzatát. 5! = 1 ⋅ 2 ⋅ 3 ⋅ 4 ⋅ 5. Írjon rekurzív
algoritmust amely kiszámolja 𝑛! értékét.
*/

public class Main {
    public static void main(String[] args) {
        int eredmeny = faktorialisSzorzat(-3);
        System.out.println(eredmeny);
    }

    private static int faktorialisSzorzat(int szam) {
        if (szam == 0)
            return 0;
        if (szam > 0)
            return faktorialisSzorzatNemNegativSzamra(szam);
        else
            return faktorialisSzorzatNegativSzamra(szam);
    }

    private static int faktorialisSzorzatNemNegativSzamra(int szam) {
        if (szam == 1)
            return szam;
        return faktorialisSzorzatNemNegativSzamra(szam - 1) * szam;
    }

    private static int faktorialisSzorzatNegativSzamra(int szam) {
        if (szam == -1)
            return szam;
        return faktorialisSzorzatNegativSzamra(szam + 1) * szam;
    }
}