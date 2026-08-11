package org.math;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class TinhToanHaiSo {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            System.out.print("Nhap so thu nhat: ");
            int a = Integer.parseInt(br.readLine());
            System.out.print("Nhap so thu hai: ");
            int b = Integer.parseInt(br.readLine());

            System.out.println("\nTong hai so = " + (a + b));
            System.out.println("Hieu hai so = " + (a - b));
            System.out.println("Tich hai so = " + (a * b));
            if (b == 0){
                System.out.print("Khong the chia cho 0");
            } else{
                System.out.print("Hieu hai so = " + ((float) a / b));
            }
        } catch (NumberFormatException ex){
            System.out.print("Phai nhap vao so nguyen");
        } catch (IOException ex) {
            System.out.println("Loi doc du lieu");
        }
    }
}
