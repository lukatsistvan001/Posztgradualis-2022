/*
Egy alap forgóvillának két állapota van (nyitva és zárva), illetve két alap eseménye (pénz
bedobás és áthaladási kísérlet). Az ilyen forgóvilla nem képes modellezni azt a helyzetet, amikor
valaki elkezd áthaladni a forgóvillán, de meggondolja magát és visszafordul. Készítsen olyan
forgóvilla modellt, ami kezelni tudja ezt az esetet is. Határozza meg a kibővített állapotok és
események halmazát, és implementálja az új állapot kiszámoló függvényt ezeknek megfelelően.
*/

enum ForgovillaAllapot {
    Nyitva,
    Zarva
}

enum ForgovillaEsemeny {
    AthaladasiKiserlet,
    PenzBedobas,
    Belerugas
}

public class Main {
    public static void main(String[] args) {

        ForgovillaAllapot eszakiKapuAllapota = ForgovillaAllapot.Nyitva;

        // valami tortenik
        eszakiKapuAllapota = ForgovillaKovetkezoAllapota(eszakiKapuAllapota,
                ForgovillaEsemeny.Belerugas);

        eszakiKapuAllapota = ForgovillaKovetkezoAllapota(eszakiKapuAllapota,
                ForgovillaEsemeny.AthaladasiKiserlet);

        eszakiKapuAllapota = ForgovillaKovetkezoAllapota(eszakiKapuAllapota,
                ForgovillaEsemeny.AthaladasiKiserlet);

        eszakiKapuAllapota = ForgovillaKovetkezoAllapota(eszakiKapuAllapota,
                ForgovillaEsemeny.PenzBedobas);

    }

    private static ForgovillaAllapot ForgovillaKovetkezoAllapota(
            ForgovillaAllapot aktualisAllapot,
            ForgovillaEsemeny aktualisEsemeny
    ) {
        switch (aktualisAllapot) {
            case Nyitva:
                switch (aktualisEsemeny) {
                    case AthaladasiKiserlet:
                        return ForgovillaAllapot.Zarva;
                    case PenzBedobas:
                        return ForgovillaAllapot.Nyitva;
                    case Belerugas:
                        return ForgovillaAllapot.Nyitva;
                    default:
                        throw new IllegalArgumentException();
                }
            case Zarva:
                switch (aktualisEsemeny) {
                    case AthaladasiKiserlet:
                        return ForgovillaAllapot.Zarva;
                    case PenzBedobas:
                        return ForgovillaAllapot.Nyitva;
                    case Belerugas:
                        return ForgovillaAllapot.Zarva;
                    default:
                        throw new IllegalArgumentException();
                }
            default:
                throw new IllegalArgumentException();
        }
    }
}