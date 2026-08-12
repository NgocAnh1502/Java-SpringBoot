package org.oop;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private int age;
    private float score;

    public Student (String name, int age, float score){
        this.name = name;
        this.age = age;
        this.score = score;
    }

    public float getScore(){
        return score;
    }

    @Override
    public String toString() {
        return "name: " + name + ", age: " + age + ", score: " + score;
    }

    public static void DiemTrungBinh(List<Student> Students){
        float diem = 0;
        float diemtb;
        if(Students.isEmpty()){
            System.out.print("Khong co sinh vien");
        } else{
            for(Student student : Students){
                diem += student.getScore();
            }
            diemtb = diem /  Students.size();
            System.out.print("Diem trung binh cua danh sach sinh vien la: " + diemtb);
        }
    }
    public static void main(String[] args){
        Student s1 = new Student("Nguyen Van A", 10, (float)3.6);
        Student s2 = new Student("Tran Van B", 11, (float)6.7);
        Student s3 = new Student("Nguyen Van A", 13, (float)10);

        List<Student> Students = new ArrayList<>();
        Students.add(s1);
        Students.add(s2);
        Students.add(s3);

        System.out.println("Danh sach sinh vien");
        for(Student student : Students){
            System.out.print(student.toString() + "\n");
        }
        DiemTrungBinh(Students);
    }
}
