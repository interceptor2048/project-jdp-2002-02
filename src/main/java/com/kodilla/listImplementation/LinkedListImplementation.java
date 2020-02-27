package com.kodilla.listImplementation;


class LinkedListImplementation<E> {
    private Node<E> root;
    private int size;

    void add(E value) {
        if (root == null) {
            root = new Node<E>(value);
        } else {
            Node<E> currentElement = root;
            while (currentElement.getNext() != null) {
                currentElement = currentElement.getNext();
            }
            Node<E> newElement = new Node<E>(value);
            newElement.setPrevious(currentElement);
            currentElement.setNext(newElement);
        }
        size++;
    }

    void printAll() {
        if(root == null) {
            System.out.println("List is empty");
        }
        Node<E> currentElement = root;
        while(currentElement != null) {
            System.out.println(currentElement.getValue());
            currentElement = currentElement.getNext();
        }
    }

    Node<E> getByIndex(int index) {
        Node<E> currentElement = root;
        for (int i = 0; i < index; i++) {
            currentElement = currentElement.getNext();
        }
        System.out.println(currentElement.getValue());
        return currentElement;
    }

    void deleteAtIndex(int index) {
        if(index == 0) {
            root = root.getNext();
        } else {
            Node<E> currentElement = root;
            for(int i = 0; i < index - 1; i++) {
                currentElement = currentElement.getNext();
            }
            currentElement.next = currentElement.next.next;
        }
    }

    //get i delete po indeksie
}