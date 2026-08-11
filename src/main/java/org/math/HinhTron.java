package org.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HinhTron {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.print("Nhap ban kinh hinh tron: ");
            double r = Double.parseDouble(br.readLine());
            if (r <= 0){
                System.out.print("\nKhong ton tai hinh tron");
            } else {
                System.out.println("\nChu vi hinh tron: " + (2 * 3.14 * r));
                System.out.print("Dien tich hinh tron: " + (3.14 * r * r));
            }
        } catch(NumberFormatException ex) {
            System.out.println("Phai nhap so");
        } catch(IOException ex) {
            System.out.println("Loi doc du lieu");
        }
    }
}
