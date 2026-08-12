package org.streamapi;

import org.oop.Student;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamAPI {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("An", 20, (float)6.5),
                new Student("Binh", 22, (float)8.0),
                new Student("Cuong", 20, (float)9.5),
                new Student("Dung", 21, (float)7.0)
        );

        // YÊU CẦU 1: Lọc điểm >= 7 và sắp xếp giảm dần
        List<Student> goodStudents = students.stream()
                .filter(s -> s.getScore() >= 7.0) // Lọc những đứa không lười biếng
                .sorted(Comparator.comparingDouble(Student::getScore).reversed()) // Đảo ngược để giảm dần
                .collect(Collectors.toList()); // Đóng gói lại thành List

        System.out.println("Danh sách sinh viên >= 7 điểm, sắp xếp giảm dần:");
        goodStudents.forEach(s -> System.out.println(s.getName() + " - " + s.getScore()));

        // YÊU CẦU 2: Nhóm sinh viên theo tuổi
        Map<Integer, List<Student>> studentsByAge = students.stream()
                .collect(Collectors.groupingBy(Student::getAge));

        System.out.println("\nNhóm sinh viên theo tuổi:");
        studentsByAge.forEach((age, list) -> {
            System.out.println("Tuổi " + age + ": " + list.stream().map(Student::getName).collect(Collectors.joining(", ")));
        });
    }
}
