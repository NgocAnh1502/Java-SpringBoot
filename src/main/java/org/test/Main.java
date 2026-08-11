package org.test;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ten: ");
        String ten =  sc.nextLine();
        System.out.print("Hello " + ten);
    }
}
