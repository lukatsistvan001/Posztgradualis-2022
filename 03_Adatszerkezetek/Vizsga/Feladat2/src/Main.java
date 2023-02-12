public class Main {
    public static void main(String[] args) {
        Ugroiskola ugroiskola = new Ugroiskola();

        ugroiskola.EgylabasElemHozzaadasaSorVegere();
        System.out.println("Az ugróiskola jelenleg " + ugroiskola.Hossz() + " centiméter hosszú és " +
                ugroiskola.Szelesseg() + " centiméter széles.");

        ugroiskola.EgylabasElemHozzaadasaSorVegere();
        System.out.println("Az ugróiskola jelenleg " + ugroiskola.Hossz() + " centiméter hosszú és " +
                ugroiskola.Szelesseg() + " centiméter széles.");

        ugroiskola.EgylabasElemHozzaadasaSorVegere();
        System.out.println("Az ugróiskola jelenleg " + ugroiskola.Hossz() + " centiméter hosszú és " +
                ugroiskola.Szelesseg() + " centiméter széles.");

        ugroiskola.TerpeszElemHozzaadasaSorVegere();
        System.out.println("Az ugróiskola jelenleg " + ugroiskola.Hossz() + " centiméter hosszú és " +
                ugroiskola.Szelesseg() + " centiméter széles.");

        ugroiskola.EgylabasElemHozzaadasaSorVegere();
        System.out.println("Az ugróiskola jelenleg " + ugroiskola.Hossz() + " centiméter hosszú és " +
                ugroiskola.Szelesseg() + " centiméter széles.");

        ugroiskola.TerpeszElemHozzaadasaSorVegere();
        System.out.println("Az ugróiskola jelenleg " + ugroiskola.Hossz() + " centiméter hosszú és " +
                ugroiskola.Szelesseg() + " centiméter széles.");

        ugroiskola.EgylabasElemHozzaadasaSorVegere();
        System.out.println("Az ugróiskola jelenleg " + ugroiskola.Hossz() + " centiméter hosszú és " +
                ugroiskola.Szelesseg() + " centiméter széles.");
    }
}

class Ugroiskola {
    int elemMeretCMben = 50;
    int hasznosElemekSzama;
    private int[] taroltElemek;

    public Ugroiskola() {
        hasznosElemekSzama = 0;
        taroltElemek = new int[1];
    }

    public int Hossz() {
        return hasznosElemekSzama * elemMeretCMben;
    }

    public int Szelesseg() {
        boolean vanTerpeszElemASorban = false;
        for (int i = 0; i < hasznosElemekSzama; i++) {
            if (taroltElemek[i] == 3) {
                vanTerpeszElemASorban = true;
                break;
            }
        }
        if (vanTerpeszElemASorban)
            return 3 * elemMeretCMben;
        else return elemMeretCMben;
    }

    public void EgylabasElemHozzaadasaSorVegere() {
        if (hasznosElemekSzama + 1 <= taroltElemek.length) {
            //van hely
            taroltElemek[hasznosElemekSzama] = 1;
            hasznosElemekSzama++;
        } else {
            //nincs hely
            int[] valtozottErtekek = new int[taroltElemek.length * 2];
            for (int i = 0; i < hasznosElemekSzama; i++) {
                valtozottErtekek[i] = taroltElemek[i];
            }
            taroltElemek = valtozottErtekek;
            taroltElemek[hasznosElemekSzama] = 1;
            hasznosElemekSzama++;
        }
    }

    public void TerpeszElemHozzaadasaSorVegere() {
        if (hasznosElemekSzama + 1 <= taroltElemek.length) {
            //van hely
            taroltElemek[hasznosElemekSzama] = 3;
            hasznosElemekSzama++;
        } else {
            //nincs hely
            int[] valtozottErtekek = new int[taroltElemek.length * 2];
            for (int i = 0; i < hasznosElemekSzama; i++) {
                valtozottErtekek[i] = taroltElemek[i];
            }
            taroltElemek = valtozottErtekek;
            taroltElemek[hasznosElemekSzama] = 3;
            hasznosElemekSzama++;
        }
    }

    public void ElemTorleseSorVegerol() {
        --hasznosElemekSzama;
    }

    public void ElemTorlesePoziciorol(int poz) {
        for (int i = poz; i < hasznosElemekSzama - 1; i++) {
            taroltElemek[i] = taroltElemek[i + 1];
        }
        --hasznosElemekSzama;
    }

    public void TerpeszElemHozzaadasaAdottPoziciora(int poz) {
        if (hasznosElemekSzama + 1 <= taroltElemek.length) {
            // van hely a tömbben
            for (int i = hasznosElemekSzama; i > poz; --i) {
                taroltElemek[i] = taroltElemek[i - 1];
            }
            taroltElemek[poz] = 3;
        } else {
            // nincs hely az új elemnek
            int[] valtozottErtekek = new int[taroltElemek.length * 2];
            for (int i = 0; i < poz; i++) {
                valtozottErtekek[i] = taroltElemek[i];
            }
            valtozottErtekek[poz] = 3;
            for (int i = poz; i < taroltElemek.length; i++) {
                valtozottErtekek[i + 1] = taroltElemek[i];
            }
            taroltElemek = valtozottErtekek;
        }
        ++hasznosElemekSzama;
    }

    public void EgylabasElemHozzaadasaAdottPoziciora(int poz) {
        if (hasznosElemekSzama + 1 <= taroltElemek.length) {
            // van hely a tömbben
            for (int i = hasznosElemekSzama; i > poz; --i) {
                taroltElemek[i] = taroltElemek[i - 1];
            }
            taroltElemek[poz] = 1;
        } else {
            // nincs hely az új elemnek
            int[] valtozottErtekek = new int[taroltElemek.length * 2];
            for (int i = 0; i < poz; i++) {
                valtozottErtekek[i] = taroltElemek[i];
            }
            valtozottErtekek[poz] = 1;
            for (int i = poz; i < taroltElemek.length; i++) {
                valtozottErtekek[i + 1] = taroltElemek[i];
            }
            taroltElemek = valtozottErtekek;
        }
        ++hasznosElemekSzama;
    }
}