package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {
    private final int[] data;
    private int index = 0;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        int tmpIndex = index;
        if (data[tmpIndex] % 2 == 0) {
            return true;
        } else {
            while (data[tmpIndex] % 2 != 0 && tmpIndex < data.length - 1) {
                tmpIndex++;
            }
            return tmpIndex > index;
        }
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        while (data[index] % 2 != 0) {
            index++;
        }
        return data[index++];
    }
}
