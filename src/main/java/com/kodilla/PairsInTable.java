package com.kodilla;

import java.util.HashMap;
import java.util.Map;

public class PairsInTable {
    private static int numberOfPairs(int[] table) {
        Map<Integer, Integer> map = new HashMap<>();
        for(Integer number : table ) {
            if(!map.containsKey(number)) {
                map.put(number, 1);
            } else {
                int value = map.get(number) + 1;
                map.put(number, value);
            }
        }
        int sumOfPairs = 0;
        for(Integer repeated : map.values()) {
            int pair = repeated/2;
            sumOfPairs += pair;
        }
        return sumOfPairs;
    }

    public static void main(String[] args) {

    int pair1 = numberOfPairs(new int[]{10, 20, 20, 10, 10, 30, 50, 10, 20});
    int pair2 = numberOfPairs(new int[]{20, 20, 30, 20});
    int pair3 = numberOfPairs(new int[]{10, 20, 30, 40, 50});
        if(pair1 == 3 && pair2 == 1 && pair3 == 0) {
        System.out.println("Znaleziono poprawną liczbę par");
    } else {
        System.out.println("Znaleziono błędną liczbę par");
    }
    }
}
