package com.helloworld;

public class Math {
    public int cong(int a, int b){
        return a + b;
    }
    public long giaiThua(int n){
        if (n < 0) {
            throw new IllegalArgumentException("Khong co giai thua cho so am");
        }
        if (n == 0 || n == 1){
            return 1;
        }
        long ketQua = 1;
        for(int i = 2; i <= n; i++){
            ketQua *= i;
        }
        return ketQua;
    }
}
