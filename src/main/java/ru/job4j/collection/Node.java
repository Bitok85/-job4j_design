package ru.job4j.collection;

public class Node<E> {
    Node<E> next;
    E data;

    public Node(E data, Node<E> next) {
        this.data = data;
        this.next = next;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }

    public E getData() {
        return data;
    }
}
