package ru.job4j.set;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import ru.job4j.list.SimpleArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleSet<T> implements Set<T> {
    private SimpleArrayList<T> set = new SimpleArrayList<>();
    private int modCount;
    private int point;

    @Override
    public boolean add(T value) {
        boolean rsl = false;
        if (!set.contains(value)) {
            set.add(value);
            rsl = true;
            modCount++;
        }
        return rsl;
    }

    @Override
    public boolean contains(T value) {
        return set.contains(value);
    }

    @Override
    public Iterator<T> iterator() {
        point = 0;
        int expectedModCount = modCount;
        return new Iterator<T>() {

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return point < set.size();
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return set.get(point++);
            }
        };
    }
}
