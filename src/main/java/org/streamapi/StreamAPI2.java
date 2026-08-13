package org.streamapi;

import org.oop.Student;

import java.util.*;
import java.util.stream.Collectors;

public class StreamAPI2 {
    public static void main(String[] args) {
        List<Student>  students =  Arrays.asList(
            new Student("An", 21, (float) 10),
            new Student("Duc", 23, (float) 6),
            new Student("Manh", 22, (float) 4),
            new Student("Binh", 22, (float) 5),
            new Student("Quang", 20, (float) 4),
            new Student("Minh", 20, (float) 2),
            new Student("Phuc", 20, (float) 3),
            new Student("Hoang", 22, (float) 5)
        );
        List<Student> StupidStudents = students.stream()
                .sorted(Comparator.comparingDouble(Student::getAge))
                .toList();

        System.out.println("Danh sach sinh vien co tuoi tang dan");
        StupidStudents.forEach(s -> System.out.println(s.getName() + " - " + s.getAge()));

        Map<Float, List<Student>> studentsByAge = students.stream()
                .collect(Collectors.groupingBy(Student::getScore));
        System.out.println("\nNhom sinh vien theo diem:");
        studentsByAge.forEach((score, list) -> {
            System.out.println("Diem " + score + ": " + list.stream().map(Student::getName).collect(Collectors.joining(", ")));
        });
    }
}
