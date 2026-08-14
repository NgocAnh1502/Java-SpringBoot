package org.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListName {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();

        names.add("Nguyen Van B");
        names.add("Nguyen Thi A");
        names.add("Tran Van D");
        names.add("Tran Thi C");

        Collections.sort(names);

        for(String name : names){
            System.out.println(name);
        }
    }
}
