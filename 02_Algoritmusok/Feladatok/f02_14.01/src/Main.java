/*
Írjon rekurzív algotmut, ami egy tört számot megszoroz egy egész számmal, kizárólag összeadás
művelet használatával.
*/

public class Main {
    public static void main(String[] args) {
        double eredmeny = szorzas(0.5, 5);
    }

    private static double szorzas(double szorzando, int szorzo) {
        if (szorzo == 1)
            return szorzando;
        else
            return szorzas(szorzando, szorzo - 1) + szorzando;
    }
}