package org.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericsLambda2 {
    public static <E> void printArray(E[] array){
        if (array == null || array.length == 0) {
            System.out.println("Mảng rỗng hoặc null.");
            return;
        }
        for (E element : array) {
            System.out.print(element + " ");
        }
    }

    public static void main(String[] args) {
        String[] product = {"Ao", "Quan", "Vay", "Tui xach"};
        printArray(product);
        List<String>  list = new ArrayList<>(Arrays.asList(product));
        list.sort((s1, s2) -> s1.length() - s2.length());

        System.out.print("\nSo chu cai tang dan: ");
        for(String s : list){
            System.out.print(s + ", ");
        }
    }
}
