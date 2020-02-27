package com.kodilla.stream;

import java.util.*;
import java.util.stream.Collectors;



public class MyStream {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("Adam");
        names.add("Aleks");
        names.add("Maciek");
        names.add("Tomek");
        names.add("Patryk");

        Map<Character, List<String>> sortedNames = new HashMap<>();

        for (String name : names) {
            char firstLetter = name.charAt(0);
            if (!sortedNames.containsKey(firstLetter)) {
                List<String> elements = new ArrayList<>();
                elements.add(name);
                sortedNames.put(firstLetter, elements);
            } else {
                sortedNames.get(firstLetter).add(name);
            }
        }
        System.out.println("Looped names: " + sortedNames);


        Map<Character, List<String>> streamedNames = names.stream()
                    .collect(Collectors.groupingBy(s -> s.charAt(0) , Collectors.toList()));
        System.out.println("Streamed names: " + streamedNames);

        System.out.println("List of names: " + names);
        int[] abc = new int[0];
        System.out.println(abc.length);
        }
    }