import javax.swing.*;

public class Main {

    // Készítsünk egy számlálót, amelyet egy végrehajtási szál vezérel, másodpercenként
    //változtatva értékét. Oldjuk meg, hogy a szál futása leállítható, újraindítható,
    //felfüggeszthető, folytatható legyene, anélkül, hogy használnánk a Thread osztály
    //vonatkozó érvénytelenített (deprecated) metódusait. Készítsünk grafikus felületet,
    //amelyen megjelenítjük a számláló aktuális értékét és négy gomb segítségével
    //vezérelni tudjuk annak futását. Oldjuk meg, hogy ne legyen szükséges kétirányú
    //függőséget bevezetni a számláló és a grafikus felület között. Erre eseménykezelésen
    //alapuló megoldást implementálhatunk (saját eseménytípus, figyelő stb.).
    public static void main(String[] args) {
        CounterFrame counterFrame = new CounterFrame();
        counterFrame.setBounds(10,10,800,600);
        counterFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}