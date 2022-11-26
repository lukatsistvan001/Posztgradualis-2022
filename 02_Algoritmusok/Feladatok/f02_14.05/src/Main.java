/*
A Fibonacci sorozat elég természetellenes képességű nyulak szaporodását modellezi. A sorozat
úgy indul, hogy 𝐹2 = 𝐹1 = 1. Ezek után egy tetszőleges 𝐹𝑛 érteke úgy számolható ki, hogy a
sorozat két előző elemét összedadjuk 𝐹𝑛 = 𝐹𝑛−1 + 𝐹𝑛−2. (1, 1, 2, 3, 5, 8, 13, …)
Számolja ki 𝐹𝑛 értékét rekurzió segítségéve.
*/

public class Main {

    public static void main(String[] args) {
        long x = Fibonacci(5000);
        System.out.println(x);
    }

    private static long Fibonacci(int n) {
        long fibElozoSzam = 1;
        long fibSzam = 1;
        int meghanyszor = n - 2;
        return Fibonacci(meghanyszor, fibSzam, fibElozoSzam);
    }

    private static long Fibonacci(int meghanyszor, long fibSzam, long fibElozoSzam) {
        if (meghanyszor == 0)
            return fibSzam;
        return Fibonacci(meghanyszor - 1, fibSzam + fibElozoSzam, fibSzam);
    }
}