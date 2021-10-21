package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {
    private final int[] data;
    private int index = 0;
    private int tmpIndex;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        if (tmpIndex == index) {
            while (data[index] % 2 != 0 && index < data.length - 1) {
                index++;
            }
            tmpIndex = index + 1;
        }
        return data[index] % 2 == 0;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[index++];
    }
}
