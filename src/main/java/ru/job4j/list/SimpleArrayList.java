package ru.job4j.list;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {
    private T[] container;
    private int size;
    private int modCount;
    private int point;

    public SimpleArrayList() {
        this.container = (T[]) new Object[10];
    }

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size == container.length - 1) {
            container = grow(container);
        }
        container[size] = value;
        size++;
        modCount++;
    }

    @Override
    public boolean contains(T value) {
        boolean rsl = false;
        for (T el : container) {
            if (Objects.equals(value, el)) {
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    @Override
    public T set(int index, T newValue) {
        T rsl = get(index);
        container[index] = newValue;
        return rsl;
    }

    @Override
    public T remove(int index) {
        T rsl = get(index);
        System.arraycopy(
                container,
                index + 1,
                container,
                index,
                container.length - index - 1
        );
        container[container.length - 1] = null;
        size--;
        modCount++;
        return rsl;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }


    @Override
    public int size() {
        return size;
    }

    private T[] grow(T[] arr) {
        return Arrays.copyOf(arr, arr.length * 2);
    }

    @Override
    public Iterator<T> iterator() {
        int expectedModCount = modCount;
        point = 0;
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return point < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[point++];
            }
        };
    }
}
