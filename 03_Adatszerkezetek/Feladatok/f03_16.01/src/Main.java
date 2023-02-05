public class Main {
    public static void main(String[] args) {
        dinTomb tomb = new dinTomb();
        System.out.println("A tömb hossza: " + tomb.hossz()); // 0

        tomb.pozicioBeszurasa(0, 1); // [1]
        tomb.pozicioBeszurasa(0, 2); // [2,1]
        tomb.pozicioBeszurasa(1, 3); // [2,3,1]

        System.out.println("A tömb hossza: " + tomb.hossz()); // 3
        tomb.tombKiirasa(); // [2,3,1]

        System.out.println(tomb.pozicioLekerdezese(2)); // 1
        tomb.pozicioErtekadasa(2, 5); // [2,3,4]
        tomb.pozicioBeszurasa(2, 4); // [2,3,4,5]
        tomb.tombKiirasa();

        tomb.pozicioTorlese(0); // [3,4,5]
        tomb.tombKiirasa(); // [3,4,5]
        tomb.pozicioTorlese(1); // [3,5]
        tomb.tombKiirasa();

        tomb.pozicioBeszurasa(0, 0);
        tomb.tombKiirasa();
    }
}

class dinTomb {
    private double[] taroltErtek;

    public dinTomb() {
        taroltErtek = new double[0];
    }

    public int hossz() {
        return this.taroltErtek.length;
    }

    public void pozicioErtekadasa(int poz, double ertek) {
        this.taroltErtek[poz] = ertek;
    }

    public double pozicioLekerdezese(int poz) {
        return this.taroltErtek[poz];
    }

    public void pozicioTorlese(int poz) {
        double[] ujTaroltErtek = new double[this.taroltErtek.length - 1];
        for (int i = 0; i < poz; i++) {
            ujTaroltErtek[i] = this.taroltErtek[i];
        }
        for (int i = poz; i < this.taroltErtek.length - 1; i++) {
            ujTaroltErtek[i] = this.taroltErtek[i + 1];
        }
        this.taroltErtek = ujTaroltErtek;
    }

    public void pozicioBeszurasa(int poz, double ertek) {
        double[] ujTaroltErtek = new double[this.taroltErtek.length + 1];
        for (int i = 0; i < poz; i++) {
            ujTaroltErtek[i] = this.taroltErtek[i];
        }
        ujTaroltErtek[poz] = ertek;
        for (int i = poz; i < this.taroltErtek.length; i++) {
            ujTaroltErtek[i + 1] = this.taroltErtek[i];
        }
        this.taroltErtek = ujTaroltErtek;
    }

    public void tombKiirasa() {
        for (int i = 0; i < this.taroltErtek.length; i++) {
            System.out.print(this.taroltErtek[i] + " ");
        }
        System.out.println();
    }
}