package ru.job4j.collection;

class Node<E> {
    private Node<E> next;
    private E data;

    public Node(E data) {
        this.data = data;
        next = null;
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
