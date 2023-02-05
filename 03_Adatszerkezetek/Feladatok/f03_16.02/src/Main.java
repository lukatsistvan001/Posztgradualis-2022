public class Main {
    public static void main(String[] args) {
        //halmazok statikus tömbbel
        dinHalmazStatikusTombbel halmaz1 = new dinHalmazStatikusTombbel();
        halmaz1.elemHozzaadasa(0);
        halmaz1.elemHozzaadasa(1);
        halmaz1.elemHozzaadasa(2);
        halmaz1.elemHozzaadasa(2);

        System.out.println(halmaz1.szamossag());

        dinHalmazStatikusTombbel halmaz2 = new dinHalmazStatikusTombbel();
        halmaz2.elemHozzaadasa(1);
        halmaz2.elemHozzaadasa(2);
        halmaz2.elemHozzaadasa(3);

        dinHalmazStatikusTombbel halmaz3 = new dinHalmazStatikusTombbel();
        halmaz3 = halmaz1.egyesites(halmaz2);
        System.out.println("A két halmaz egyesítése: ");
        halmaz3.halmazKiirasa();

        halmaz3 = halmaz1.metszet(halmaz2);
        System.out.println("A két halmaz metszete: ");
        halmaz3.halmazKiirasa();

        halmaz3 = halmaz1.kivonas(halmaz2);
        System.out.println("A két halmaz különbsége: ");
        halmaz3.halmazKiirasa();

        halmaz1.elemKivetele(1);

        halmaz1.urites();
        if (halmaz1.uresE())
            System.out.println("A halmaz üres.");

        //halmazok dinamikus tömbbel
        System.out.println("Halmazok dinamikus tömbökkel.");
        dinHalmazDinamikusTombbel halmaz1dintomb = new dinHalmazDinamikusTombbel();
        dinHalmazDinamikusTombbel halmaz2dintomb = new dinHalmazDinamikusTombbel();
        dinHalmazDinamikusTombbel halmaz3dintomb = new dinHalmazDinamikusTombbel();

        halmaz1dintomb.elemHozzaadas(0);
        halmaz1dintomb.elemHozzaadas(1);
        halmaz1dintomb.elemHozzaadas(2);
        halmaz1dintomb.elemHozzaadas(2);
        System.out.println(halmaz1dintomb.szamossag());

        halmaz2dintomb.elemHozzaadas(1);
        halmaz2dintomb.elemHozzaadas(2);
        halmaz2dintomb.elemHozzaadas(3);

        int[] egyesites = halmaz1dintomb.egyesites(halmaz2dintomb).elemei();
        System.out.println("A két halmaz egyesítése: ");
        for (int i = 0; i < egyesites.length; i++) {
            System.out.print(egyesites[i] + " ");
        }
        System.out.println();

        int[] metszet = halmaz1dintomb.metszet(halmaz2dintomb).elemei();
        System.out.println("A két halmaz metszete: ");
        for (int i = 0; i < metszet.length; i++) {
            System.out.print(metszet[i] + " ");
        }
        System.out.println();

        int[] kulonbseg = halmaz1dintomb.kivon(halmaz2dintomb).elemei();
        System.out.println("A két halmaz különbsége: ");
        for (int i = 0; i < kulonbseg.length; i++) {
            System.out.print(kulonbseg[i] + " ");
        }

        halmaz1dintomb.elemKivetele(1);

        halmaz1dintomb.urites();
        if (halmaz1dintomb.uresE())
            System.out.println("A halmaz üres.");
    }
}

class dinHalmazStatikusTombbel {
    private int[] taroltErtek;

    public dinHalmazStatikusTombbel() {
        taroltErtek = new int[0];
    }

    public boolean elemHozzaadasa(int elem) {
        if (tartalmazzaE(elem))
            return false;
        else {
            int[] ujTaroltErtek = new int[this.taroltErtek.length + 1];
            for (int i = 0; i < this.taroltErtek.length; i++) {
                ujTaroltErtek[i] = this.taroltErtek[i];
            }
            ujTaroltErtek[this.taroltErtek.length] = elem;
            this.taroltErtek = ujTaroltErtek;
            return true;
        }
    }

    public boolean elemKivetele(int elem) {
        if (!tartalmazzaE(elem))
            return false;
        else {
            dinHalmazStatikusTombbel ujTaroltErtek = new dinHalmazStatikusTombbel();
            for (int i = 0; i < this.taroltErtek.length; i++) {
                if (this.taroltErtek[i] != elem)
                    ujTaroltErtek.elemHozzaadasa(this.taroltErtek[i]);
            }
            this.taroltErtek = ujTaroltErtek.taroltErtek;
            return true;
        }
    }

    public int szamossag() {
        return this.taroltErtek.length;
    }

    public dinHalmazStatikusTombbel kivonas(dinHalmazStatikusTombbel mit) {
        dinHalmazStatikusTombbel ujTaroltErtek = new dinHalmazStatikusTombbel();
        int index = 0;
        while (index < this.taroltErtek.length) {
            if (!mit.tartalmazzaE(this.taroltErtek[index]))
                ujTaroltErtek.elemHozzaadasa(this.taroltErtek[index]);
            index++;
        }
        return ujTaroltErtek;
    }

    public dinHalmazStatikusTombbel egyesites(dinHalmazStatikusTombbel mivel) {
        dinHalmazStatikusTombbel ujTaroltErtek = new dinHalmazStatikusTombbel();
        for (int i = 0; i < this.taroltErtek.length; i++) {
            ujTaroltErtek.elemHozzaadasa(this.taroltErtek[i]);
        }
        for (int i = 0; i < mivel.taroltErtek.length; i++) {
            ujTaroltErtek.elemHozzaadasa(mivel.taroltErtek[i]);
        }
        return ujTaroltErtek;
    }

    public dinHalmazStatikusTombbel metszet(dinHalmazStatikusTombbel mivel) {
        dinHalmazStatikusTombbel ujTarolErtek = new dinHalmazStatikusTombbel();
        for (int i = 0; i < this.taroltErtek.length; i++) {
            if (mivel.tartalmazzaE(this.taroltErtek[i]))
                ujTarolErtek.elemHozzaadasa(this.taroltErtek[i]);
        }
        return ujTarolErtek;
    }

    public void urites() {
        this.taroltErtek = new int[0];
    }

    public boolean tartalmazzaE(int elem) {
        boolean tartalmazza = false;
        for (int i = 0; i < this.taroltErtek.length; i++) {
            if (this.taroltErtek[i] == elem)
                tartalmazza = true;
        }
        return tartalmazza;
    }

    public boolean uresE() {
        if (this.taroltErtek.length == 0)
            return true;
        else return false;
    }

    public void halmazKiirasa() {
        for (int i = 0; i < this.taroltErtek.length; i++) {
            System.out.print(this.taroltErtek[i] + " ");
        }
        System.out.println();
    }

    public int adottPozicioErteke(int pozicio) {
        return this.taroltErtek[pozicio];
    }
}

class dinHalmazDinamikusTombbel {
    private dinHalmazStatikusTombbel taroltErtek;

    public dinHalmazDinamikusTombbel() {
        taroltErtek = new dinHalmazStatikusTombbel();
    }

    public boolean tartalmazzaE(int elem) {
        for (int i = 0; i < this.taroltErtek.szamossag(); i++) {
            if (this.taroltErtek.tartalmazzaE(elem))
                return true;
        }
        return false;
    }

    public int szamossag() {
        return this.taroltErtek.szamossag();
    }

    public boolean elemHozzaadas(int elem) {
        if (this.taroltErtek.tartalmazzaE(elem))
            return false;
        else {
            this.taroltErtek.elemHozzaadasa(elem);
            return true;
        }
    }

    public boolean elemKivetele(int elem) {
        if (!this.taroltErtek.tartalmazzaE(elem))
            return false;
        else {
            this.taroltErtek.elemKivetele(elem);
            return true;
        }
    }

    public dinHalmazDinamikusTombbel egyesites(dinHalmazDinamikusTombbel halmaz) {
        dinHalmazDinamikusTombbel eredmeny = new dinHalmazDinamikusTombbel();
        for (int i = 0; i < this.taroltErtek.szamossag(); i++) {
            eredmeny.elemHozzaadas(this.taroltErtek.adottPozicioErteke(i));
        }
        for (int i = 0; i < halmaz.szamossag(); i++) {
            eredmeny.elemHozzaadas(halmaz.taroltErtek.adottPozicioErteke(i));
        }
        return eredmeny;
    }

    public dinHalmazDinamikusTombbel metszet(dinHalmazDinamikusTombbel halmaz) {
        dinHalmazDinamikusTombbel eredmeny = new dinHalmazDinamikusTombbel();
        for (int i = 0; i < this.taroltErtek.szamossag(); i++) {
            if (halmaz.tartalmazzaE(this.taroltErtek.adottPozicioErteke(i)))
                eredmeny.elemHozzaadas(this.taroltErtek.adottPozicioErteke(i));
        }
        return eredmeny;
    }

    public dinHalmazDinamikusTombbel kivon(dinHalmazDinamikusTombbel halmaz) {
        dinHalmazDinamikusTombbel eredmeny = new dinHalmazDinamikusTombbel();
        for (int i = 0; i < this.taroltErtek.szamossag(); i++) {
            if (!halmaz.tartalmazzaE(this.taroltErtek.adottPozicioErteke(i)))
                eredmeny.taroltErtek.elemHozzaadasa(this.taroltErtek.adottPozicioErteke(i));
        }
        return eredmeny;
    }

    public void urites() {
        this.taroltErtek.urites();
    }

    public boolean uresE() {
        return this.taroltErtek.uresE();
    }

    public int[] elemei() {
        int[] elemek = new int[this.taroltErtek.szamossag()];
        for (int i = 0; i < this.taroltErtek.szamossag(); i++) {
            elemek[i] = this.taroltErtek.adottPozicioErteke(i);
        }
        return elemek;
    }
}