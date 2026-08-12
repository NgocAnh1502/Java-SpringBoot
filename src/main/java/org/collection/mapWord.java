package org.collection;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class mapWord {
    public static void main(String[] args) {
        String text = "Through the night, through the sky, you're the reason that i still try.";

        String textclean = text.replaceAll("[^a-zA-Z0-9'\\s]", "").toLowerCase();

        String[] words = textclean.split("\\s+");

        Map<String, Integer> map = new HashMap<String, Integer>();
        for (String word : words) {
            if (word.isEmpty()){
                continue;
            }
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        System.out.println("So lan xuat hien cac tu");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
