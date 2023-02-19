import collection.StudentIterator;
import collection.StudentList;
import core.Person;
import core.Student;

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student("Laci", 25, "Informatika");
        Student s2 = new Student("Maria", 30, "Gepeszmernok");

        StudentList sl = new StudentList(5);
        sl.addStudent(s1);
        sl.addStudent(s2);

        for (StudentIterator si = sl.getIterator(); si.hasMoreElements(); ) {
            System.out.println(si.nextElement());
        }

        StudentIterator si2 = sl.getIterator();
        while (si2.hasMoreElements()) {
            System.out.println(si2.nextElement());
        }
    }
}