
package ru.job4j.collection;

public class SimpleStack<T> {
    private ForwardLinked<T> linked = new ForwardLinked<>();

    public T pop() {
        return linked.deleteFirst();
    }

    public T push(T value) {
        return linked.addFirst(value);
    }

    public boolean isEmpty() {
        return linked.isEmpty();
    }
}
