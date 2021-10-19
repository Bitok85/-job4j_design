package ru.job4j.it;

import java.util.NoSuchElementException;
import java.util.Iterator;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int col = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        boolean rsl = false;
        while (row < data.length) {
            if (data[row].length > 0) {
                rsl = true;
                break;
            } else {
                row++;
            }
        }
        return rsl;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Integer rsl = data[row][col++];
        if (col == data[row].length) {
            col = 0;
            row++;
        }
        return rsl;
    }
}