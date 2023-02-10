/*
Egészítse ki az egyszeresen láncolt lista adattípust egy olyan függvénnyel, ami visszaadja az adott
lista megfordítottját.
a. Oly módon, hogy egy teljesen új lista jön létre, ami az eredeti listában szereplő értékeket
forított sorrendben tartalamzza.
b. Oly módon, hogy az aktuális listát huzalozza úgy át, hogy önmaga megfordítottja legyen.
*/

//MÉG NINCS KÉSZ!

public class Main {
    public static void main(String[] args) {
        lancoltLista lista = new lancoltLista();
        lista.beszurasListaElejere(3); // 3
        lista.beszurasListaElejere(2); // 2 3
        lista.beszurasListaElejere(1); // 1 2 3
        lista.beszurasListaVegere(4); // 1 2 3 4
        lista.beszurasListaVegere(5); // 1 2 3 4 5
        lista.beszurasAdottPoziciora(3, 0); // 1 2 3 0 4 5

        //lista.torlesElsoElem(); // 2 3 0 4 5
        //lista.torlesUtolsoElem(); // 2 3 0 4
        //lista.torlesAdottPoziciorol(1); //2 0 4

        lista = lista.megforditasUjListaval();
        lista.megforditasUjListaNelkul();

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
    private lancszem utolso;

    public lancoltLista() {
        this.elso = null;
        this.utolso = null;
    }

    public void beszurasListaElejere(int adat) {
        lancszem ujLancszem = new lancszem();
        ujLancszem.setAdat(adat);
        if (this.hossz() == 0) {
            this.elso = ujLancszem;
            this.utolso = ujLancszem;
        } else {
            ujLancszem.setKovetkezo(this.elso);
            this.elso = ujLancszem;
        }
    }

    public void beszurasListaVegere(int adat) {
        lancszem ujLancszem = new lancszem();
        ujLancszem.setAdat(adat);
        if (this.hossz() == 0) {
            this.elso = ujLancszem;
            this.utolso = ujLancszem;
        } else if (this.hossz() == 1) {
            this.utolso = ujLancszem;
            this.elso.setKovetkezo(this.utolso);
        } else {
            this.utolso.setKovetkezo(ujLancszem);
            this.utolso = ujLancszem;
        }
    }

    public void beszurasAdottPoziciora(int pozicio, int adat) {
        if (this.elso == null)
            beszurasListaElejere(adat);
        else if (pozicio == this.hossz() - 1) {
            beszurasListaVegere(adat);
        } else {
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
        if (this.hossz() == 0)
            return;
        else if (this.hossz() == 1) {
            this.elso = null;
            this.utolso = null;
        } else
            this.elso = this.elso.getKovetkezo();
    }

    public void torlesUtolsoElem() {
        if (hossz() == 0)
            return;
        else if (hossz() == 1) {
            this.elso = null;
            this.utolso = null;
        } else {
            lancszem aktualis = this.elso;
            for (int i = 0; i < hossz() - 2; i++) {
                aktualis = aktualis.getKovetkezo();
            }
            this.utolso = aktualis;
            this.utolso.setKovetkezo(null);
        }
    }

    public void torlesAdottPoziciorol(int pozicio) {
        if (hossz() == 0)
            return;
        else if (hossz() == 1) {
            this.elso = null;
            this.utolso = null;
        } else if (pozicio == hossz() - 1) {
            torlesUtolsoElem();
        } else {
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
        this.utolso.setKovetkezo(masikLista.elso);
    }

    public boolean uresE() {
        return this.hossz() == 0;
    }

    public lancoltLista megforditasUjListaval() {
        lancoltLista ujLista = new lancoltLista();
        ujLista.elso = this.utolso;
        lancszem aktualisUjListaban = ujLista.elso;
        int thisHossza = this.hossz();
        for (int i = 1; i < thisHossza - 1; i++) {
            lancszem aktualis = this.elso;
            for (int j = i; j < thisHossza - 1; j++) {
                aktualis = aktualis.getKovetkezo();
            }
            aktualisUjListaban.setKovetkezo(aktualis);
            aktualisUjListaban = aktualisUjListaban.getKovetkezo();
        }
        aktualisUjListaban.setKovetkezo(this.elso);
        return ujLista;
    }

    public void megforditasUjListaNelkul() {
        lancszem elsoBackup = this.elso;
        this.elso = this.utolso;
        lancszem aktualis = this.utolso;
        lancszem aktualisElotti = this.elso;
        int lancHossza = this.hossz();
        for (int i = 1; i < lancHossza - 1; i++) {
            for (int j = i; j < lancHossza - 1; j++) {
                aktualis = aktualis.getKovetkezo();
            }
            aktualis.setKovetkezo(aktualisElotti);
            aktualis = aktualisElotti;
        }
        aktualis.setKovetkezo(elsoBackup);
        this.utolso = elsoBackup;
    }
}
