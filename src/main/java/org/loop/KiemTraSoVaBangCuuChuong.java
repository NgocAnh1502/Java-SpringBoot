package org.loop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KiemTraSoVaBangCuuChuong {
    public static int nhapSo(BufferedReader br, String thongBao){
        while(true) {
            try{
                System.out.print(thongBao);
                return Integer.parseInt(br.readLine());
            } catch (NumberFormatException ex){
                System.out.println("Loi khi nhap so, moi nhap lai");
            } catch (IOException ex){
                System.out.println("Loi khi doc du lieu" + ex.getMessage());
            }
        }
    }
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while(true) {
                System.out.println("\n+--+----------------------------------+");
                System.out.println("| 1| Kiem tra so chan le              +");
                System.out.println("+--+----------------------------------+");
                System.out.println("| 2| Kiem tra so chia het cho 3       +");
                System.out.println("+--+----------------------------------+");
                System.out.println("| 3| Bang cuu chuong tu 2 den 9       +");
                System.out.println("+--+----------------------------------+");
                System.out.println("| 0| Thoat                            +");
                System.out.println("+--+----------------------------------+");
                int luaChon = nhapSo(br, "Moi nhap lua chon: ");
                int so;
                switch (luaChon) {
                    case 1:
                        so = nhapSo(br, "Nhap so can kiem tra: ");
                        if (so % 2 == 0) {
                            System.out.println("\n" + so + " la so chan");
                        } else {
                            System.out.println("\n" + so + " la so le");
                        }
                        break;
                    case 2:
                        so =  nhapSo(br, "Moi nhap lua chon: ");
                        if (so % 3 == 0) {
                            System.out.println("\n" + so + " chia het cho 3");
                        } else {
                            System.out.println("\n" + so + " khong chia het cho 3");
                        }
                        break;
                    case 3:
                        System.out.println("Bang cuu chuong: ");
                        for (int i = 2; i < 10; i++) {
                            for (int j = 1; j <= 10; j++) {
                                System.out.println(i + " * " + j + " = " + (i * j));
                            }
                        }
                        break;
                    case 0:
                        System.out.print("Da thoat chuong trình");
                        return;
                    default:
                        System.out.print("Lua chon khong phu hop, moi chon lai");
                        break;
                }
            }
        } catch (IOException ex){
            System.out.print("Loi khi doc du lieu" + ex.getMessage());
        }
    }
}
