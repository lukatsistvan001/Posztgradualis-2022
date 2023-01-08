public class Main {
    public static void main(String[] args) {
        dinHalmaz halmaz1 = new dinHalmaz();
        dinHalmaz halmaz2 = new dinHalmaz();
        dinHalmaz halmaz3 = new dinHalmaz();

        halmaz1.elemHozzaadas("valaki");
        halmaz1.elemHozzaadas("valamikor");
        halmaz1.elemHozzaadas("valaminek");
        System.out.print("A halmaz1 tartalma: ");
        halmaz1.elemekKiirasa();

        System.out.println();
        System.out.println("A halmaz1 számossága: " + halmaz1.szamossaga());
        System.out.println("A halmaz1-ben benne van-e a valamikor? - " + halmaz1.tartalmazzaE("valamikor"));
        System.out.println("A halmaz1-ből a valamikor-t sikerült-e kivenni? - " + halmaz1.elemKivetele("valamikor"));
        System.out.println("A halmaz1 tartalmazza-e a valamikor-t? - " + halmaz1.tartalmazzaE("valamikor"));
        System.out.println("A halmaz1 számossága: " + halmaz1.szamossaga());

        halmaz2.elemHozzaadas("valamiért");
        halmaz2.elemHozzaadas("valakinek");
        halmaz2.elemHozzaadas("valaki");
        System.out.print("A halmaz2 tartalma: ");
        halmaz2.elemekKiirasa();

        halmaz3 = halmaz1.halmazKivonasa(halmaz2);
        System.out.println(halmaz3.szamossaga());

        halmaz3.egyesites(halmaz1);
        System.out.println(halmaz3.szamossaga());

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

    public dinHalmaz() {
        taroltHalmaz = new String[0];
    }

    public String[] elemekKiirasa() {
        String[] ujTarolthalmaz = new String[this.taroltHalmaz.length];
        for (int i = 0; i < taroltHalmaz.length; i++) {
            ujTarolthalmaz[i] = this.taroltHalmaz[i];
        }
        return ujTarolthalmaz;
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
        if (tartalmazzaE(elem))
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
            ujhalmaz.elemKivetele(ujhalmaz.taroltHalmaz[i]);
        }
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
        return ujhalmaz;
    }

    public dinHalmaz metszet(dinHalmaz masikHalmaz) {
        dinHalmaz ujhalmaz = new dinHalmaz();
        for (int i = 0; i < ; i++) {
            
        }
        return ujhalmaz;
    }

    public void urites() {

    }

    public boolean uresE() {
        return szamossaga() == 0;
    }

    public boolean tartalmazzaE(String karakterlanc) {
        for (int i = 0; i < taroltHalmaz.length; i++) {
            if (taroltHalmaz[i].equals(karakterlanc))
                return true;
        }
        return false;
    }
}