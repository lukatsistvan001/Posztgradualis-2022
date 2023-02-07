/*
1. Implementálja az egyszeresen láncolt lista adattípust (számokra) a következő műveletekkel
a. adat beszúrása a lista elejére
b. adat beszúrása a lista végére
c. adat beszúrása adott pozícióra
d. adat törlése adott pozícióból
e. adat keresése
f. adat törlése
g. lista hosszának lekérdezése
h. egy lista hozzáfűzése az aktuális lista végéhez
i. adott listaelem elkérése (nem csak adat, hanem egész elem)
*/

public class Main {
    public static void main(String[] args) {
        lancoltLista lista = new lancoltLista();
        lista.beszurasListaElejere(3); // 3
        lista.beszurasListaElejere(2); // 2 3
        lista.beszurasListaElejere(1); // 1 2 3
        System.out.println("A lista hossza: " + lista.hossz()); // 3
        lista.beszurasAdottPoziciora(1, 0); // 1 0 2 3
        lista.beszurasAdottPoziciora(2, 5); // 1 0 5 2 3
        lista.beszurasListaVegere(10); // 1 0 5 2 3 10
        lista.torlesElsoElem(); // 0 5 2 3 10
        lista.torlesAdottPoziciorol(2); // 0 5 3 10
        System.out.println("A 3-as poziciója: " + lista.kereses(3)); // 2
        lista.torlesAdat(10); // 0 5 3

        lancoltLista masikLista = new lancoltLista();
        masikLista.beszurasListaElejere(9);
        masikLista.beszurasListaElejere(8);
        masikLista.beszurasListaElejere(7);

        lista.listaHozzafuzese(masikLista);
    }
}

class lancszem {
    private lancszem kovetkezo;
    private int adat;

    public void setKovetkezo(lancszem kovetkezo) {
        this.kovetkezo = kovetkezo;
    }

    public lancszem getKovetkezo() {
        return this.kovetkezo;
    }

    public void setAdat(int adat) {
        this.adat = adat;
    }

    public int getAdat() {
        return this.adat;
    }
}

class lancoltLista {
    private lancszem elso;

    public lancoltLista() {
        this.elso = null;
    }

    public void beszurasListaElejere(int adat) {
        lancszem ujLancszem = new lancszem();
        ujLancszem.setAdat(adat);
        ujLancszem.setKovetkezo(this.elso);
        this.elso = ujLancszem;
    }

    public void beszurasListaVegere(int adat) {
        lancszem ujLancszem = new lancszem();
        ujLancszem.setAdat(adat);
        beszurasAdottPoziciora(hossz(), adat);
    }

    public void beszurasAdottPoziciora(int pozicio, int adat) {
        if (this.elso == null)
            beszurasListaElejere(adat);
        else {
            lancszem ujLancszem = new lancszem();
            ujLancszem.setAdat(adat);
            lancszem beszurandoElotti = this.elso;
            for (int i = 0; i < pozicio - 1; i++) {
                beszurandoElotti = beszurandoElotti.getKovetkezo();
            }
            ujLancszem.setKovetkezo(beszurandoElotti.getKovetkezo());
            beszurandoElotti.setKovetkezo(ujLancszem);
        }
    }

    public void torlesElsoElem() {
        lancszem ujLancszem = this.elso.getKovetkezo();
        this.elso = ujLancszem;
    }

    public void torlesAdottPoziciorol(int pozicio) {
        if (this.elso.getKovetkezo() == null)
            torlesElsoElem();
        else {
            lancszem torlesElotti = this.elso;
            for (int i = 0; i < pozicio - 1; i++) {
                torlesElotti = torlesElotti.getKovetkezo();
            }
            torlesElotti.setKovetkezo(torlesElotti.getKovetkezo().getKovetkezo());
        }
    }

    public int kereses(int adat) {
        int sorszam = 0;
        lancszem aktualis = this.elso;
        for (int i = 0; i < hossz(); i++) {
            if (aktualis.getAdat() == adat)
                return sorszam;
            sorszam++;
            aktualis = aktualis.getKovetkezo();
        }
        return -1;
    }

    public void torlesAdat(int adat) {
        int pozicio = kereses(adat);
        if (pozicio != -1)
            torlesAdottPoziciorol(pozicio);
    }

    public int hossz() {
        lancszem ujLancszem = this.elso;
        int hossz = 0;
        while (ujLancszem != null) {
            hossz++;
            ujLancszem = ujLancszem.getKovetkezo();
        }
        return hossz;
    }

    public void listaHozzafuzese(lancoltLista masikLista) {
        lancoltLista ujLancoltLista = new lancoltLista();
        ujLancoltLista.elso = this.elso;
        lancszem aktualis = this.elso;
        for (int i = 0; i < this.hossz() - 1; i++) {
            aktualis = aktualis.getKovetkezo();
        }
        aktualis.setKovetkezo(masikLista.elso);
    }
}