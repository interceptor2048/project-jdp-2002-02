package com.kodilla.listImplementation;


public class Main {
    public static void main(String[] args) {
        LinkedListImplementation<Integer> list = new LinkedListImplementation<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        System.out.println("Adding to the list result:");
        list.printAll();

        System.out.println("\nSearch by index (3) result:");
        list.getByIndex(3);

        list.deleteAtIndex(3);
        System.out.println("\nDeleting at index (3) result:");
        list.printAll();

    }
}
