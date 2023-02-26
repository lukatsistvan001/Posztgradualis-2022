/*
Írjunk programot, amely kiírja a konzolra a parancssor argumentumait, a
kisbetűket nagybetűkbe, a nagybetűket kisbetűkbe alakítva. Útmutatás: egy String
objektum esetében egy adott karaktert a charAt(index) metódus segítségével
kérdezhetünk le, az ellenőrzés és átalakítás a Character osztály statikus
metódusainak segítségével történhet
*/

package Feladat2;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            for (int j = 0; j < args[i].length(); j++) {
                if (Character.isLowerCase(args[i].charAt(j))) {
                    System.out.print(Character.toUpperCase(args[i].charAt(j)));
                } else {
                    System.out.print(Character.toLowerCase(args[i].charAt(j)));
                }
            }
            System.out.print(" ");
        }
    }
}