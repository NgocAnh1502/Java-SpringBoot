package org.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericsLambda {
    public static <T> void printArray(T[] array){
        if(array == null || array.length == 0){
            System.out.println("Mang rong");
            return;
        }
        System.out.print("Mang: ");
        for(T i : array){
            System.out.print(i + " ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Integer [] a = {1, 2, 3, 4, 5, 6};
        printArray(a);
        List<Integer> list = new ArrayList<>(Arrays.asList(a));
        List<Integer> evenList = new ArrayList<>();
        list.forEach(n -> {
            if(n % 2 == 0){
                evenList.add(n);
            }
        });

        System.out.print("\nMang so chan: ");
        for(Integer i : evenList){
            System.out.print(i + " ");
        }
    }
}
