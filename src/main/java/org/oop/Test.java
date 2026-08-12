package org.oop;

import java.util.ArrayList;
import java.util.List;

interface PhuongThucThanhToan {
    void xuLyThanhToan(double soTien);
}

class Momo implements  PhuongThucThanhToan{
    @Override
    public void xuLyThanhToan(double soTien) {
        System.out.println("Thanh toán qua momo với số tiền " + soTien + "VND");
    }
}

class CreditCard implements PhuongThucThanhToan{
    @Override
    public void xuLyThanhToan(double soTien) {
        System.out.println("Thanh toán qua Credit Card với số tiền " + soTien + "VND");
    }
}

class COD implements  PhuongThucThanhToan{
    @Override
    public void xuLyThanhToan(double soTien) {
        System.out.println("Thanh toán khi nhận hàng với số tiền " + soTien + "VND");
    }
}

public class Test {
    public static void main(String[] args) {
        List<PhuongThucThanhToan> pttt = new ArrayList<>();

        pttt.add(new Momo());
        pttt.add(new CreditCard());
        pttt.add(new COD());

        for(PhuongThucThanhToan p : pttt){
            p.xuLyThanhToan(500000);
        }
    }
}