package org.hprtech.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Demo {

    public static void main(String[] args) {
        Set<Student> studentSet = new HashSet<>();
        studentSet.add(new Student(1, "ABC", false));
        studentSet.add(new Student(2, "XYZ", true));
        studentSet.add(new Student(3, "PQR", true));

        Comparator<Student> byPreferred = Comparator.comparing(Student::isActive, Comparator.reverseOrder());
        Supplier<TreeSet<Student>> student = () -> new TreeSet<Student>(byPreferred);
        TreeSet<Student> studentTreeSet = studentSet.stream().collect(Collectors.toCollection(student));
        System.out.println(studentTreeSet);
    }
}
