package org.collection;

import java.util.*;

public class mapTest {
    public static void priceMenu(Map<String, Integer> map){
        map.put("ao", 10);
        map.put("quan", 20);
        map.put("vay", 50);
        map.put("tui xach", 200);
    }

    public static void product(List<String> list){
        list.addAll(List.of("AO", "Ao", "Ao", "QuAn", "Quan", "VAy", "Tui xach", "Tui xach"));
    }

    public static void main(String[] args) {
        Map<String, Integer> priceCatalog = new HashMap<>();
        priceMenu(priceCatalog);
        System.out.println("Bảng giá ");
        for(Map.Entry<String, Integer> entry : priceCatalog.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        List<String> cart = new ArrayList<>();
        product(cart);

        Map<String, Integer> hoaDon = new TreeMap<>();
        for(String item : cart){
            String items = item.toLowerCase();
            hoaDon.put(items, hoaDon.getOrDefault(item, 0) + 1);
        }
        System.out.println("Hóa đơn: ");
        int bill = 0;
        for(Map.Entry<String, Integer> entry : hoaDon.entrySet()){
            String itemName = entry.getKey();
            int quantity = entry.getValue();
            int unitPrice = priceCatalog.getOrDefault(itemName, 0);

            int totalPrice = unitPrice * quantity;
            bill += totalPrice;

            System.out.println(itemName + " - Số lượng: " + quantity +
                    " - Thành tiền: " + unitPrice + " - Thành tiền: " + totalPrice);

        }
        System.out.println("----------------------------------------");
        System.out.println("TỔNG CỘNG CẦN THANH TOÁN: " + bill + " $");
        System.out.println("========================================");
    }
}
