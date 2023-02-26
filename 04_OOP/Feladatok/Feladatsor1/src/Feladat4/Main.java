/*
Készítsünk egy osztályt törtek reprezentációjára, és az osztályon belüli metódusok
formájában implementáljuk az alapműveleteket (összeadás, kivonás, szorzás,
osztás), illetve az ezek elvégzéséhez szükséges segédműveleteket. Készítsünk egy
programot, amelyben szemléltetjük osztályunk működését.
*/

package Feladat4;

public class Main {
    public static void main(String[] args) {
        Fractional n1 = new Fractional(3, 4);
        Fractional n2 = new Fractional(5, 8);
        Fractional sum = n1.add(n2);
        System.out.println(n1.getNumerator() + "/" + n1.getDenominator() + " + " +
                n2.getNumerator() + "/" + n2.getDenominator() + " = " +
                sum.getNumerator() + "/" + sum.getDenominator());
        Fractional sub = n1.subtract(n2);
        System.out.println(n1.getNumerator() + "/" + n1.getDenominator() + " - " +
                n2.getNumerator() + "/" + n2.getDenominator() + " = " +
                sub.getNumerator() + "/" + sub.getDenominator());
        Fractional mul = n1.multiply(n2);
        System.out.println(n1.getNumerator() + "/" + n1.getDenominator() + " * " +
                n2.getNumerator() + "/" + n2.getDenominator() + " = " +
                mul.getNumerator() + "/" + mul.getDenominator());
        Fractional div = n1.divide(n2);
        System.out.println(n1.getNumerator() + "/" + n1.getDenominator() + " / " +
                n2.getNumerator() + "/" + n2.getDenominator() + " = " +
                div.getNumerator() + "/" + div.getDenominator());
    }
}