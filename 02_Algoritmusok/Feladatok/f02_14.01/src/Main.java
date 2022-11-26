/*
Írjon rekurzív algotmut, ami egy tört számot megszoroz egy egész számmal, kizárólag összeadás
művelet használatával.
*/

public class Main {
    public static void main(String[] args) {
        double eredmeny = szorzas(-0.5, -5);
        System.out.println(eredmeny);
    }

    private static double szorzas(double szorzando, int szorzo) {
        if (szorzo<0){
            szorzo = -szorzo;
            szorzando = -szorzando;
        }
        return szorzasNemNegativSzammal(szorzando, szorzo);
    }

    private static double szorzasNemNegativSzammal(double szorzando, int szorzo) {
        if (szorzo == 0)
            return 0;
        return szorzas(szorzando, szorzo - 1) + szorzando;
    }
}