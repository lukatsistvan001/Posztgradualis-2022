/*
Készítsünk egy osztályhierarchiát mértani alakzatok reprezentációjára. Az
alakzatok közös tulajdonságaikat (pl. pozíció, szín stb.) egy absztrakt
alaposztálytól (Shape) örökölhetik, ugyanez az osztály határozhatja meg közös
metódusaikat (pl. kerület és terület kiszámítása absztrakt metódusok stb.).
Hozzunk létre legalább két származtatott osztályt konkrét alakzatok
reprezentációjára (pl. Circle, Square, Rectangle stb.), amelyekben megfelelő
módon implementáljuk az előzőleg említett metódusokat. Hozzunk létre egy
Resizable interfészt, amelynek resize metódusa egy Dimension objektumot kap
paraméterként: az újraméretezés az alakzat köré írt téglalap méretei alapján
történne. Az interfész megvalósítása által tegyük újraméretezhetővé legalább egy
alakzatunkat. Írjunk egy programot, amelyben szemléltetjük az osztályok
működését.
*/

package Feladat5;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Circle circle = new Circle(1, 1, Color.RED, 10);
        System.out.println("The circle area: " + circle.calculateArea());
        System.out.println("The circle perimeter: " + circle.calculatePerimeter());
        circle.frameSize();
        circle.resize(new Dimension(25, 30));
        circle.frameSize();

        System.out.println();

        Square square = new Square(1, 1, Color.BLUE, 10);
        System.out.println("The square area: " + square.calculateArea());
        System.out.println("The square perimeter: " + square.calculatePerimeter());
        square.frameSize();
        square.resize(new Dimension(25, 30));
        square.frameSize();

        System.out.println();

        Rectangle rectangle = new Rectangle(1, 1, Color.BLUE, 10, 15);
        System.out.println("The rectangle area: " + rectangle.calculateArea());
        System.out.println("The rectangle perimeter: " + rectangle.calculatePerimeter());
        rectangle.frameSize();
        rectangle.resize(new Dimension(25, 30));
        rectangle.frameSize();
    }
}