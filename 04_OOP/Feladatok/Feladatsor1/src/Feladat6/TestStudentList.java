package Feladat6;

import Feladat6.collection.StudentIterator;
import Feladat6.collection.StudentList;
import Feladat6.core.Student;

public class TestStudentList {
    public static void main(String[] args) {

        Student s1 = new Student("Béla", 20, "Teológia");
        Student s2 = new Student("Erzsébet", 21, "Turizmus");
        StudentList studentList = new StudentList(5);
        studentList.addStudent(s1);
        studentList.addStudent(s2);

        StudentIterator si = studentList.getIterator();
        while (si.hasMoreElements()) {
            System.out.println(si.nextElement());
        }
    }
}