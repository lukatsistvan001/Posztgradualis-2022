package com.company;

public class Main {

    public static void main(String[] args) {

        SzimplanLancoltLista szll = new SzimplanLancoltLista();

        szll.VegereBeszur("Utolsó");
        szll.ElejereBeszur("Második");
        szll.ElejereBeszur("Első");

        szll.AdottPoziciorolTorol(1);
    }
}

class SzimplanLancoltLista {
    private ListaElem elso;

    public SzimplanLancoltLista() {
        elso = null;
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
    private ListaElem kovetkezo;

    public ListaElem(String adat) {
        adat = adat;
    }

    public void setKovetkezo(ListaElem ujKovetkezo) {
        kovetkezo = ujKovetkezo;
    }

    public ListaElem getKovetkezo() {
        return kovetkezo;
    }
}