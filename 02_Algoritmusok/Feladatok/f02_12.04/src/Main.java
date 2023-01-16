enum allapotok {
    olvasas,
    iras,
    gyujtes
}

// Ezt érdemes lenne elkomplikálni?
enum esemenyek {
    szunet,
    idezojel,
    mas
}

public class Main {
    private static String karakterfolyam = "\"Az alma elment aludni\" mert \"almos volt.\"";
    private static String karakterlanc = "";

    public static void main(String[] args) {
        allapotok allapot;
        switch (karakterfolyam.charAt(0)) {
            case '"':
                allapot = allapotok.gyujtes;
                break;
            default:
                allapot = allapotok.olvasas;
        }
        for (int i = 0; i < karakterfolyam.length(); i++) {
            allapot = feldolgozas(allapot, karakterfolyam.charAt(i));
        }
        System.out.println('"' + karakterlanc + '"');
    }

    private static allapotok feldolgozas(allapotok aktualisAllapot, char karakter) {
        switch (aktualisAllapot) {
            case olvasas:
                switch (karakter) {
                    case ' ':
                        return allapotok.iras;
                    default:
                        if (karakter != ' ')
                            karakterlanc = karakterlanc + karakter;
                        return allapotok.olvasas;
                }
            case iras:
                switch (karakter) {
                    case '"':
                        System.out.println('"' + karakterlanc + '"');
                        karakterlanc = "";
                        return allapotok.gyujtes;
                    default:
                        System.out.println('"' + karakterlanc + '"');
                        karakterlanc = "";
                        if (karakter != ' ')
                            karakterlanc = karakterlanc + karakter;
                        return allapotok.olvasas;
                }
            case gyujtes:
                switch (karakter) {
                    case '"':
                        if (karakterlanc.length() == 0)
                            return allapotok.gyujtes;
                        else
                            return allapotok.iras;
                    default:
                        karakterlanc = karakterlanc + karakter;
                        return allapotok.gyujtes;
                }
            default:
                throw new IllegalArgumentException();
        }
    }
}