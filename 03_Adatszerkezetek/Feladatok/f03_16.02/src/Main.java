public class Main {
    public static void main(String[] args) {
        dinHalmaz halmaz1 = new dinHalmaz();
        dinHalmaz halmaz2 = new dinHalmaz();
        dinHalmaz halmaz3 = new dinHalmaz();

        halmaz1.elemHozzaadas("1");
        halmaz1.elemHozzaadas("2");
        halmaz1.elemHozzaadas("3");
        System.out.println("A halmaz1 tartalma: ");
        halmaz1.elemekKiirasa();

        System.out.println("A halmaz1 számossága: " + halmaz1.szamossaga());
        System.out.println("A halmaz1-ben benne van-e a 2? - " + halmaz1.tartalmazzaE("2"));
        System.out.println("A halmaz1-ből a valamikor-t sikerült-e kivenni? - " + halmaz1.elemKivetele("2"));
        System.out.println("A halmaz1 tartalmazza-e a 2-t? - " + halmaz1.tartalmazzaE("2"));
        System.out.println("A halmaz1 számossága: " + halmaz1.szamossaga());

        halmaz2.elemHozzaadas("1");
        halmaz2.elemHozzaadas("4");
        halmaz2.elemHozzaadas("5");
        System.out.println("A halmaz2 tartalma: ");
        halmaz2.elemekKiirasa();

        halmaz3 = halmaz2.halmazKivonasa(halmaz1);
        System.out.println("A halmaz3 számossága: " + halmaz3.szamossaga());
        System.out.println("A halmaz3 tartalma: ");
        halmaz3.elemekKiirasa();

        halmaz3.egyesites(halmaz1);
        System.out.println("A halmaz3 számossága: " + halmaz3.szamossaga());
        System.out.println("A halmaz3 tartalma: ");
        halmaz3.elemekKiirasa();

        halmaz3.metszet(halmaz2);
        System.out.println(halmaz3.szamossaga());

        System.out.println(halmaz3.uresE());
        halmaz3.urites();
        System.out.println(halmaz3.uresE());


    }
}

class dinHalmaz {
    //tagvaltozok
    private String[] taroltHalmaz;

    //konstruktor
    public dinHalmaz() {
        taroltHalmaz = new String[0];
    }

    public void elemekKiirasa() {
        for (int i = 0; i < this.taroltHalmaz.length; i++) {
            System.out.println(this.taroltHalmaz[i]);
        }
    }

    public boolean elemHozzaadas(String elem) {
        if (tartalmazzaE(elem))
            return false;
        String[] ujTaroltHalmaz = new String[taroltHalmaz.length + 1];
        for (int i = 0; i < taroltHalmaz.length; i++) {
            ujTaroltHalmaz[i] = taroltHalmaz[i];
        }
        ujTaroltHalmaz[ujTaroltHalmaz.length - 1] = elem;
        taroltHalmaz = ujTaroltHalmaz;
        return true;
    }

    public boolean elemKivetele(String elem) {
        if (!tartalmazzaE(elem))
            return false;
        String[] ujTaroltHalmaz = new String[taroltHalmaz.length - 1];
        boolean megvan = false;
        for (int i = 0; i < taroltHalmaz.length; i++) {
            if (taroltHalmaz[i].equals(elem)) {
                megvan = true;
                continue;
            }
            if (megvan)
                ujTaroltHalmaz[i - 1] = taroltHalmaz[i];
            else ujTaroltHalmaz[i] = taroltHalmaz[i];
        }
        taroltHalmaz = ujTaroltHalmaz;
        return true;
    }

    public int szamossaga() {
        return taroltHalmaz.length;
    }

    public dinHalmaz halmazKivonasa(dinHalmaz kivonandoHalmaz) {
        dinHalmaz ujhalmaz = new dinHalmaz();
        for (int i = 0; i < this.taroltHalmaz.length; i++) {
            ujhalmaz.elemHozzaadas(this.taroltHalmaz[i]);
        }
        for (int i = 0; i < kivonandoHalmaz.taroltHalmaz.length; i++) {
            ujhalmaz.elemKivetele(kivonandoHalmaz.taroltHalmaz[i]);
        }
        this.taroltHalmaz = ujhalmaz.taroltHalmaz;
        return ujhalmaz;
    }

    public dinHalmaz egyesites(dinHalmaz masikHalmaz) {
        dinHalmaz ujhalmaz = new dinHalmaz();
        for (int i = 0; i < this.taroltHalmaz.length; i++) {
            ujhalmaz.elemHozzaadas(this.taroltHalmaz[i]);
        }
        for (int i = 0; i < masikHalmaz.taroltHalmaz.length; i++) {
            ujhalmaz.elemHozzaadas(masikHalmaz.taroltHalmaz[i]);
        }
        this.taroltHalmaz = ujhalmaz.taroltHalmaz;
        return ujhalmaz;
    }

    public dinHalmaz metszet(dinHalmaz masikHalmaz) {
        dinHalmaz ujhalmaz = new dinHalmaz();
        if (this.taroltHalmaz.length < masikHalmaz.taroltHalmaz.length)
            for (int i = 0; i < this.taroltHalmaz.length; i++) {
                if (masikHalmaz.tartalmazzaE(this.taroltHalmaz[i]))
                    ujhalmaz.elemHozzaadas(this.taroltHalmaz[i]);
            }
        else
            for (int i = 0; i < masikHalmaz.taroltHalmaz.length; i++) {
                if (this.tartalmazzaE(masikHalmaz.taroltHalmaz[i]))
                    ujhalmaz.elemHozzaadas(masikHalmaz.taroltHalmaz[i]);
            }
        this.taroltHalmaz = ujhalmaz.taroltHalmaz;
        return ujhalmaz;
    }

    public void urites() {
        String[] ujhalmaz = new String[0];
        this.taroltHalmaz = ujhalmaz;
    }

    public boolean uresE() {
        return szamossaga() == 0;
    }

    public boolean tartalmazzaE(String karakterlanc) {
        for (int i = 0; i < this.taroltHalmaz.length; i++) {
            if (this.taroltHalmaz[i].equals(karakterlanc))
                return true;
        }
        return false;
    }
}