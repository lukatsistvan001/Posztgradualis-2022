public class Main {
    public static void main(String[] args) {
        B b = new B("Hi");
    }
}

class A {
    A(String a) {
        System.out.println("Az A osztály konstruktora " + a);
    }
}
class B extends A {
    B(String b) {
        super(b);
        System.out.println("A B osztály konstruktora " + b);
    }
}