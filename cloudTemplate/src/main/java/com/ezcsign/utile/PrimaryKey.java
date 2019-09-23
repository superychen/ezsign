package com.ezcsign.utile;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * describe:
 *
 * @author donting
 * @date 2019/07/19
 */
public class PrimaryKey {
    public static String generate() {
        return System.currentTimeMillis() + "" + UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            String s = generate();
            System.out.println(s);
            if (map.containsKey(s.length())) {
                map.put(s.length(), map.get(s.length()) + 1);
            } else {
                map.put(s.length(), 1);
            }
        }
        System.out.println(map);
    }

}
