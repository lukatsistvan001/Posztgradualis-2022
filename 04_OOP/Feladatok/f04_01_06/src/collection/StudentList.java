package collection;

import core.Student;

public class StudentList {

    class StudentIteratorImpl implements StudentIterator {

        private int index;

        @Override
        public boolean hasMoreElements() {
            return index < current;
        }

        @Override
        public Student nextElement() {
            return students[index++];
        }
    }

    private int current;
    private Student[] students;

    public StudentList(int size) {
        students = new Student[size];
    }

    public void addStudent(Student student) {
        students[current++] = student;
    }

    public StudentIterator getIterator() {
        return new StudentIteratorImpl();
    }
}