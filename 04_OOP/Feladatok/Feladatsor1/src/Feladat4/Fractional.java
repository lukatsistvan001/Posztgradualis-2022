package Feladat4;

public class Fractional {

    private int numerator;
    private int denominator;

    public Fractional(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("The denominator is zero.");
        }
        if (numerator == 0) {
            this.numerator = 0;
            this.denominator = 1;
        } else {
            this.numerator = numerator;
            this.denominator = denominator;
        }
        if (denominator < 0) {
            this.numerator = -1 * numerator;
            this.denominator = -1 * denominator;
        }
    }

    public int getNumerator() {
        return this.numerator;
    }

    public int getDenominator() {
        return this.denominator;
    }

    public Fractional add(Fractional fractional) {
        Fractional solution = new Fractional(
                this.numerator * fractional.denominator + fractional.numerator * this.denominator,
                this.denominator * fractional.denominator);
        solution.reduce();
        return solution;
    }

    public Fractional subtract(Fractional fractional) {
        Fractional solution = new Fractional(
                this.numerator * fractional.denominator - fractional.numerator * this.denominator,
                this.denominator * fractional.denominator);
        solution.reduce();
        return solution;
    }

    public Fractional multiply(Fractional fractional) {
        Fractional solution = new Fractional(
                this.numerator * fractional.numerator,
                this.denominator * fractional.denominator);
        solution.reduce();
        return solution;
    }

    public Fractional divide(Fractional fractional) {
        Fractional solution = new Fractional(
                this.numerator * fractional.denominator,
                this.denominator * fractional.numerator);
        solution.reduce();
        return solution;
    }

    public void reduce() {
        if (numerator < denominator) {
            for (int i = numerator; i > 0; i--) {
                if (numerator % i == 0 && denominator % i == 0) {
                    this.numerator = numerator / i;
                    this.denominator = denominator / i;
                }
            }
        } else {
            for (int i = denominator; i > 0; i--) {
                if (numerator % i == 0 && denominator % i == 0) {
                    this.numerator = numerator / i;
                    this.denominator = denominator / i;
                }
            }
        }
    }
}