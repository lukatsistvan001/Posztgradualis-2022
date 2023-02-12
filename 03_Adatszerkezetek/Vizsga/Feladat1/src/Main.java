public class Main {

    public static void main(String[] args) {

        DuplanLancoltLista dll = new DuplanLancoltLista();

        dll.VegereBeszur("Utolsó");
        dll.ElejereBeszur("Második");
        dll.ElejereBeszur("ElsŐ");

        dll.AdottPoziciorolTorol(2);
    }
}

class DuplanLancoltLista {
    private ListaElem elso;
    private ListaElem utolso;

    public DuplanLancoltLista() {
        elso = null;
        utolso = null;
    }

    public void ElejereBeszur(String adat) {
        ListaElem ujListaElem = new ListaElem(adat);
        ujListaElem.setKovetkezo(this.elso);
        if (UresE()) {
            this.elso = ujListaElem;
            this.utolso = ujListaElem;
        } else {
            this.elso.setElozo(ujListaElem);
            this.elso = ujListaElem;
        }
    }

    public void VegereBeszur(String adat) {
        ListaElem ujListaElem = new ListaElem(adat);
        ujListaElem.setElozo(this.utolso);
        if (UresE()) {
            this.elso = ujListaElem;
            this.utolso = ujListaElem;
        } else {
            this.utolso.setKovetkezo(ujListaElem);
            this.utolso = ujListaElem;
        }
    }

    public boolean UresE() {
        return (elso == null && utolso == null);
    }

    public void AdottPoziciorolTorol(int pos) {
        if (UresE())
            return;

        ListaElem torlendo = elso;
        for (int i = 0; i < pos; i++) {
            torlendo = torlendo.getKovetkezo();
        }
        if (torlendo.getElozo() != null && torlendo.getKovetkezo() != null) {
            // több elem a listában
            torlendo.getElozo().setKovetkezo(torlendo.getKovetkezo());
            torlendo.getKovetkezo().setElozo(torlendo.getElozo());
        } else if (torlendo.getElozo() == null && torlendo.getKovetkezo() == null) {
            // 1 elem a listában
            this.elso = null;
            this.utolso = null;
        } else if (torlendo.getElozo() == null) {
            // első elem törlése
            elso.getKovetkezo().setElozo(null);
            elso = elso.getKovetkezo();
        } else {
            // utolsó elem törlése
            utolso.getElozo().setKovetkezo(null);
            utolso = utolso.getElozo();
        }
    }
}


class ListaElem {
    private String adat;
    private ListaElem elozo;
    private ListaElem kovetkezo;

    public ListaElem(String adat) {
        this.adat = adat;
    }

    public void setElozo(ListaElem ujElozo) {
        this.elozo = ujElozo;
    }

    public ListaElem getElozo() {
        return this.elozo;
    }

    public void setKovetkezo(ListaElem ujKovetkezo) {
        this.kovetkezo = ujKovetkezo;
    }

    public ListaElem getKovetkezo() {
        return this.kovetkezo;
    }
}