package ru.job4j.kiss;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MaxMin {

    public <T> T max(List<T> value, Comparator<T> comparator) {
        return minMax(value, comparator).get(1);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return minMax(value, comparator).get(0);
    }

    private <T> List<T> minMax(List<T> list, Comparator<T> comparator) {
        if (list.size() < 2) {
            throw new IllegalArgumentException("List must be > 2");
        }
        List<T> rsl = new ArrayList<>();
        T min = list.get(0);
        T max = list.get(0);
        for (T el : list) {
            if (comparator.compare(min, el) > 0) {
                min = el;
            } else if (comparator.compare(max, el) < 0) {
                max = el;
            }
        }
        rsl.add(min);
        rsl.add(max);
        return rsl;
    }
}


