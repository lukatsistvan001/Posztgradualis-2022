package com.company;

public class Main {

    public static void main(String[] args) {

        DuplanLancoltLista dll = new DuplanLancoltLista();

        dll.VegereBeszur("Utolsó");
        dll.ElejereBeszur("Második");
        dll.ElejereBeszur("ElsŐ");

        dll.AdottPoziciorolTorol(1);
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

    }

    public void VegereBeszur(String adat) {

    }

    public void AdottPoziciorolTorol(int pos) {


        ListaElem pozicioElotti = elso;
        for (int i = 0; i < pos - 1; i++) {
            pozicioElotti = pozicioElotti.getKovetkezo();
        }


    }
}

class ListaElem {
    private String adat;
    private ListaElem elozo;
    private ListaElem kovetkezo;

    public ListaElem(String adat) {
        adat = adat;
    }

    public void setElozo(ListaElem ujElozo) {
        elozo = ujElozo;
    }

    public ListaElem getElozo() {
        return elozo;
    }

    public void setKovetkezo(ListaElem ujKovetkezo) {
        kovetkezo = ujKovetkezo;
    }

    public ListaElem getKovetkezo() {
        return kovetkezo;
    }
}