package org.oop;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Polymorphism_Exception {
    public static void chiaHaiSo(float a, float b){
        try{
            if (b == 0f){
                throw new ArithmeticException("Khong the chia cho 0");
            }
            float c =  a / b;
            System.out.println("Ket qua phep chia: " + c);
        } catch (ArithmeticException ex){
            System.out.println(ex.getMessage());
        } finally {
            System.out.println("Hoan tat phep chia");
        }
    }
    public static void docFileTen(String path){
        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            String name;
            while ((name = br.readLine()) != null){
                System.out.println("\nTen:" + name);
            }
        } catch (IOException e) {
            System.out.println("\nLoi doc file");
        } finally{
            System.out.println("Xong");
        }
    }
    public static void main(String[] args) {
        chiaHaiSo(10,20);
        chiaHaiSo(12, 0);
        docFileTen("text.txt");
    }
}
